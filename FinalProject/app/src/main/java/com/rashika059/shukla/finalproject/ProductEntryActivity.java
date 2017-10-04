package com.rashika059.shukla.finalproject;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.support.design.widget.TextInputEditText;
        import android.support.design.widget.TextInputLayout;
        import android.support.v4.widget.NestedScrollView;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Base64;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Spinner;
        import android.widget.Toast;

        import java.io.ByteArrayOutputStream;

public class ProductEntryActivity extends AppCompatActivity {
    private TextInputLayout textInputLayoutname,textInputLayoutprice,textInputLayoutbrand,textInputLayoutwarranty,textInputLayoutquantity,textInputLayoutdesc;
    private final AppCompatActivity activity = ProductEntryActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputEditText name,price,warranty,quantity,desc;
    String nametxt,typetxt,pricetxt,desctxt,mainmenutxt,submenutxt,recievedmainmenu,recievedsubmenu;
    Button ButtonAdd,ButtonReset;
    ImageView imgView;
    RadioGroup rgT,rgM;
    RadioButton rbT,rbM;
    String encodedImage,imgDecodableString;
    Context context;
    Spinner spinner1;
    int selectedOption,spinnerPosition;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        recievedmainmenu=getIntent().getStringExtra("MainMenu");
        recievedsubmenu=getIntent().getStringExtra("SubMenu");
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        imgView = (ImageView) findViewById(R.id.imageview);
        spinner1=(Spinner) findViewById(R.id.spinnerMenu);
        ArrayAdapter myAdap = (ArrayAdapter) spinner1.getAdapter(); //cast to an ArrayAdapter
        spinnerPosition = myAdap.getPosition(recievedsubmenu);
        spinner1.setSelection(spinnerPosition);
        submenutxt = spinner1.getSelectedItem().toString().trim();
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewK);
        textInputLayoutname=(TextInputLayout)findViewById(R.id.textInputLayoutname);
        textInputLayoutprice=(TextInputLayout) findViewById(R.id.textInputLayoutprice);
        textInputLayoutdesc=(TextInputLayout) findViewById(R.id.textInputLayoutdesc);
        name = (TextInputEditText) findViewById(R.id.name);
        price = (TextInputEditText) findViewById(R.id.price);
        desc = (TextInputEditText) findViewById(R.id.desc);
        ButtonAdd = (Button) findViewById(R.id.buttonAddproduct);
        ButtonReset = (Button) findViewById(R.id.buttonReset);
        if(((RadioButton)findViewById(R.id.m1)).getText().toString().equals(recievedmainmenu)) {
            ((RadioButton) findViewById(R.id.m1)).setChecked(true);
        }
        else if(((RadioButton)findViewById(R.id.m2)).getText().toString().equals(recievedmainmenu)) {
            ((RadioButton) findViewById(R.id.m2)).setChecked(true);
        }
        else {
            ((RadioButton) findViewById(R.id.m3)).setChecked(true);
        }


        ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametxt=name.getText().toString();
                pricetxt=price.getText().toString();
                desctxt=desc.getText().toString();
                rgM = (RadioGroup)findViewById(R.id.radioGroupM);
                selectedOption = rgM.getCheckedRadioButtonId();
                rbM = (RadioButton)findViewById(selectedOption);
                mainmenutxt = rbM.getText().toString();
                rgT = (RadioGroup)findViewById(R.id.radioGroupT);
                selectedOption = rgT.getCheckedRadioButtonId();
                rbT = (RadioButton)findViewById(selectedOption);
                typetxt= rbT.getText().toString();
                Toast.makeText(ProductEntryActivity.this,mainmenutxt,Toast.LENGTH_SHORT).show();
                submit();

            }
        });
        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton b=(RadioButton) findViewById(R.id.type3);
                name.setText("");
                price.setText("");
                desc.setText("");
                imgView.setImageBitmap(BitmapFactory.decodeFile(""));
            }
        });
    }


    public void browseImage(View v) {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        galleryIntent.putExtra("crop", "true");
        galleryIntent.putExtra("outputX", 200);
        galleryIntent.putExtra("outputY", 260);
        galleryIntent.putExtra("aspectX", 1);
        galleryIntent.putExtra("aspectY", 1);
        galleryIntent.putExtra("scale", true);
        galleryIntent.putExtra("return-data", true);
// Start the Intent
        startActivityForResult(galleryIntent, 0);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 0) {
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // Toast.makeText(this, "Picture saved at " + imageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                        Toast.makeText(this, "ImageSet", Toast.LENGTH_SHORT).show();
                        imgView.setImageBitmap(thumbnail);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        if (thumbnail != null) {
                            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object//0 for low quality
                        }
                        byte[] b = baos.toByteArray();
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        Toast.makeText(this, "Wait for moment ....", Toast.LENGTH_SHORT).show();
                        Toast.makeText(this,encodedImage,Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(this, "Activity.RESULT_CANCELLED", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;


                }

            }//onActivityCamera-END
            if (requestCode == 0 && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                if (cursor != null) {
                    cursor.moveToFirst();
                }

                int columnIndex = 0;
                if (cursor != null) {
                    columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                }
                if (cursor != null) {
                    imgDecodableString = cursor.getString(columnIndex);
                }
                if (cursor != null) {
                    cursor.close();
                }
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                //imageUploadSTART

                Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object//0 for low quality
                byte[] b = baos.toByteArray();
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                Toast.makeText(this, "ImageSet", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Wait for moment ....", Toast.LENGTH_SHORT).show();
                Log.d("error", "images" + encodedImage);
                //close
            }
        } catch (Exception e) {
            Toast.makeText(this, "Problem Detected!", Toast.LENGTH_LONG).show();
        }
    }


    public void submit() {
        int f=0;

        if(!isinputEditTextFilled(name,textInputLayoutname,"Name is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(price,textInputLayoutprice,"Price is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(desc,textInputLayoutdesc,"Description is required")) {
            f=1;
        }
        if(!(submenutxt.equals(recievedsubmenu)))
        {
            Toast.makeText(this, "Select required option", Toast.LENGTH_SHORT).show();
            f=1;
        }
        if(imgView.getDrawable() == null) {
            Toast.makeText(this,"Picture is required",Toast.LENGTH_SHORT).show();
            f = 1;
        }
        if(f==0){
            Toast.makeText(getApplicationContext(),"Data Entered",Toast.LENGTH_LONG).show();
            String method="addP";
            if (isOnline())
            {

                Toast.makeText(this, "connection is ok", Toast.LENGTH_SHORT).show();
                Log.d("TAG1", "before calling background task ");
                BackgroundTaskP backgroundTask=new BackgroundTaskP(this);
                Log.d("TAG2", "between calling background task ");
                Toast.makeText(this,encodedImage,Toast.LENGTH_SHORT).show();
                backgroundTask.execute(method,nametxt,typetxt,pricetxt,desctxt,mainmenutxt,submenutxt,encodedImage);
                Log.d("TAG3", "after calling background task ");
                Intent in=new Intent(ProductEntryActivity.this,MainMenuActivity.class);
                in.putExtra("MainMenu",recievedmainmenu);
                in.putExtra("SubMenu",recievedsubmenu);
                startActivity(in);


            } else {
                Toast.makeText(this, "Connection is Offline", Toast.LENGTH_SHORT).show();
            }


        }


        else{

            Toast.makeText(getApplicationContext(),"Form not validated",Toast.LENGTH_LONG).show();

        }

    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
    public boolean isinputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputEditText.setError(message);
            //hideKeyboardFrom(textInputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}






