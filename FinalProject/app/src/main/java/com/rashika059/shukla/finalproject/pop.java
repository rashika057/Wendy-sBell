package com.rashika059.shukla.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class pop extends AppCompatActivity {
    String name,price;
    TextView tn;
    EditText tp;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        name=getIntent().getStringExtra("Name");
        price=getIntent().getStringExtra("Price");
        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));
        tn=(TextView) findViewById(R.id.txtN);
        tp=(EditText) findViewById(R.id.txtP);
        tn.setText(name);
        tp.setText(price);
        btn =(Button) findViewById(R.id.bttnE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    BackgroundTaskEdit backgroundTask = new BackgroundTaskEdit();
                    backgroundTask.execute(name,tp.getText().toString(),getIntent().getStringExtra("MainMenu"));
                    Intent in=new Intent(pop.this,MainMenuActivity.class);
                    in.putExtra("MainMenu",getIntent().getStringExtra("MainMenu"));
                    in.putExtra("SubMenu",getIntent().getStringExtra("SubMenu"));
                    startActivity(in);
                }
            }
        });
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
