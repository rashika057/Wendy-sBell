package com.rashika059.shukla.finalprojectcustomer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref", 0);
        if((sp.contains("login"))){
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("login");
            editor.apply();
            Toast.makeText(Logout.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
            Intent in=new Intent(this,MainActivity.class);
            startActivity(in);

        }
        else
            Toast.makeText(Logout.this,"You haven't logged in yet",Toast.LENGTH_SHORT).show();
        finish();
    }
}
