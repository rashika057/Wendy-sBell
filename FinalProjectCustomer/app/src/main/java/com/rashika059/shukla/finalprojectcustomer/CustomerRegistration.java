package com.rashika059.shukla.finalprojectcustomer;


        import android.content.Context;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class CustomerRegistration extends AppCompatActivity {
    private final AppCompatActivity activity = CustomerRegistration.this;
    private EditText EditTextName, EditTextEmail,EditTextUserName,EditTextPassword;
    private EditText EditTextConfPassword,EditTextMobile;
    TextView textToLogin;
    String cnametxt,cemailtxt,cusernametxt,cpasstxt,cconfpasstxt,cmobiletxt;
    Button BtnReg;
    Context context;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        EditTextName = (EditText) findViewById(R.id.cname);
        EditTextEmail = (EditText) findViewById(R.id.cemail);
        EditTextUserName = (EditText) findViewById(R.id.cusername);
        EditTextPassword = (EditText) findViewById(R.id.cpassword);
        EditTextConfPassword = (EditText) findViewById(R.id.cconfpassword);
        EditTextMobile=(EditText) findViewById(R.id.cmobile);
        textToLogin=(TextView)findViewById(R.id.tologin);
         BtnReg= (Button) findViewById(R.id.buttonReg);
         BtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 cnametxt=EditTextName.getText().toString();
                 cemailtxt=EditTextEmail.getText().toString();
                 cusernametxt=EditTextUserName.getText().toString();
                 cpasstxt=EditTextPassword.getText().toString();
                 cconfpasstxt=  EditTextConfPassword.getText().toString();
                 cmobiletxt=EditTextMobile.getText().toString();
                submit();

            }
        });
      textToLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent in=new Intent(CustomerRegistration.this,CustomerLogin.class);
              startActivity(in);
          }
      });

    }
        public void submit() {
        int f=0;
        if(!isinputEditTextFilled(EditTextName,"Name is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(EditTextEmail,"Email is required")) {
            f=1;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(EditTextEmail.getText().toString()).matches()) {
            EditTextEmail.setError("Invalid Email");
            f=1;
        }
        if(!isinputEditTextFilled(EditTextUserName,"User Name is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(EditTextPassword,"Password  is required")) {
            f=1;
        }
        if(!isinputEditTextFilled(EditTextConfPassword,"Confirm password is required")) {
            f=1;
        }
        else if(!(cpasstxt.equals(cconfpasstxt)))
        {
           EditTextConfPassword.setError("This is not same as above password");
            f=1;
        }
        if(!isinputEditTextFilled(EditTextMobile,"Phone No. is required")) {
            f=1;
        }
        else if(EditTextMobile.getText().toString().length()!=10){
            EditTextMobile.setError("Invalid Mobile No.");
            f=1;
        }
        if(f==0){
            Toast.makeText(getApplicationContext(),"Form data send",Toast.LENGTH_LONG).show();
            String method="register";
            if (isOnline())
            {

                Toast.makeText(this, "connection is ok", Toast.LENGTH_SHORT).show();
                Log.d("TAG1", "before calling background task ");
                BackgroundTaskP backgroundTask=new BackgroundTaskP(this);
                Log.d("TAG2", "between calling background task ");
                backgroundTask.execute(method,cnametxt,cemailtxt,cusernametxt,cpasstxt,cconfpasstxt,cmobiletxt);
                Log.d("TAG3", "after calling background task ");

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
    public boolean isinputEditTextFilled(EditText textInputEditText,String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputEditText.setError(message);
            return false;
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





