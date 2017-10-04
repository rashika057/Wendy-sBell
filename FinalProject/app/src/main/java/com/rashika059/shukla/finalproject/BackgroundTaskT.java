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
public class BackgroundTaskT extends AsyncTask<String,Void,String> {
    String data;
    Context ctx;
    public BackgroundTaskT(Context ctx)
    {
        this.ctx=ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://192.168.43.49/ProjectHungerBhagao/employee-db.php";;
        String method=params[0];
        if (method.equals("addE"))
        {

            Log.d("TAG","in backgroundTask");
            String name=params[1];
            String gen=params[2];
            String pos=params[3];
            String contact=params[4];
            String addr=params[5];
            String dob=params[6];
            String Item_img=params[7];
            try{
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                Log.d("TAG","doInBackground: "+name);
                data= URLEncoder.encode("name","UTF-8")+"="+ URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("gen","UTF-8")+"="+ URLEncoder.encode(gen,"UTF-8")+"&"+
                        URLEncoder.encode("pos","UTF-8")+"="+ URLEncoder.encode(pos,"UTF-8")+"&"+
                        URLEncoder.encode("contact","UTF-8")+"="+ URLEncoder.encode(contact,"UTF-8")+"&"+
                        URLEncoder.encode("addr","UTF-8")+"="+ URLEncoder.encode(addr,"UTF-8")+"&"+
                        URLEncoder.encode("dob","UTF-8")+"="+ URLEncoder.encode(dob,"UTF-8")+"&"+
                        URLEncoder.encode("Item_img","UTF-8")+"="+ URLEncoder.encode(Item_img,"UTF-8");                        ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                Log.d("TAG","send "+data);
                return "Registration.....success";

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

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

