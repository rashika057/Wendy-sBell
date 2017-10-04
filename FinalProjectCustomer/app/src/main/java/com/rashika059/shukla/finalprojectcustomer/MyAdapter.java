package com.rashika059.shukla.finalprojectcustomer;

/**
 * Created by pc on 12-07-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFish> data= Collections.emptyList();
    DataFish current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public MyAdapter( List<DataFish> data){

        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_view, parent,false);
        MyHolder holder=new MyHolder(view,data);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        final MyHolder myHolder= (MyHolder) holder;
        final DataFish current=data.get(position);
        myHolder.fishName.setText(current.fishName);
        // load image into imageview using glide
        myHolder.ivFish.setImageResource(Integer.parseInt(current.fishImage));
        ((MyHolder) holder).imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),MainMenuActivity.class);
                intent.putExtra("SubMenu",current.fishName);
                intent.putExtra("MainMenu",current.fishMainMenu);
                v.getContext().startActivity(intent);

            }
        });

    }


    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView fishPrice,fishBrand,fishWarranty,fishDescription;
        ImageView ivFish;
        TextView fishName;
        ImageButton imageButton;
        List<DataFish> data= Collections.emptyList();
        // create constructor to get widget reference
        public MyHolder(View itemView,List<DataFish> data) {
            super(itemView);

            this.data=data;
            Log.d("TAG", "onClick: ");
            fishName= (TextView) itemView.findViewById(R.id.textName);
            ivFish= (ImageView) itemView.findViewById(R.id.ivImage);
            imageButton=(ImageButton)itemView.findViewById(R.id.next);


        }

    }


}


