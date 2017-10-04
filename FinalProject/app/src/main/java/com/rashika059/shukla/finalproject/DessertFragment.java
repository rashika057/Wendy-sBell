package com.rashika059.shukla.finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DessertFragment extends Fragment {

    private RecyclerView rv;
    private MyAdapter mAdapter;

    String[] menuItems={"Pastries","Donuts","Frozen deserts","Indian Sweets"};
    int[] images={R.drawable.pastry,R.drawable.donut,R.drawable.ice,R.drawable.choco,R.drawable.sweet};


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dessert, container, false);
        return view;

    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<DataFish> data=new ArrayList<>();

        for(int i=0;i<menuItems.length;i++)
        {
            DataFish fishData = new DataFish();
            fishData.fishName= menuItems[i];
            fishData.fishMainMenu="Desserts";
            fishData.fishImage= Integer.toString(images[i]);
            data.add(fishData);
        }
        rv = (RecyclerView)getView().findViewById(R.id.rv_recycler_view);
        mAdapter = new MyAdapter(data);
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

}
