package com.rashika059.shukla.finalprojectcustomer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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


public class CartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    protected DrawerLayout nDrawerLayout;
    protected ActionBarDrawerToggle nToggle;
    private Toolbar mToolbar;
    android.support.v7.app.ActionBar actionBar;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;
    private com.rashika059.shukla.finalprojectcustomer.AdapterFishCart mAdapter;
    Button pay,bill;
    int total=0,pp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this,nDrawerLayout,R.string.Open,R.string.Close);
        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(this);
        pay=(Button)findViewById(R.id.pay);
        pay.setOnClickListener(this);
        new AsyncFetch().execute();
           }

    @Override
    public void onClick(View view) {
        if(total!=0)
        {
        Intent in=new Intent(this,PayActivity.class);
            in.putExtra("BillAmount", bill.getText().toString());
        startActivity(in);
    }
    else{
            Intent in=new Intent(this,MainActivity.class);
            startActivity(in);
        }
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(CartActivity.this);
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
                url = new URL("http://192.168.43.49/ProjectHungerBhagao/alldata_cart.php");

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
                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref", 0);
                String cartName=sp.getString("login","");
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data=URLEncoder.encode("cartName","UTF-8")+"="+ URLEncoder.encode(cartName,"UTF-8");
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
                    Log.d("TAG",json_data.getString("TotalPrice"));
                    Log.d("TAG",json_data.getString("Name"));
                    String price=json_data.getString("TotalPrice");
                    pp=Integer.parseInt(price);
                    total=total+pp;
                   // Log.d("TAG",json_data.getString("Picture"));
                    fishData.fishId=json_data.getString("Itemid");
                    fishData.fishName= json_data.getString("Name");
                    fishData.fishType= json_data.getString("Type");
                    fishData.fishQuantity= json_data.getString("Quantity");
                    fishData.fishAmount= json_data.getString("TotalPrice");
                    //  fishData.sizeName= json_data.getString("name");
                    // fishData.price= json_data.getInt("Id");
                    data.add(fishData);
                }
                bill=(Button) findViewById(R.id.bill);
                bill.setText("Rs."+Integer.toString(total));
                if(total!=0)
                    pay.setText("Place Order");
                // Setup and Handover data to recyclerview
                mRVFishPrice = (RecyclerView)findViewById(R.id.fishPriceList);
                mAdapter = new com.rashika059.shukla.finalprojectcustomer.AdapterFishCart(CartActivity.this, data);
                mRVFishPrice.setAdapter(mAdapter);
                mRVFishPrice.setLayoutManager(new LinearLayoutManager(CartActivity.this));

            }
            catch (JSONException e) {
                Toast.makeText(CartActivity.this,"Cart is Empty!! To see your cart you need to login", Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        String itemText=item.toString();
        Log.d("TAG", "onOptionsItemSelected: "+itemText);

        if(nToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.nav_home:
                Intent h=new Intent(this,MainActivity.class);
                startActivity(h);
                break;
            case R.id.nav_Cart:
                Intent c=new Intent(this,CartActivity.class);
                startActivity(c);
                break;
            case R.id.nav_logout:
                Intent w=new Intent(this,Logout.class);
                startActivity(w);
                break;
            case R.id.nav_account:
                Intent f=new Intent(this,CustomerLogin.class);
                startActivity(f);
                break;
            case R.id.nav_tc:
                Intent k=new Intent(this,MainActivity.class);
                startActivity(k);
                break;
            case R.id.nav_info:
                Intent b=new Intent(this,MainActivity.class);
                startActivity(b);
                break;
        }

        return false;
    }
}
