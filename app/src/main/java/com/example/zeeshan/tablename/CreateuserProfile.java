package com.example.zeeshan.tablename;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class CreateuserProfile extends AppCompatActivity {
    private static String urlInsertProfile = "http://tezkamayi.tk/InsertUserProfile.php";
    private static final String SUCCESS_MSG = "success";
    private static final String CONNECTION_FAILED_MSG = "connection";
    JSONParser jParser = new JSONParser();
    RadioGroup genderRadio_group;
    RadioButton selectedRadioButton;
    EditText Name,Age,Height,Weight,Bmi;
    int height = 0, weight = 0, age = 0,bmi=0,bee=0,acivityLevelPosition=0;
    RelativeLayout saveButton;
    String activityLevel;
    Spinner SPINNER;


    private final int requestCode = 20;
    String CONFORMEDNAME;
    static String name,beeP,bodyTypeP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createprofile);


        //Getting Value from Previous Activity
/*

        final Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        CONFORMEDNAME=bundle.getString("Name");

*/

        //biding to UI
        genderRadio_group= (RadioGroup)findViewById(R.id.gendeRradio_group);
        Name=(EditText)findViewById(R.id.nameEditText);
        name= RegisterNow.firstName+" "+RegisterNow.lastName;
        Name.setText(name);
        SPINNER=(Spinner)findViewById(R.id.spinnerActivityLevel);

    SPINNER.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long
                    id)
            {
                // Your code here

               // On selecting a spinner item
                acivityLevelPosition=position;
               activityLevel = adapterView.getItemAtPosition(position).toString();
                Log.i(activityLevel, "activityLevel: "+activityLevel);
             //   Toast.makeText(CreateuserProfile.this, "spinner "+activityLevel, Toast
               //     .LENGTH_SHORT)
                 //     .show();

          }

          public void onNothingSelected(AdapterView<?> adapterView) {
               return;
         }
   });
        ImageView cameraImage=(ImageView) findViewById(R.id.imageView2);
        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Download().execute("test");
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(photoCaptureIntent, requestCode);
                // Toast.makeText(v.getContext(),urlResponse,Toast.LENGTH_SHORT).show();

            }
        });
        saveButton=(RelativeLayout)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Age = (EditText) findViewById(R.id.ageEditText);
                Height = (EditText) findViewById(R.id.heightEditText);
                Weight = (EditText) findViewById(R.id.weightEditText);
                Bmi = (EditText) findViewById(R.id.bmiEditText);

                // get selected radio button from radioGroup
                int selectedRadioButtonId = genderRadio_group.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonId);


                String gender = selectedRadioButton.getText().toString();


                //spinner


                // Toast.makeText(getApplicationContext(), "Radio Button  " + gender, Toast.LENGTH_LONG)
                //     .show();

                //String ageString=Age.getText().toString();
                //Toast.makeText(getApplicationContext()," "+ageString,Toast.LENGTH_SHORT).show();

                // female 2131558565
                boolean ageFlag = false, heighthFlag = false, weightFlag = false;

                String ageS = Age.getText().toString();
                if (TextUtils.isEmpty(ageS)) {
                    Age.setError("Enter Age First");
                    Age.requestFocus();
                    return;
                }

                String heightS = Height.getText().toString();
                if (TextUtils.isEmpty(heightS)) {
                    Height.setError("Enter Height First");
                    Height.requestFocus();
                    return;
                }
                String weightS = Weight.getText().toString();
                if (TextUtils.isEmpty(weightS)) {
                    Weight.setError("Enter Weight First");
                    Weight.requestFocus();
                    return;
                }


                //this is the result from camera
                //String bmiS=Bmi.getText().toString();


                height = Integer.parseInt(heightS);
                age = Integer.parseInt(ageS);
                weight = Integer.parseInt(weightS);
                //bmi = Integer.parseInt(bmiS);
                //  Toast.makeText(getApplicationContext(),
                // "Enter height betwee " + weight +age + height
                // + String.valueOf(heightvalidater(height)
                // +String.valueOf(weightvalidater(weight))
                // +String.valueOf(agevalidater(age))), Toast.LENGTH_SHORT).show();

                if (!heightvalidater(height)) {
                    Toast.makeText(getApplicationContext(), "Enter height between 55cm & 246cm " + heightS + height + String.valueOf(heightvalidater(height)), Toast.LENGTH_SHORT).show();
                    Height.setError("Enter height between 55cm & 246cm ");
                    Height.requestFocus();
                    return;

                } else if (!agevalidater(age)) {
                    Toast.makeText(getApplicationContext(), "Enter age between 6 TO 100 YEARS", Toast.LENGTH_SHORT).show();
                    Age.setError("Enter age between 6 & 100 Years");
                    Age.requestFocus();
                    return;


                } else if (!weightvalidater(weight)) {
                    Toast.makeText(getApplicationContext(), "Enter weight between 10kg & 250kg ", Toast.LENGTH_SHORT).show();
                    Weight.setError("Enter weight between 10kg & 250kg ");
                    Weight.requestFocus();
                    return;


                } else {
                    bmi = bmicalculator(height, weight);
                    bee = beecalculator(height, weight, age, gender);
              //      beeP = String.valueOf(beecalculator(height, weight, age, gender));
                    bodyTypeP = String.valueOf(bodytype(bmi));

                    Intent intent = new Intent(getApplicationContext(), CompleteProfile.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", Name.getText().toString());
                    bundle.putString("BodyType", bodyTypeP);
                    bundle.putInt("Height", height);
                    bundle.putInt("Age", age);
                    bundle.putInt("Weight", weight);
                    bundle.putInt("BMI", bmi);
                    bundle.putInt("BEE", bee);



                    intent.putExtras(bundle);
                    startActivity(intent);

//                    try {
//
//                        //receiving result from signup class.
//                        String result = new InsertProfile().execute(Name.getText().toString(), heightS, weightS, ageS, gender, bmiS).get();
//
//                        if (result != null) {
//                            // Toast.makeText(getApplicationContext(), "wait:"+beeP+selectedRadioButtonId, Toast.LENGTH_SHORT).show();
//
//                            JSONObject jsonObject = new JSONObject(result);
//                            String response = (String) jsonObject.getString("result");
//                            Toast.makeText(getApplicationContext(), "User Profile is Created:", Toast.LENGTH_SHORT).show();
//                            bundle = new Bundle();
//                            bundle.putString("Name", Name.getText().toString());
//                            bundle.putInt("Height", height);
//                            bundle.putInt("Weight", weight);
//                            bundle.putInt("Age", age);
//                            bundle.putString("GenderText", gender);
//                            bundle.putInt("Gender", selectedRadioButtonId);
//                            bundle.putInt("BMI", bmi);
//                            bundle.putString("activity", activityLevel);
//                            intent.putExtras(bundle);
//                            startActivity(intent);
//                            if (response.equals(SUCCESS_MSG) || response.equals("1")) {
//                                Toast.makeText(getApplicationContext(), "User Profile is Created:", Toast.LENGTH_SHORT).show();
//                                bundle = new Bundle();
//                                bundle.putString("Name", Name.getText().toString());
//                                bundle.putInt("Height", height);
//                                bundle.putInt("Weight", weight);
//                                bundle.putInt("Age", age);
//                                bundle.putString("GenderText", gender);
//                                bundle.putInt("Gender", selectedRadioButtonId);
//                                bundle.putInt("BMI", bmi);
//                                bundle.putString("activity", activityLevel);
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//                            } else if (response.equals(CONNECTION_FAILED_MSG) || response.equals("0")) {
//                                Toast.makeText(getApplicationContext(), "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                }



                }
        });

        //int age=Integer.parseInt(Age.getText().toString());
        // agevalidater(age);
    }

    private boolean agevalidater(int age){
        if(age>=6 && age <=100){
            return  true;
        }
        else
        {
            return false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
    //use bitmap
            HttpURLConnection c = null;
            OutputStream os = null;

            InputStream in;
            OutputStream ot;
            URL url;
            try {


            } //catch (MalformedURLException e)
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean heightvalidater(int height) {

        if (height >= 55 && height <= 246) {

            //55cm is smallest guy in worls
            // 246 cm tallest guy in world
            //53.34 cm smallest female
            //233cm maximum height

            return true;

        } else {
            return false;
        }
    }

    private boolean weightvalidater(int weight) {

        if (weight >= 10 && weight <= 250) {

            //55cm is smallest guy in worls
            //246 cm tallest guy in world
            //53.34 cm smallest female
            //233cm maximum height

            return true;

        } else {
            return false;
        }
    }



    public class InsertProfile extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            BufferedReader inBuffer = null;
            String result = "fail";

            String nameP = params[0];
            String ageP = params[3];
            String heightP = params[1];
            String weightP = params[2];
            String genderP = params[4];

            String bmiP = params[5];

            //sending POST request.
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(urlInsertProfile);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("UserName", nameP));
                postParameters.add(new BasicNameValuePair("Email", RegisterNow.UserID));
                postParameters.add(new BasicNameValuePair("Age", ageP));
                postParameters.add(new BasicNameValuePair("Height", heightP));
                postParameters.add(new BasicNameValuePair("Weight", weightP));
                postParameters.add(new BasicNameValuePair("Gender", genderP));
                postParameters.add(new BasicNameValuePair("bmi",bmiP ));
                postParameters.add(new BasicNameValuePair("bee",beeP ));
                postParameters.add(new BasicNameValuePair("BodyType",bodyTypeP ));
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


    public  int bmicalculator(int height,int weight){

        float BMI=0;
        float heightinmeter= (float) (height/100.0);
        //BMI = (Weight in Kilograms / (Height in Meters x Height in Meters))
        BMI=(weight/(heightinmeter*heightinmeter));

        return (int) Math.round(BMI);
    }


    //http://www.globalrph.com/harris-benedict-equation.htm
    public int beecalculator(int height,int weight,int age,String genderText){

        double BEE=0;

        double ageFloat;
        double weightFloat;


        // calculation for men (metric)
        // BEE = 66 + ( 13.7 x weight in kg ) + ( 5 x height in cm ) - ( 6.8 x age in years )
        if(genderText.equals("Male")){
            ageFloat=(float)(age*6.8);
            weightFloat=(float)(weight*13.7);
            height=height*5;
            BEE=(float)(66+ weightFloat+height-ageFloat);
        }

//BMR calculation for women (metric)
// BMR = 655+ ( 9.6 x weight in kg ) + ( 1.85 x height in cm ) - ( 4.7x age in years )
        if(genderText.equals("Female")){

            ageFloat=(float)(age*4.7);
            weightFloat=(float)(weight*9.6);
            float heightfloat=(float) (height*1.8);
            BEE=(float)(665+ weightFloat+heightfloat-ageFloat);
        }
//
//<item>Sedentary</item>
//        <item>Lightly active</item>
//        <item>Moderately active</item>
//        <item>Very active</item>
//        <item>Extra active</item>

        if(acivityLevelPosition==1)
            BEE=BEE*1.2;
        else if(acivityLevelPosition==2)
            BEE=BEE*1.375;
        else if(acivityLevelPosition==3)
            BEE=BEE*1.55;
        else if(acivityLevelPosition==4)
            BEE=BEE*1.7;
        else if(acivityLevelPosition==5)
            BEE=BEE*1.9;

        // calculation for men (metric)
        // BEE = 66 + ( 13.7 x weight in kg ) + ( 5 x height in cm ) - ( 6.8 x age in years )


        //BMR calculation for women (metric)
        //BMR = 655+ ( 9.6 x weight in kg ) + ( 1.85 x height in cm ) - ( 4.7x age in years )

        return (int) Math.round(BEE);

    }

    public String bodytype(int BMI){

        String bodytype="";

        if(BMI<16)
            bodytype="Severe Thinness";

        else if(BMI>=16 && BMI <19)
            bodytype="Mild  Thinness";
        else if(BMI>=19 && BMI <26)
            bodytype="Normal";
        else if(BMI>26)
            bodytype="Overweight";
        return bodytype;
    }

}
