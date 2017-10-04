package com.rashika059.shukla.finalproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity
{

FloatingActionButton btna;
public static final int CONNECTION_TIMEOUT = 10000;
public static final int READ_TIMEOUT = 15000;
private RecyclerView mRVFishPrice;
private AdapterFish mAdapter;
String mainmenu,submenu;
private Toolbar mToolbar;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_menu);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mToolbar);
    TextView textView;
    if(getSupportActionBar()!=null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    mainmenu = getIntent().getStringExtra("MainMenu");
    submenu = getIntent().getStringExtra("SubMenu");
    textView = (TextView) findViewById(R.id.textName);
    textView.setText(getIntent().getStringExtra("MainMenu"));
    textView = (TextView) findViewById(R.id.textName1);
    textView.setText(getIntent().getStringExtra("SubMenu"));
    btna = (FloatingActionButton) findViewById(R.id.fab);
    btna.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent in = new Intent(MainMenuActivity.this, ProductEntryActivity.class);
            in.putExtra("MainMenu", mainmenu);
            in.putExtra("SubMenu", submenu);
            startActivity(in);
        }
    });
    new AsyncFetch().execute();
}

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(MainMenuActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://192.168.43.49/ProjectHungerBhagao/alldata_item.php");

            }
            catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream os=conn.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                Log.d("TAG", "doInBackground: "+submenu+"  "+mainmenu);
                String data= URLEncoder.encode("submenu","UTF-8")+"="+ URLEncoder.encode(submenu,"UTF-8")+"&"+
                        URLEncoder.encode("mainmenu","UTF-8")+"="+ URLEncoder.encode(mainmenu,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                // setDoOutput to true as we recieve data from json file


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<DataFishNew> data=new ArrayList<>();

            pdLoading.dismiss();
            try {
                Log.d("TAG",result);
                JSONArray jArray = new JSONArray(result);


                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){

                    JSONObject json_data = jArray.getJSONObject(i);
                    DataFishNew fishData = new DataFishNew();
                    Log.d("TAG",json_data.getString("Price"));
                    Log.d("TAG",json_data.getString("Name"));
                    Log.d("TAG",json_data.getString("Picture"));
                    fishData.fishName= json_data.getString("Name");
                    fishData.fishPrice= json_data.getString("Price");
                    fishData.fishImage= json_data.getString("Picture");
                    fishData.fishId=json_data.getString("Id");
                    fishData.fishType= json_data.getString("Type");
                    fishData.fishMainMenu=json_data.getString("MainMenu");
                    fishData.fishSubMenu=submenu;
                    Log.d("TAG","Server result"+""+json_data.getString("Picture"));
                    data.add(fishData);
                }

                // Setup and Handover data to recyclerview
                mRVFishPrice = (RecyclerView)findViewById(R.id.fishPriceList);
                mAdapter = new AdapterFish(MainMenuActivity.this, data);
                mAdapter.notifyDataSetChanged();
                mRVFishPrice.setAdapter(mAdapter);
                GridLayoutManager mLinear=new GridLayoutManager(MainMenuActivity.this,2);
                mRVFishPrice.setLayoutManager(mLinear);

            }
            catch (JSONException e) {
                Toast.makeText(MainMenuActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}