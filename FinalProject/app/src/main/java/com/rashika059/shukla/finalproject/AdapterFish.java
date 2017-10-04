package com.rashika059.shukla.finalproject;

import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AlertDialog;
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
        import android.widget.Toast;

        import com.squareup.picasso.Picasso;
        import java.util.Collections;
        import java.util.List;

        import static java.security.AccessController.getContext;

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

        final MyHolder myHolder= (MyHolder) holder;
        final DataFishNew current=data.get(position);
        myHolder.fishName.setText(current.fishName);
        myHolder.fishPrice.setText("Rs."+current.fishPrice);
        if(current.fishType.equals("Veg"))
        myHolder.fishType.setImageResource(R.drawable.veg);
        else if(current.fishType.equals("Non Veg"))
        myHolder.fishType.setImageResource(R.drawable.nonveg);
        // load image into imageview using glide
        Picasso.with(context).load(current.fishImage).into(myHolder.ivFish);
        ((MyHolder) holder).imgDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int f=1;
                /*AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Deletion");
                builder.setMessage("Do you really want to delete the item?");


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            if (isOnline()) {
                            BackgroundTaskDel backgroundTask = new BackgroundTaskDel();
                            backgroundTask.execute(current.fishId,current.fishMainMenu);
                                                        }
                        Intent intent=new Intent(context,HelpActivity.class);
                        context.startActivity(intent);

                        dialog.dismiss();

                        //else {
                          //  Toast.makeText(.getContext(), "Connection is Offline", Toast.LENGTH_SHORT).show();
                        //}

                    }

                });


                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Code that is executed when clicking NO

                        dialog.dismiss();
                    }

                });


                AlertDialog alert = builder.create();
                alert.show();
*/
                if (isOnline()) {
                    BackgroundTaskDel backgroundTask = new BackgroundTaskDel();
                    backgroundTask.execute(current.fishId,current.fishMainMenu);
                }
                Intent intent=new Intent(v.getContext(),HelpActivity.class);
                intent.putExtra("MainMenu",current.fishMainMenu);
                intent.putExtra("SubMenu",current.fishSubMenu);
                v.getContext().startActivity(intent);
            }
        });
        ((MyHolder) holder).imgEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in=new Intent(v.getContext(),pop.class);
                in.putExtra("Name",current.fishName);
                in.putExtra("Price",current.fishPrice);
                in.putExtra("MainMenu",current.fishMainMenu);
                in.putExtra("SubMenu",current.fishSubMenu);
                v.getContext().startActivity(in);
            }
        });
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }


    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView fishPrice,fishName;
        ImageView ivFish,fishType;
        ImageButton imgDel,imgEdit;
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
            fishPrice = (TextView) itemView.findViewById(R.id.textFishPrice);
            fishType=(ImageView)itemView.findViewById(R.id.ivImageType);
            imgDel=(ImageButton)itemView.findViewById(R.id.ivImageDel);
            imgEdit=(ImageButton) itemView.findViewById(R.id.ivImageEdit);
        }

    }


}

