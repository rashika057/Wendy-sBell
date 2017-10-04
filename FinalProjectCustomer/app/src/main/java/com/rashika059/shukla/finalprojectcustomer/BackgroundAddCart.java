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


public class BackgroundAddCart extends AsyncTask<String,Void,String> {
        String data,username;

        Context ctx;
        public BackgroundAddCart(Context ctx)
        {
            this.ctx=ctx;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url="http://192.168.43.49/ProjectHungerBhagao/addtocart.php";
                Log.d("TAG","in backgroundTask");
                String cartName=params[0];
                String id=params[1];
                String name=params[2];
                String type=params[3];
                String quantity=params[4];
                String amount=params[5];
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
                    data= URLEncoder.encode("cartName","UTF-8")+"="+ URLEncoder.encode(cartName,"UTF-8")+"&"+
                            URLEncoder.encode("id","UTF-8")+"="+ URLEncoder.encode(id,"UTF-8")+"&"+
                            URLEncoder.encode("name","UTF-8")+"="+ URLEncoder.encode(name,"UTF-8")+"&"+
                            URLEncoder.encode("type","UTF-8")+"="+ URLEncoder.encode(type,"UTF-8")+"&"+
                            URLEncoder.encode("quantity","UTF-8")+"="+ URLEncoder.encode(quantity,"UTF-8")+"&"+
                            URLEncoder.encode("amount","UTF-8")+"="+ URLEncoder.encode(amount,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    //get response from server
                    InputStream is=httpURLConnection.getInputStream();
                    is.close();
                    return "Add to cart task Successful";

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


            if (result.equals("Add to cart task Successful"))
            {
                Toast.makeText(ctx, "Item has been successfuly added.", Toast.LENGTH_SHORT).show();

            }


        } }

