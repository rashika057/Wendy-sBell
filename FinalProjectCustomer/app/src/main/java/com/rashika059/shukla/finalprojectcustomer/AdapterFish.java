
package com.rashika059.shukla.finalprojectcustomer;

/**
 * Created by pc on 11-07-2017.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rashika059.shukla.finalprojectcustomer.DataFishNew;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;

public class AdapterFish extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFishNew> data= Collections.emptyList();
    DataFishNew current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterFish(Context context, List<DataFishNew> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_fish, parent,false);
        MyHolder holder=new MyHolder(view,context,data);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        final DataFishNew current=data.get(position);
        myHolder.fishName.setText(current.fishName);
        myHolder.fishPrice.setText(current.fishPrice+"Rs.");
        myHolder.fishType.setText(current.fishType);
        // load image into imageview using glide
        Picasso.with(context).load(current.fishImage)

                .into(myHolder.ivFish);
        ((MyHolder) holder).imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ItemDetails.class);
                intent.putExtra("img",current.fishImage);
                intent.putExtra("name",current.fishName);
                intent.putExtra("price",current.fishPrice);
                intent.putExtra("type",current.fishType);
                intent.putExtra("id",current.fishId);
                intent.putExtra("description",current.fishDescription);
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

        TextView fishPrice,fishName,fishType;
        ImageView ivFish;
        ImageButton imgButton;
        Context context;
        List<DataFishNew> data= Collections.emptyList();
        // create constructor to get widget reference
        public MyHolder(View itemView,Context context,List<DataFishNew> data) {
            super(itemView);
            this.context=context;
            this.data=data;
            Log.d("TAG", "onClick: ");
            fishName= (TextView) itemView.findViewById(R.id.textFishName);
            ivFish= (ImageView) itemView.findViewById(R.id.ivImage);
            imgButton=(ImageButton) itemView.findViewById(R.id.next);
            // textSize = (TextView) itemView.findViewById(R.id.textSize);
            fishPrice = (TextView) itemView.findViewById(R.id.textFishPrice);
            fishType=(TextView)itemView.findViewById(R.id.textFishType);
            //  textPrice = (TextView) itemView.findViewById(R.id.textPrice);
        }

    }


}

