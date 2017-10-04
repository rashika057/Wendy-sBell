package com.rashika059.shukla.finalprojectcustomer;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.squareup.picasso.Picasso;

public class ItemDetails extends AppCompatActivity {
    ImageView profile;
    TextView dname,dtype,dprice,ddesc,dquantity;
    String blurl,quantity,dnametxt,dtypetxt,dpricetxt,ddesctxt,dquantitytxt,didtxt;
    ImageButton btninc,btndec;
    Button btnCart;
    private Toolbar mToolbar;
    int quan=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        blurl=getIntent().getStringExtra("img");
        dnametxt=getIntent().getStringExtra("name");
        dtypetxt=getIntent().getStringExtra("type");
        dpricetxt=getIntent().getStringExtra("price");
        ddesctxt=getIntent().getStringExtra("description");
        didtxt=getIntent().getStringExtra("id");
        profile=(ImageView) findViewById(R.id.profile_image);
        dname=(TextView) findViewById(R.id.textItemName);
        dtype=(TextView) findViewById(R.id.textItemType);
        dprice=(TextView) findViewById(R.id.textItemPrice);
        ddesc=(TextView) findViewById(R.id.textItemDescription);
        dquantity=(TextView)findViewById(R.id.quantity);
        btninc=(ImageButton)findViewById(R.id.addQuantity);
        btndec=(ImageButton)findViewById(R.id.subQuantity);
        dquantity.setText("1");
        btninc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quan++;
                dquantity.setText(Integer.toString(quan));

            }
        });
        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quan--;
                if(quan>0)
                dquantity.setText(Integer.toString(quan));
            }
        });
        dtype.setText("Type : "+dtypetxt);
        dname.setText("Name : "+dnametxt);
        dprice.setText("Price : "+dpricetxt);
        ddesc.setText("Description : "+ddesctxt);
                Picasso.with(this)
                .load(blurl)
                //.resize(400,400)                        // optional
                .into(profile);
        btnCart=(Button)findViewById(R.id.addbasket);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref", 0);
                    if(!(sp.contains("login"))){
                        Toast.makeText(ItemDetails.this,"YEE",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(ItemDetails.this,CustomerLogin.class);
                    startActivity(in);
                }
                else{
                        String cartName=sp.getString("login","");
                        Log.d("TAG", "onClick: "+cartName);
                        if (isOnline())
                        {
                            dquantitytxt=dquantity.getText().toString();
                            int amount=(Integer.parseInt(dpricetxt))*(Integer.parseInt(dquantitytxt));
                             String amounttxt=Integer.toString(amount);
                            Toast.makeText(ItemDetails.this, cartName, Toast.LENGTH_SHORT).show();
                            Toast.makeText(ItemDetails.this, "connection is ok", Toast.LENGTH_SHORT).show();
                            Log.d("TAG1", "before calling background task ");
                            BackgroundAddCart backgroundTask=new BackgroundAddCart(ItemDetails.this);
                            Log.d("TAG2", "between calling background task ");
                            backgroundTask.execute(cartName,didtxt,dnametxt,dtypetxt,dquantitytxt,amounttxt);
                            Log.d("TAG3", "after calling background task ");
                        }
                }
            }
            public boolean isOnline() {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cm.getActiveNetworkInfo();
                return info != null && info.isConnectedOrConnecting();
            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
