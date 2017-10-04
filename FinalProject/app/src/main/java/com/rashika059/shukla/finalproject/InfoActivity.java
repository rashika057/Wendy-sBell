package com.rashika059.shukla.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
public class InfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout nDrawerLayout;
    protected ActionBarDrawerToggle nToggle;
    android.support.v7.app.ActionBar actionBar;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
         mToolbar=(Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(mToolbar);
        actionBar=getSupportActionBar();
        actionBar.show();
        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this,nDrawerLayout,R.string.Open,R.string.Close);
        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(this);

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
            case R.id.nav_Orders:
                Intent c=new Intent(this,OrdersActivity.class);
                startActivity(c);
                break;
            case R.id.nav_team:
                Intent f=new Intent(this,TeamActivity.class);
                startActivity(f);
                break;
            case R.id.nav_tc:
                Intent k=new Intent(this,TcActivity.class);
                startActivity(k);
                break;
            case R.id.nav_info:
                Intent b=new Intent(this,InfoActivity.class);
                startActivity(b);
                break;
        }

        return false;
    }
}
