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




public class BackgroundTaskP extends AsyncTask<String,Void,String> {
    String data,username;

    Context ctx;
    public BackgroundTaskP(Context ctx)
    {
        this.ctx=ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://192.168.43.49/ProjectHungerBhagao/customerreg-db.php";
        String method=params[0];
        if (method.equals("register"))
        {

            Log.d("TAG","in backgroundTask");
            String name=params[1];
            String email=params[2];
            username=params[3];
            String password=params[4];
            String confpassword=params[5];
            String mobile=params[6];
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
                        URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("confpassword","UTF-8")+"="+ URLEncoder.encode(confpassword,"UTF-8")+"&"+
                        URLEncoder.encode("mobile","UTF-8")+"="+ URLEncoder.encode(mobile,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                //get response from server
                InputStream is=httpURLConnection.getInputStream();
                is.close();
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


        if (result.equals("Registration.....success"))
        {
            Toast.makeText(ctx, "Registration Successful "+username, Toast.LENGTH_SHORT).show();
           new AsyncCart().execute();

        }


    }

    private class AsyncCart extends AsyncTask<String,Void,String> {
        String data;

        Context ctx;
                @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url="http://192.168.43.49/ProjectHungerBhagao/customercart.php";

                Log.d("TAG","in backgroundTask");

                try{
                    URL url=new URL(reg_url);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                    data=URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    //get response from server
                    InputStream is=httpURLConnection.getInputStream();
                    is.close();
                    return "Cart formation.....success";

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


            if (result.equals("Cart formation.....success"))
            {
                Log.d("TAG", "Cart made");
            }


        }
    }


}

