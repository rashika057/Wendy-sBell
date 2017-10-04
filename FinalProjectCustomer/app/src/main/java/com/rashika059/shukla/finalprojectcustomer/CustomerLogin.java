package com.rashika059.shukla.finalprojectcustomer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout nDrawerLayout;
    protected ActionBarDrawerToggle nToggle;
    private Toolbar mToolbar;
    android.support.v7.app.ActionBar actionBar;
    private EditText EditTextUserName,EditTextPassword;
    TextView textToReg;
    String cusernametxt,cpasstxt;
    Button BtnLogin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        mToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this,nDrawerLayout,R.string.Open,R.string.Close);
        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(this);

        EditTextUserName = (EditText) findViewById(R.id.cusername);
        EditTextPassword = (EditText) findViewById(R.id.cpassword);
        textToReg=(TextView)findViewById(R.id.toReg);
        BtnLogin= (Button) findViewById(R.id.buttonLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cusernametxt=EditTextUserName.getText().toString();
                cpasstxt=EditTextPassword.getText().toString();
                submit();

            }
        });
        textToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(CustomerLogin.this,CustomerRegistration.class);
                startActivity(in);
            }
        });

    }
    public void submit() {
        int f=0;
        if(!isinputEditTextFilled(EditTextUserName,"User Name is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(EditTextPassword,"Password  is required")) {
            f=1;
        }

        if(f==0){
            Toast.makeText(getApplicationContext(),"Data entered",Toast.LENGTH_LONG).show();
            String method="login";
            if (isOnline())
            {

                Toast.makeText(this, "connection is ok", Toast.LENGTH_SHORT).show();
                Log.d("TAG1", "before calling background task ");
                BackgroundTaskL backgroundTask=new BackgroundTaskL(this);
                Log.d("TAG2", "between calling background task ");
                backgroundTask.execute(method,cusernametxt,cpasstxt);
                Log.d("TAG3", "after calling background task ");
                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref", 0);
                if(sp.contains("login"))
                    Toast.makeText(CustomerLogin.this,sp.getString("login",""),Toast.LENGTH_LONG).show();
                finish();

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
    public boolean isinputEditTextFilled(EditText textInputEditText,String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputEditText.setError(message);
            return false;
        }
        return true;
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






