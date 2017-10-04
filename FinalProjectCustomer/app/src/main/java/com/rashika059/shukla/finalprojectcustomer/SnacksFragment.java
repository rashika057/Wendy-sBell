package com.rashika059.shukla.finalprojectcustomer;


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


public class SnacksFragment extends Fragment {

    private RecyclerView rv;
    private MyAdapter mAdapter;

    String[] menuItems={"Burgers","Maggi and Noodles","Pastas","Pizzas","Rolls","Soups","Specialities"};
    int[] images={R.drawable.burger1,R.drawable.maggi,R.drawable.pasta,R.drawable.pizza,R.drawable.rolls,R.drawable.soup,R.drawable.special};


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        return view;

    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<DataFish> data=new ArrayList<>();

        for(int i=0;i<menuItems.length;i++)
        {
            DataFish fishData = new DataFish();
            fishData.fishName= menuItems[i];
            fishData.fishMainMenu="Snacks";
            fishData.fishImage= Integer.toString(images[i]);
            data.add(fishData);
        }
        rv = (RecyclerView)getView().findViewById(R.id.rv_recycler_view);
        mAdapter = new MyAdapter(data);
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

}


