package com.rashika059.shukla.finalproject;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.StringTokenizer;

public class EmployeeEntryActivity extends AppCompatActivity {
    private TextInputLayout textInputLayoutname, textInputLayoutcontact, textInputLayoutaddr, textInputLayoutposition, textInputLayoutdob;
    private NestedScrollView nestedScrollView;
    private TextInputEditText name, contact, addr, position, dob;
    String nametxt, addrtxt, contacttxt, postxt, gentxt, dobtxt;
    Button ButtonAdd, ButtonReset;
    ImageView imgView;
    private int selectedOption;
    RadioGroup rgT;
    RadioButton rbT;
    String encodedImage, imgDecodableString;
    private Toolbar mToolbar;
    private String initialDate;
    private String initialMonth;
    private String initialYear;
    private DatePickerDialog dialog = null;


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_entry);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        imgView = (ImageView) findViewById(R.id.imageview);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewK);
        textInputLayoutname = (TextInputLayout) findViewById(R.id.textInputLayoutname);
        textInputLayoutcontact = (TextInputLayout) findViewById(R.id.textInputLayoutcontact);
        textInputLayoutaddr = (TextInputLayout) findViewById(R.id.textInputLayoutaddr);
        name = (TextInputEditText) findViewById(R.id.name);
        addr = (TextInputEditText) findViewById(R.id.addr);
        position = (TextInputEditText) findViewById(R.id.position);
        dob = (TextInputEditText) findViewById(R.id.dob);

        context = getApplicationContext();
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dtTxt;

                String preExistingDate = (String) dob.getText().toString();

                if (preExistingDate != null && !preExistingDate.equals("")) {
                    StringTokenizer st = new StringTokenizer(preExistingDate, "/");
                    initialMonth = st.nextToken();
                    initialDate = st.nextToken();
                    initialYear = st.nextToken();
                    if (dialog == null)
                        dialog = new DatePickerDialog(v.getContext(),
                                new PickDate(), Integer.parseInt(initialYear),
                                Integer.parseInt(initialMonth) - 1,
                                Integer.parseInt(initialDate));
                    dialog.updateDate(Integer.parseInt(initialYear),
                            Integer.parseInt(initialMonth) - 1,
                            Integer.parseInt(initialDate));

                } else {
                    dtTxt = Calendar.getInstance();
                    if (dialog == null)
                        dialog = new DatePickerDialog(v.getContext(), new PickDate(), dtTxt.getTime().getYear(), dtTxt.getTime().getMonth(),
                                dtTxt.getTime().getDay());
                    dialog.updateDate(dtTxt.getTime().getYear(), dtTxt.getTime().getMonth(),
                            dtTxt.getTime().getDay());
                }

                dialog.show();
            }
        });

        contact = (TextInputEditText) findViewById(R.id.contact);
        ButtonAdd = (Button) findViewById(R.id.buttonAddproduct);
        ButtonReset = (Button) findViewById(R.id.buttonReset);
        ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametxt = name.getText().toString();
                addrtxt = addr.getText().toString();
                contacttxt = contact.getText().toString();
                postxt = position.getText().toString();
                dobtxt = dob.getText().toString();
                rgT = (RadioGroup) findViewById(R.id.radioGroupT);
                selectedOption = rgT.getCheckedRadioButtonId();
                rbT = (RadioButton) findViewById(selectedOption);
                gentxt = rbT.getText().toString();
                submit();

            }
        });
        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton b = (RadioButton) findViewById(R.id.type1);
                b.setChecked(true);
                name.setText("");
                addr.setText("");
                position.setText("");
                contact.setText("");
                dob.setText("");
                imgView.setImageBitmap(BitmapFactory.decodeFile(""));
            }
        });
    }

    public void takePicture(View View) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
           /*  File  imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Test.jpg");//directory path and file name two argument in file

                //generate path Uri
                Uri value = Uri.fromFile(imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, value);//Extraoutput show path for saving file
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//define image quality 0 for low and 1 for high quality*/
        startActivityForResult(intent, 0);

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
        super.onActivityResult(requestCode, resultCode, data);
        try
        {

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
                        Toast.makeText(this, encodedImage, Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(this, "Activity.RESULT_CANCELLED", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;


                }

            }//onActivityCamera-END*/
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
        int f = 0;

        if (!isinputEditTextFilled(name, textInputLayoutname, "Name is required")) {
            f = 1;
        }
        if (!isinputEditTextFilled(addr, textInputLayoutaddr, "Address is required")) {
            f = 1;
        }
        if (!isinputEditTextFilled(position, textInputLayoutposition, "Position is required")) {
            f = 1;
        }
        if (!isinputEditTextFilled(contact, textInputLayoutcontact, "Your Contact no. is required")) {
            f = 1;
        }
        if (!isinputEditTextFilled(dob, textInputLayoutdob, "Date of birth is required")) {
            f = 1;
        }
        if (imgView.getDrawable() == null) {
            Toast.makeText(this, "Picture is required", Toast.LENGTH_SHORT).show();
            f = 1;
        }
        if (f == 0) {
            Toast.makeText(getApplicationContext(), "Data Entered", Toast.LENGTH_LONG).show();
            String method = "addE";
            if (isOnline()) {

                Toast.makeText(this, "connection is ok", Toast.LENGTH_SHORT).show();
                Log.d("TAG1", "before calling background task ");
                BackgroundTaskT backgroundTask = new BackgroundTaskT(this);
                Log.d("TAG2", "between calling background task ");
                Toast.makeText(this, encodedImage, Toast.LENGTH_SHORT).show();
                backgroundTask.execute(method, nametxt, gentxt, postxt, contacttxt, addrtxt, dobtxt, encodedImage);
                Log.d("TAG3", "after calling background task ");
                Intent in = new Intent(EmployeeEntryActivity.this, TeamActivity.class);
                startActivity(in);


            } else {
                Toast.makeText(this, "Connection is Offline", Toast.LENGTH_SHORT).show();
            }


        } else {

            Toast.makeText(getApplicationContext(), "Form not validated", Toast.LENGTH_LONG).show();

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private class PickDate implements DatePickerDialog.OnDateSetListener {
        int monthOfYear1;

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear1 = monthOfYear + 1;

            view.updateDate(year, monthOfYear1, dayOfMonth);
            dob.setText(dayOfMonth + "/" + monthOfYear1 + "/" + year);
            dialog.hide();
            Calendar calendar = Calendar.getInstance();
            int currentyear = calendar.get(Calendar.YEAR);
            int ageofperosn = currentyear - year;
            String finalage = String.valueOf(ageofperosn);

        }

    }

}




