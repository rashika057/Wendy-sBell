package com.rashika059.shukla.finalproject;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFish> data= Collections.emptyList();
    DataFish current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public EmployeeAdapter(Context context, List<DataFish> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_employee, parent,false);
        MyHolder holder=new MyHolder(view,context,data);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        final DataFish current=data.get(position);
        myHolder.fishName.setText("Name: " +current.fishName);
        myHolder.fishPos.setText("Position: " +current.position);
                     // load image into imageview using glide
        Picasso.with(context).load(current.fishImage)

                .into(myHolder.ivFish);
        myHolder.call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"9936053355"));
                try {
                    view.getContext().startActivity(callIntent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        myHolder.msg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                            + "9936053355")));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });


    }


    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {


        ImageView ivFish;
        TextView fishName,fishPos;
        Context context;
        ImageButton call1,msg1;
        List<DataFish> data= Collections.emptyList();
        // create constructor to get widget reference
        public MyHolder(View itemView,Context context,List<DataFish> data) {
            super(itemView);
            this.context=context;
            this.data=data;
            Log.d("TAG", "onClick: ");
            fishName= (TextView) itemView.findViewById(R.id.textFishName);
            ivFish= (ImageView) itemView.findViewById(R.id.ivImage);
            fishPos = (TextView) itemView.findViewById(R.id.textFishPos);
            call1=(ImageButton) itemView.findViewById(R.id.call);
            msg1=(ImageButton) itemView.findViewById(R.id.msg);
        }

    }


}

