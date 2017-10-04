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

public class BackgroundTaskP extends AsyncTask<String,Void,String> {
    String data;

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
        String reg_url="http://192.168.43.49/ProjectHungerBhagao/item-db.php";;
        String method=params[0];
        if (method.equals("addP"))
        {

            Log.d("TAG","in backgroundTask");
            String name=params[1];
            String type=params[2];
            String price=params[3];
            String desc=params[4];
            String mainmenu1=params[5];
            String submenu1=params[6];
            String Item_img=params[7];
            Log.d("TAG","result :"+name+" "+type+" "+price+""+Item_img);
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
                        URLEncoder.encode("type","UTF-8")+"="+ URLEncoder.encode(type,"UTF-8")+"&"+
                        URLEncoder.encode("price","UTF-8")+"="+ URLEncoder.encode(price,"UTF-8")+"&"+
                        URLEncoder.encode("desc","UTF-8")+"="+ URLEncoder.encode(desc,"UTF-8")+"&"+
                        URLEncoder.encode("mainmenu","UTF-8")+"="+ URLEncoder.encode(mainmenu1,"UTF-8")+"&"+
                        URLEncoder.encode("submenu","UTF-8")+"="+ URLEncoder.encode(submenu1,"UTF-8")+"&"+
                        URLEncoder.encode("Item_img","UTF-8")+"="+ URLEncoder.encode(Item_img,"UTF-8");                        ;
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

