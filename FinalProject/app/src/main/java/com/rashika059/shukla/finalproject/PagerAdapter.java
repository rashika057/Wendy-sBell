package com.rashika059.shukla.finalproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private String[] tabtitle=new String[]{"Snacks","Beverages","Desserts"};
    Context context;
    public PagerAdapter(FragmentManager fm,Context context) {

        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if (position==0)
        {
            fragment=new SnacksFragment();
        }
        if (position==1)
        {
            fragment=new BeveragesFragment();
        }
        if (position==2)
        {
            fragment=new DessertFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabtitle[position];
    }
}