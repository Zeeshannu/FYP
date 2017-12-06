package com.example.zeeshan.tablename;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeeshan.tablename.Receiver.NetworkStateChangeReceiver;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterNow extends AppCompatActivity {
    JSONArray person = null;
    boolean SIGNUP_FLAG=false,EMAIL_EXISTS_FLAG=false;

     private static String urlInsertUser = "http://tezkamayi.tk/Insert_User.php";
    private static final String SUCCESS_MSG = "success";
    private static final String CONNECTION_FAILED_MSG = "connection";
    JSONParser jParser = new JSONParser();
    EditText editFirstName,editLastName,editUserEmail, editPass;


    ImageView showPass;

    static final String TAG="Regester";
    static String firstName,lastName,UserID,Password;
    private GoogleApiClient client;


    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;



    RelativeLayout register;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_now);

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);


        linearLayout= (LinearLayout) findViewById(R.id.linearlayout);

        register = (RelativeLayout) findViewById(R.id.btnRegister);


        editUserEmail = (EditText) findViewById(R.id.emailEditor);
        editPass = (EditText) findViewById(R.id.passwordEditor);
        editFirstName = (EditText) findViewById(R.id.firstName);
        editLastName = (EditText) findViewById(R.id.lastName);





        showPass = (ImageView) findViewById(R.id.imageViewPassword);
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

        TextView login = (TextView) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home_page = new Intent(RegisterNow.this, Login.class);
                startActivity(home_page);


            }

        });
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // Login.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editUserEmail.getText().toString().trim();
                UserID = editUserEmail.getText().toString().trim();
                firstName = editFirstName.getText().toString().trim();
                lastName = editLastName.getText().toString().trim();
                String password=editPass.getText().toString().trim();

                if (TextUtils.isEmpty(firstName)) {
                    editFirstName.setError("Enter First Name");
                    editFirstName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(lastName)) {
                    editLastName.setError("Enter Last Name");
                    editLastName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(id)) {
                    editUserEmail.setError("Enter Email First");
                    editUserEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(editPass.getText().toString().trim())) {
                    editPass.setError("Enter Password ");
                    editPass.requestFocus();
                    return;
                }
                if(password.length()<5)
                {
                        editPass.setError(" Minimum Password Length 6");
                        editPass.requestFocus();
                        return;
                }
                if (!isEmailValid(UserID)) {
                    editUserEmail.setError("Enter Valid Email");
                    editUserEmail.requestFocus();
                    return;
                }

                backgroundTaskEmailExist EmailCheck=new backgroundTaskEmailExist();
                try {
                    EmailCheck.execute(UserID).get();
                    if(EMAIL_EXISTS_FLAG){
                        editUserEmail.setError("Email Already Exists");
                        editUserEmail.requestFocus();
                        return;
                    }else if(!EMAIL_EXISTS_FLAG ){

                        backgroundTaskSignUp SignUp=new backgroundTaskSignUp();
                        SignUp.execute(firstName,lastName,UserID,password).get();

                        if(!SIGNUP_FLAG )
                        {
                            Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
                            Intent initializer = new Intent(RegisterNow.this, CreateuserProfile.class);
                            startActivity(initializer);
                        }
                        else
                            Toast.makeText(RegisterNow.this, "Failed to SignUp", Toast.LENGTH_SHORT)
                                    .show();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }



            }
        });

    }



////                    else
////                    {
////                        Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
////                        Intent initializer = new Intent(RegisterNow.this, CreateuserProfile.class);
////                        startActivity(initializer);
////
//////                        try {
//////                                    //receiving result from signup class.
//////                                    String result = new Signup().execute(UserID, Password).get();
//////                                    if (result != null) {
//////
//////                                        JSONObject jsonObject = new JSONObject(result);
//////                                        String response = (String) jsonObject.getString("result");
//////
//////                                        if (response.equals(SUCCESS_MSG)) {
//////                                            Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
//////                                            Intent initializer = new Intent(RegisterNow.this, CreateuserProfile.class);
//////                                            startActivity(initializer);
//////                                        } else if (response.equals(CONNECTION_FAILED_MSG)) {
//////                                            Toast.makeText(getApplicationContext(), "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
//////                                        } else {
//////                                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//////                                        }
//////
//////                                    }
//////                                }
//////                                catch (InterruptedException e) {
//////                                    e.printStackTrace();
//////                                } catch (ExecutionException e) {
//////                                    e.printStackTrace();
//////                                } catch (JSONException e) {
//////                                    e.printStackTrace();
//////                                }
//////
////
////                              /*  Intent initializer = new Intent(Register_Temp.this, CreateuserProfile.class);
////
////                                startActivity(initializer);*/
////                              String s="";
////
////                    }
////                }
////                else{
////                    Toast.makeText(getApplicationContext(), "UserId is already taken", Toast.LENGTH_SHORT).show();
////                }
////            }




    //email pattern matcher
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


//     class Signup extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            BufferedReader inBuffer = null;
//            String result = "fail";
//
//            String id = params[0];
//            String pass = params[1];
//
//
//            //sending POST request.
//            try {
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpPost request = new HttpPost(urlInsertUser);
//                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
//                postParameters.add(new BasicNameValuePair("ID", id));
//                postParameters.add(new BasicNameValuePair("Password", pass));
//
//
//                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
//                        postParameters);
//
//                request.setEntity(formEntity);
//                //executing request and storing result.
//                HttpResponse httpResponse = httpClient.execute(request);
//
//                //translating into input stream
//                HttpEntity httpEntity = httpResponse.getEntity();
//                InputStream content = httpEntity.getContent();
//
//                //reading from the buffer.
//                BufferedReader reader = new BufferedReader(new InputStreamReader(content, "iso-8859-1"), 8);
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//
//                //storing into string.
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
////                    Log.i("Test", line);
//                }
//                result = sb.toString();
//            } catch (Exception e) {
//                // Do something about exceptions
//                result = e.getMessage();
//                e.printStackTrace();
//            } finally {
//                if (inBuffer != null) {
//                    try {
//                        inBuffer.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//        }
//    }
//
//
//

// 11/22/2017*/
    class  backgroundTaskEmailExist extends AsyncTask<String ,Void,Void> {
//

//
        String user_email,result_EmailExist;
        HttpURLConnection httpURLConnection;
        URL url = null;

        @Override
        protected Void doInBackground(String ... Args) {


//user_email==that user enterted  for regestration
            user_email=Args[0];

            try {

                // Enter URL address where your php file resides
                url = new URL(Config.urlGetEmail);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {


                // Setup HttpURLConnection class to send and receive data from php and mysql
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(READ_TIMEOUT);
                httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                httpURLConnection.setRequestMethod("POST");

                // setDoInput and setDoOutput to true as we send and recieve data
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("email", user_email);
                String query = builder.build().getEncodedQuery();


                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                httpURLConnection.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                //return e1.toString();
            }

            try {

                int response_code = httpURLConnection.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    String serverResult="";
                    try {
                        JSONObject jsonObject=new JSONObject(String.valueOf(result));
                         serverResult= String.valueOf(jsonObject.get("result"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Pass data to onPostExecute method


                    if (serverResult.equals("false")||(serverResult.equals(""))) {
                        EMAIL_EXISTS_FLAG=false;
                    } else
                        EMAIL_EXISTS_FLAG= true;
                    //   return (result.toString());

                }

            } catch (IOException e) {
                e.printStackTrace();
                //e.toString();
            } finally {
                httpURLConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d(TAG, "onPostExecute: Result "+result);
        }
    }






    class  backgroundTaskSignUp extends AsyncTask<String, Void, Void> {
        //
        ProgressDialog pdLoading = new ProgressDialog(RegisterNow.this);
        //
        String user_email,firstName,lastName,password;
        HttpURLConnection httpURLConnection;
        URL url = null;
        @Override
        protected void onPreExecute() {



            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected Void doInBackground(String ... Args) {


//user_email==that user enterted  for regestration

            firstName = Args[0];
            lastName = Args[1];
            user_email = Args[2];
            password = Args[3];

            try {

                // Enter URL address where your php file resides
                url = new URL(Config.urlSignUp);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                //return false;
            }
            try {


                // Setup HttpURLConnection class to send and receive data from php and mysql
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(READ_TIMEOUT);
                httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                httpURLConnection.setRequestMethod("POST");

                // setDoInput and setDoOutput to true as we send and recieve data
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("id", user_email)
                        .appendQueryParameter("lastname", lastName)
                        .appendQueryParameter("firstname", firstName)
                        .appendQueryParameter("password", password);
                String query = builder.build().getEncodedQuery();


                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                httpURLConnection.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                //   return false;
                //return e1.toString();
            }

            try {

                int response_code = httpURLConnection.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    String serverResult = "";
                    try {
                        JSONObject jsonObject = new JSONObject(String.valueOf(result));
                        serverResult = String.valueOf(jsonObject.get("result"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Pass data to onPostExecute method


                    if (serverResult.equals("false") || (serverResult.equals(""))) {
                        SIGNUP_FLAG = false;
                    } else {
                        SIGNUP_FLAG = true;
                    }

                    //   return (result.toString());

                } else {
                    //return false;
                    //("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                // return false;
                //e.toString();
            } finally {
                httpURLConnection.disconnect();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {

            pdLoading.cancel();
            //Toast.makeText(RegisterNow.this, "resut "+result, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onPostExecute: Result "+result);
        }
    }





}

