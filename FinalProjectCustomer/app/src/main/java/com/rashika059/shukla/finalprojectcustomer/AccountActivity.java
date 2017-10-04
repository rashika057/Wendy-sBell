package com.rashika059.shukla.finalprojectcustomer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout nDrawerLayout;
    protected ActionBarDrawerToggle nToggle;
    private Toolbar mToolbar;
    android.support.v7.app.ActionBar actionBar;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;

    Button pay,bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this,nDrawerLayout,R.string.Open,R.string.Close);
        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref", 0);
        if(!(sp.contains("login"))){
            Intent in=new Intent(AccountActivity.this,CustomerLogin.class);
            startActivity(in);

        }
        String userName=sp.getString("login","");
        TextView tt=(TextView) findViewById(R.id.username);
        tt.setText("Welcome   ");

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
                Intent f=new Intent(this,AccountActivity.class);
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
