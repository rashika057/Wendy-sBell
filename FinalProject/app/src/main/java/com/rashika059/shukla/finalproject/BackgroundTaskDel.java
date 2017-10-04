package com.rashika059.shukla.finalproject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by pc on 24-08-2017.
 */

public class BackgroundTaskDel extends AsyncTask<String,Void,String> {
    String data;
    Context ctx;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://192.168.43.49/ProjectHungerBhagao/itemdel-db.php";;

            String itemid=params[0];
            String mainmenu=params[1];
            try{
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                data= URLEncoder.encode("id","UTF-8")+"="+ URLEncoder.encode(itemid,"UTF-8")+"&"+
                        URLEncoder.encode("mainmenu","UTF-8")+"="+ URLEncoder.encode(mainmenu,"UTF-8");                        ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                Log.d("TAG","send "+data);
                return "Deletion.....success";

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        if (result.equals("Login Success...Welcome"))
        {
            Toast.makeText(ctx, "Welcome", Toast.LENGTH_SHORT).show();


        }


    }
}


