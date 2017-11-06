package com.example.zeeshan.tablename;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    TextView getStarted;


    private static  final String SUCCESS_MSG = "success";
    private static  final String FAIL_MSG = "failed";
    private static  final String CONNECTION_FAILED_MSG = "connection";
    JSONArray person = null;
    JSONArray Data = null;
    JSONParser jParser = new JSONParser();
    private ArrayList<String> idData = new ArrayList<String>();
    private ArrayList<String> passData = new ArrayList<String>();
    String userDataID;
    static String emailID;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_);
        getStarted= (TextView) findViewById(R.id.btnForgotPassword);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home_page = new Intent(Login.this,ForgotPasswordOne.class);
                startActivity(home_page);


            }

        });

        ImageView showPass=(ImageView)findViewById(R.id.imageView2);
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getTransformationMethod() == null) {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    password.setTransformationMethod(null);
                }

            }
        });

        TextView signUp= (TextView) findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home_page = new Intent(Login.this,RegisterNow.class);
                startActivity(home_page);


            }

        });
        RelativeLayout verifyMail= (RelativeLayout)findViewById(R.id.btnLogin);
        email=(EditText) findViewById(R.id.emailEditor);
        password=(EditText) findViewById(R.id.passwordEditor);
        verifyMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean verified = false;
                Log.d("bsafjbshjafb","shjfsfvhjshfvs");
                if (email.getText().toString().equals(" ")  || email.getText().toString().equals("") || email.getText().toString().equals(null)) {
                    Toast.makeText(Login.this, "Enter Email Id", Toast.LENGTH_LONG).show();
                    Log.d("bsafjbshjafb","Enter Email Id");
                } else {
                    if (isEmailValid(email.getText().toString())   ) {

                       try {


                                new getData().execute(email.getText().toString(),password.getText().toString()).get();

                                if(!checkInfo(email.getText().toString(),password.getText().toString()))
                                {
                                    Toast.makeText(Login.this,"Email id or password is incorrect", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(Login.this,"Login Successfully", Toast.LENGTH_SHORT).show();

                                    Login.emailID=email.getText().toString();
                                    Intent home_page = new Intent(Login.this,Initializer.class);
                                    startActivity(home_page);

                                }


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }


                    }
                    else
                    {
                        Log.d("bsafjbshjafb","Enter Valid Email Id");
                        Toast.makeText(Login.this,"Enter Valid Email Id", Toast.LENGTH_LONG).show();
                    }



                }
            }
        });


    }

    public Boolean checkInfo(String Id, String pass) {

        try {
            userDataID=Id;

            ArrayList<String> ids = new getData().execute().get();


            if (ids != null) {
                for (int i = 0; i < ids.size(); i++) {
                    String id = ids.get(i);
                    if (id.equals(Id)) {

                        String password = passData.get(i);
                        if (pass.equals(password)) {
                            return true;
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
    class getData extends AsyncTask<String, String, ArrayList<String>> {


        protected ArrayList<String> doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ID", userDataID ));

            // getting JSON string from URL
            String url = "http://tezkamayi.tk/getData.php";


            JSONObject json = jParser.makeHttpRequest(url, "GET", params);

            try {
                // Checking for SUCCESS TAG
                String result = json.getString("result");

                if (result.equals("success")) {

                    // products found
                    // Getting Array of Products
                    person = json.getJSONArray("UserInfo");

                    // looping through All Products
                    for (int i = 0; i < person.length(); i++) {
                        JSONObject c = person.getJSONObject(i);
                        String id = c.getString("id");

                        String pass = c.getString("pass");
                        //balance=balanceTemp;
                        idData.add(id);
                        passData.add(pass);

                    }

                } else {

                    // Log.i("MainActivity", "result = failed");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return idData;
        }


    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }
}
