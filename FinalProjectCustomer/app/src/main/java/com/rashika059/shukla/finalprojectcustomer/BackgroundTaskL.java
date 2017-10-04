package com.rashika059.shukla.finalprojectcustomer;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static com.rashika059.shukla.finalprojectcustomer.MainMenuActivity.READ_TIMEOUT;

public class BackgroundTaskL extends AsyncTask<String,Void,String> {
        public String data,message;
        SharedPreferences sp;
        Context ctx;
public BackgroundTaskL(Context ctx)
        {
        this.ctx=ctx;

        }


        HttpURLConnection conn;
    URL url = null;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //this method will be running on UI thread

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // Enter URL address where your json file resides
            // Even you can make call to php file which returns json data
            url = new URL("http://192.168.43.49/ProjectHungerBhagao/log.php");

        }

        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.toString();
        }

            String username = params[1];
            String password = params[2];

            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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



        try {
            Log.d("TAG",result);
            JSONArray jArray = new JSONArray(result);

           if(jArray.length()!=0) {
               JSONObject json_data = jArray.getJSONObject(0);
               Log.d("TAG", "onPostExecute: Welcome"+json_data.getString("Username"));
               Toast.makeText(ctx,"Welcome",Toast.LENGTH_LONG).show();
               sp = ctx.getSharedPreferences("MyPref", 0);
               SharedPreferences.Editor editor = sp.edit();
               editor.putString("login",json_data.getString("Username"));
               editor.commit();

           }
           else{
               Toast.makeText(ctx,"Wrong Username or password",Toast.LENGTH_LONG).show();

           }
            // Extract data from json and store into ArrayList as class objects



        }
        catch (JSONException e) {
            Log.d("TAG", "onPostExecute: "+e.toString());
        }

    }

}