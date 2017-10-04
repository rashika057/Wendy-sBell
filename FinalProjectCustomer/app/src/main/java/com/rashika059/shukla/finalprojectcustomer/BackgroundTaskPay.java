package com.rashika059.shukla.finalprojectcustomer;

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
 * Created by pc on 15-07-2017.
 */

    public class BackgroundTaskPay extends AsyncTask<String,Void,String> {
        String data;

        Context ctx;
        public BackgroundTaskPay(Context ctx)
        {
            this.ctx=ctx;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url="http://192.168.43.49/ProjectHungerBhagao/order.php";;

                Log.d("TAG","in backgroundTask");
                String name=params[0];
                String address=params[1];
                String phone=params[2];
                String orderkey=params[3];
                String billamount=params[4];
                try{
                    URL url=new URL(reg_url);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                    Log.d("TAG", "doInBackground: "+name);
                    //encode data before send it
                    //no space permiteted in equals sign
                    Log.d("TAG","doInBackground: "+name);
                    data= URLEncoder.encode("name","UTF-8")+"="+ URLEncoder.encode(name,"UTF-8")+"&"+
                            URLEncoder.encode("address","UTF-8")+"="+ URLEncoder.encode(address,"UTF-8")+"&"+
                            URLEncoder.encode("phone","UTF-8")+"="+ URLEncoder.encode(phone,"UTF-8")+"&"+
                            URLEncoder.encode("billamount","UTF-8")+"="+ URLEncoder.encode(billamount,"UTF-8")+"&"+
                            URLEncoder.encode("orderkey","UTF-8")+"="+ URLEncoder.encode(orderkey,"UTF-8");                        ;
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    //get response from server
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



