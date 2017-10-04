package com.rashika059.shukla.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Intent intent=new Intent(HelpActivity.this,MainMenuActivity.class);
        intent.putExtra("MainMenu",getIntent().getStringExtra("MainMenu"));
        intent.putExtra("SubMenu" ,getIntent().getStringExtra("SubMenu"));
        startActivity(intent);
    }
}
