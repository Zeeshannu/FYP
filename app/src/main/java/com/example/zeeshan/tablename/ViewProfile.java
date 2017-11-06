package com.example.zeeshan.tablename;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewProfile extends Fragment {
    private static  final String SUCCESS_MSG = "success";
    private static  final String FAIL_MSG = "failed";
    private static  final String CONNECTION_FAILED_MSG = "connection";
    JSONArray person = null;
    JSONArray Data = null;
    JSONParser jParser = new JSONParser();
    private ArrayList<String> idData = new ArrayList<String>();
    private ArrayList<String> passData = new ArrayList<String>();
    String userDataID;


    String name, genderText;
    int age, height, weight, gender, bmi;
    Button SaveButton;
    String usernameDB,bodyTypeDB,heightDB,weightDB,bmiDB,beeDB;
    TextView NameEditText, BodytypeEditText, BEEEditText, BMIEditText, WeightEditText, HeightEditText;

    View v;
    Initializer initializer;

    public void InitializerObject(Initializer i) {
        initializer = i;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_complete_profile, container, false);
        try {

            userDataID=Login.emailID;

            new getData().execute(Login.emailID).get();



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




//getting data from database
        name = "Rabiya Rohail";
        age = 22;
        height = 158;
        weight = 55;
        // gender=bundle.getInt("Gender");
        genderText = "Female";
        bmi = 22;

//binding to the UI
        NameEditText = (TextView) v.findViewById(R.id.nameEditText);
        BEEEditText = (TextView) v.findViewById(R.id.beeEditText);
        WeightEditText = (TextView) v.findViewById(R.id.weightEditText);
        HeightEditText = (TextView) v.findViewById(R.id.heightEditText);
        BodytypeEditText = (TextView) v.findViewById(R.id.bodytypeEditText);
        BMIEditText = (TextView) v.findViewById(R.id.bmiEditText);


        NameEditText.setText(usernameDB);
        HeightEditText.setText(heightDB);
        WeightEditText.setText(weightDB);
        int BMI = bmicalculator(height, weight);

        BMIEditText.setText(bmiDB);
        BEEEditText.setText(beeDB);
        BodytypeEditText.setText(bodyTypeDB);
        return v;

    }
    /*
    parameters for async
        //name,gender,bodytype,aga,height,weight,bmi,bee
        */
    public  int bmicalculator(int height,int weight){

        float BMI=0;
        float heightinmeter= (float) (height/100.0);
        //BMI = (Weight in Kilograms / (Height in Meters x Height in Meters))
        BMI=(weight/(heightinmeter*heightinmeter));

        return (int) Math.round(BMI);
    }


    public int beecalculator(int height,int weight,int age,String genderText){

        double BEE=0;

        double ageFloat;
        double weightFloat;

        // female 2131558565
        // male 2131558564
        //
        if(genderText.equals("Male")){
            ageFloat=(float)(age*6.8);
            weightFloat=(float)(weight*13.7);
            height=height*5;
            BEE=(float)(66+ weightFloat+height-ageFloat);
        }
        if(genderText.equals("Female")){
            ageFloat=(float)(age*4.7);
            weightFloat=(float)(weight*9.6);
            float heightfloat=(float) (height*1.8);
            BEE=(float)(665+ weightFloat+heightfloat-ageFloat);
        }


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





    class getData extends AsyncTask<String, String, ArrayList<String>> {


        protected ArrayList<String> doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Email", userDataID ));

            // getting JSON string from URL
            String url = "http://tezkamayi.tk/Get_UserProfile.php";


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
                        usernameDB=c.getString("UserName");

                        bodyTypeDB=c.getString("BodyType");

                        heightDB=c.getString("Height");

                        weightDB=c.getString("Weight");

                        bmiDB=c.getString("Bmi");

                        beeDB=c.getString("Bee");



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

}



