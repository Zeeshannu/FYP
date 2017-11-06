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

import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterNow extends AppCompatActivity {
    JSONArray person = null;

    private static String url = "http://tezkamayi.tk/getTable.php";
    private static String urlGetEmail = "http://tezkamayi.tk/getID.php";
    private static String urlInsertUser = "http://tezkamayi.tk/Insert_User.php";
    private static final String SUCCESS_MSG = "success";
    private static final String CONNECTION_FAILED_MSG = "connection";
    JSONParser jParser = new JSONParser();
    EditText editFirstName,editLastName,editUserEmail, editPass;

    private ArrayList<String> idData = new ArrayList<String>();
    private ArrayList<String> passData = new ArrayList<String>();


    ImageView showPass;

    static String firstName,lastName,UserID,Password;
    private GoogleApiClient client;

    RelativeLayout register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.register_now);


        register= (RelativeLayout) findViewById(R.id.btnRegister);


        editUserEmail= (EditText) findViewById(R.id.emailEditor);
        editPass = (EditText) findViewById(R.id.passwordEditor);
        editFirstName =(EditText) findViewById(R.id.firstName);
        editLastName =(EditText) findViewById(R.id.lastName);



        showPass=(ImageView)findViewById(R.id.imageViewPassword);
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPass.getTransformationMethod() == null) {
                    editPass.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    editPass.setTransformationMethod(null);
                }

            }
        });

        TextView login= (TextView) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home_page = new Intent(RegisterNow.this,Login.class);
                startActivity(home_page);


            }

        });
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // Login.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editUserEmail.getText().toString();
                UserID = editUserEmail.getText().toString();
                firstName= editFirstName.getText().toString();
                lastName= editLastName.getText().toString();
                //Id =editUserEmail.getText().toString();
                if (id.equals("") || id.equals(" ") || id.equals(null)||firstName.equals("") || firstName.equals(" ") || firstName.equals(null)||lastName.equals("") || lastName.equals(" ") || lastName.equals(null)) {
                    Toast.makeText(getApplicationContext(), "Enter required details First", Toast.LENGTH_SHORT).show();
                }
                else if (!isEmailValid(UserID))
                {
                    Toast.makeText(getApplicationContext(), "Enter Valid email id First", Toast.LENGTH_SHORT).show();

                }
                else if (!checkID(UserID)) {
                    Password = editPass.getText().toString();

                    if (Password.equals("") || Password.equals(" ") || Password.equals(null) || UserID.equals("") || UserID.equals(" ") || UserID.equals(null)) {
                        Toast.makeText(getApplicationContext(), "Enter Password First", Toast.LENGTH_SHORT).show();
                    } else {
                        if(Password.length()<5)
                            {
                                Toast.makeText(getApplicationContext(), "Password should contain atleast 5 characters", Toast.LENGTH_SHORT).show();
                            }
                            else {



                                try {

                                    //receiving result from signup class.
                                    String result = new Signup().execute(UserID, Password).get();
                                    if (result != null) {

                                        JSONObject jsonObject = new JSONObject(result);
                                        String response = (String) jsonObject.getString("result");

                                        if (response.equals(SUCCESS_MSG)) {
                                            Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
                                            Intent initializer = new Intent(RegisterNow.this, CreateuserProfile.class);

                                            startActivity(initializer);
                                        } else if (response.equals(CONNECTION_FAILED_MSG)) {
                                            Toast.makeText(getApplicationContext(), "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                              /*  Intent initializer = new Intent(Register_Temp.this, CreateuserProfile.class);

                                startActivity(initializer);*/
                              String s="";
                            }

                    }



                }
                else{
                    Toast.makeText(getApplicationContext(), "UserId is already taken", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
    public Boolean checkID(String Id) {

        try {

            ArrayList<String> ids = new getData().execute().get();
            Toast.makeText(getApplicationContext(), "id:" + String.valueOf(ids.size()), Toast.LENGTH_SHORT).show();
            if (ids != null) {
                for (int i = 0; i < ids.size(); i++) {

                    String id = ids.get(i);
                    Log.d("iddd",id);
                    if (id.equals(UserID)) {
                        Log.d("iddd",id);
                        Toast.makeText(getApplicationContext(), "id:" + id, Toast.LENGTH_SHORT).show();
                        return true;
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
            // getting JSON string from URL
            String url = urlGetEmail;
            // url = "http://travel-soiled-movem.000webhostapp.com/getID.php";




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
                        Log.d("c.d",id);

                        // String pass = c.getString("pass");
                        idData.add(id);
                        //   passData.add(pass);

                    }
                } else {

                    Log.i("MainActivity", "result = failed");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return idData;
        }


    }
    public class Signup extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            BufferedReader inBuffer = null;
            String result = "fail";

            String id = params[0];
            String pass = params[1];


            //sending POST request.
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(urlInsertUser);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("ID", id));
                postParameters.add(new BasicNameValuePair("Password", pass));


                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);

                request.setEntity(formEntity);
                //executing request and storing result.
                HttpResponse httpResponse = httpClient.execute(request);

                //translating into input stream
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream content = httpEntity.getContent();

                //reading from the buffer.
                BufferedReader reader = new BufferedReader(new InputStreamReader(content, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;

                //storing into string.
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
//                    Log.i("Test", line);
                }
                result = sb.toString();
            } catch (Exception e) {
                // Do something about exceptions
                result = e.getMessage();
                e.printStackTrace();
            } finally {
                if (inBuffer != null) {
                    try {
                        inBuffer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }



}
