package com.rashika059.shukla.finalproject;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import java.util.Collections;
        import java.util.List;

public class AdapterFishOrder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFish> data= Collections.emptyList();
    DataFish current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterFishOrder(Context context, List<DataFish> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_order, parent,false);
        MyHolder holder=new MyHolder(view,context,data);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        final DataFish current=data.get(position);
        myHolder.fishOrderKey.setText("Order Key: " +current.orderOrderKey);
        myHolder.fishBillAmount.setText("Bill Amount: Rs." + current.orderBillAmount);               // load image into imageview using glide
        ((MyHolder) holder).imgbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent ob=new Intent(Intent.ACTION_VIEW);
                ob.setData(Uri.parse("http://192.168.43.49/ProjectHungerBhagao/orders/"+current.orderOrderKey+current.id+".pdf"));
                v.getContext().startActivity(ob);

            }
        });
    }


    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView fishOrderKey,fishBillAmount;
        ImageButton imgbtn;
        Context context;
        List<DataFish> data= Collections.emptyList();
        // create constructor to get widget reference
        public MyHolder(View itemView,Context context,List<DataFish> data) {
            super(itemView);
            this.context=context;
            this.data=data;
            Log.d("TAG", "onClick: ");
            fishOrderKey= (TextView) itemView.findViewById(R.id.textOrderKey);
            fishBillAmount = (TextView) itemView.findViewById(R.id.textAmount);
            imgbtn=(ImageButton)itemView.findViewById(R.id.next);
        }

    }


}

