package com.rashika059.shukla.finalprojectcustomer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class PayActivity extends AppCompatActivity {
    private TextInputLayout textInputLayoutname,textInputLayoutprice,textInputLayoutbrand,textInputLayoutwarranty,textInputLayoutquantity,textInputLayoutdesc;
    private final AppCompatActivity activity = PayActivity.this;
    private NestedScrollView nestedScrollView;
    private EditText name,address,phone;
    String nametxt,paymenttypetxt,addresstxt,OrderKey,phonetxt;
    Button ButtonPay;
    RadioGroup rgT;
    RadioButton rbT;
    int selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref", 0);
         OrderKey=sp.getString("login","");
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        name = (EditText) findViewById(R.id.cusername);
        address = (EditText) findViewById(R.id.cuseradd);
        phone=(EditText) findViewById(R.id.cuserph);
        ButtonPay = (Button) findViewById(R.id.buttonPay);

        ButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametxt=name.getText().toString();
                addresstxt=address.getText().toString();
                phonetxt=phone.getText().toString();
                rgT = (RadioGroup)findViewById(R.id.radioGroupM);
                selectedOption = rgT.getCheckedRadioButtonId();

                selectedOption = rgT.getCheckedRadioButtonId();
                rbT = (RadioButton)findViewById(selectedOption);
                paymenttypetxt= rbT.getText().toString();
                submit();

            }
        });

    }



    public void submit() {
        int f=0;

        if(!isinputEditTextFilled(name,"Name is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(address,"Address is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(phone,"Contact No. is required")) {
            f=1;
        }
        if(f==0){
            Toast.makeText(getApplicationContext(),"Data Entered",Toast.LENGTH_LONG).show();
            if (isOnline())
            {

                Toast.makeText(this, "connection is ok", Toast.LENGTH_SHORT).show();
                Log.d("TAG1", "before calling background task ");
                BackgroundTaskPay backgroundTask=new BackgroundTaskPay(this);
                Log.d("TAG2", "between calling background task ");

                backgroundTask.execute(nametxt,addresstxt,phonetxt,OrderKey,getIntent().getStringExtra("BillAmount"));
                Log.d("TAG3", "after calling background task ");
                Intent in=new Intent(PayActivity.this,MainActivity.class);
                startActivity(in);


            } else {
                Toast.makeText(this, "Connection is Offline", Toast.LENGTH_SHORT).show();
            }


        }


        else{

            Toast.makeText(getApplicationContext(),"Form not validated",Toast.LENGTH_LONG).show();

        }

    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
    public boolean isinputEditTextFilled(EditText textInputEditText, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputEditText.setError(message);
            //hideKeyboardFrom(textInputEditText);
            return false;
        }

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}






