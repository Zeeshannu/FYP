package com.example.zeeshan.tablename;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.concurrent.ExecutionException;

public class DinnerFood extends AppCompatActivity {

    Spinner SPINNER;
    String JSON_STRING, searchQuery,tablenam;

    //getting the detail of course->parameters
    Double Protein=0.0,Fat=0.0,Carbohydrate=0.0,Calories=0.0;
    String JSON_STRING_FoodDetail;
    Boolean BACKGROUND_RESULT_FoodDetail;


    Boolean BACKGROUND_RESULT;

    JSONObject jsonObject,jsonObjectexact;
    JSONArray jsonArray;
    TextView textView;
    TableDataAdapter tableDataAdapter;
    ListView listView;



    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;


    public ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_food);


        SPINNER=(Spinner)findViewById(R.id.spinnerfoodCategory);


        //constructor of adapter
        tableDataAdapter=new TableDataAdapter(this, R.layout.activity_list_row);

        listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(tableDataAdapter);




        SPINNER.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long
                    id)
            {
                // Your code here

                // On selecting a spinner item
                tablenam = adapterView.getItemAtPosition(position).toString();

                if(tablenam.equals(("Food Categroy"))){

                }
                // Showing selected spinner item
                else
                {

                    Session.dinnerCategory=tablenam;
                    BackgroundTaskGetJson bk = new BackgroundTaskGetJson();
                    bk.execute(tablenam);
                }

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



// ListView on item selected listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long ld) {

                TableData t=(TableData)tableDataAdapter.getItem(position);




                Intent i = new Intent();
                i.setClass(getApplicationContext(), FoodDetails.class);
                // parameters
                i.putExtra("position", String.valueOf(position + 1));

                 i.putExtra("foodname", t.getFoodname());
                Session.cource_selection="dinner";

                i.putExtra("Cource", Session.cource_selection);


                Session.dinnerSelection=t.getFoodname();

                if(Session.dinnerSC_0.getCategory().equals("")|| Session
                        .dinnerSC_0.getSelection().equals("")&& Session.dinnerrcounter==0)
                {
                    Session.dinnerSC_0.setSelection(Session.dinnerSelection);
                    Session.dinnerSC_0.setCategory(Session.dinnerCategory);


                    BackgroundTaskGetJson_FoodDetail bk = new BackgroundTaskGetJson_FoodDetail();
                    try {
                        //food category refers  food table
                        boolean flag_breakfast = bk.execute(Session.dinnerSC_0.getCategory(),
                                Session.dinnerSC_0.getSelection())
                                .get();
                        if (flag_breakfast) {

                            String   JSON_Breakfast = JSON_STRING_FoodDetail;
                            Session.foodDinner_0 = getdiet(JSON_Breakfast);
                            Fat= Session.foodDinner_0.getFat();
                            Calories= Session.foodDinner_0.getCalories();
                            Protein= Session.foodDinner_0.getProtein();
                            Carbohydrate= Session.foodDinner_0.getCarbohydrate();


                            Toast.makeText(DinnerFood.this, ""+ Session.foodDinner_0.getCalories(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }else if(Session.dinnerSC_1.getCategory().equals("")|| Session
                        .dinnerSC_1.getSelection().equals("")&& Session.dinnerrcounter==1)
                {
                    Session.dinnerSC_1.setSelection(Session.dinnerSelection);
                    Session.dinnerSC_1.setCategory(Session.dinnerCategory);


                    BackgroundTaskGetJson_FoodDetail bk = new BackgroundTaskGetJson_FoodDetail();
                    try {
                        //food category refers  food table
                        boolean flag_breakfast = bk.execute(Session.dinnerSC_1.getCategory(),
                                Session.dinnerSC_1.getSelection())
                                .get();
                        if (flag_breakfast) {

                            String   JSON_Breakfast = JSON_STRING_FoodDetail;
                            Session.foodDinner_1 = getdiet(JSON_Breakfast);
                            Fat= Session.foodDinner_1.getFat();
                            Calories= Session.foodDinner_1.getCalories();
                            Protein= Session.foodDinner_1.getProtein();
                            Carbohydrate= Session.foodDinner_1.getCarbohydrate();


                            Toast.makeText(DinnerFood.this, ""+ Session.foodDinner_1.getCalories(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }else if(Session.dinnerSC_2.getCategory().equals("")|| Session
                        .dinnerSC_2.getSelection().equals("")&& Session.dinnerrcounter==2)
                {
                    Session.dinnerSC_2.setSelection(Session.dinnerSelection);
                    Session.dinnerSC_2.setCategory(Session.dinnerCategory);



                    BackgroundTaskGetJson_FoodDetail bk = new BackgroundTaskGetJson_FoodDetail();
                    try {
                        //food category refers  food table
                        boolean flag_breakfast = bk.execute(Session.dinnerSC_2.getCategory(),
                                Session.dinnerSC_2.getSelection())
                                .get();
                        if (flag_breakfast) {

                            String   JSON_Breakfast = JSON_STRING_FoodDetail;
                            Session.foodDinner_2 = getdiet(JSON_Breakfast);
                            Fat= Session.foodDinner_2.getFat();
                            Calories= Session.foodDinner_2.getCalories();
                            Protein= Session.foodDinner_2.getProtein();
                            Carbohydrate= Session.foodDinner_2.getCarbohydrate();


                            Toast.makeText(DinnerFood.this, ""+ Session.foodDinner_2.getCalories(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                }else if(Session.dinnerSC_3.getCategory().equals("")|| Session
                        .dinnerSC_3.getSelection().equals("")&& Session.dinnerrcounter==3)
                {
                    Session.dinnerSC_3.setSelection(Session.dinnerSelection);
                    Session.dinnerSC_3.setCategory(Session.dinnerCategory);

                    BackgroundTaskGetJson_FoodDetail bk = new BackgroundTaskGetJson_FoodDetail();
                    try {
                        //food category refers  food table
                        boolean flag_breakfast = bk.execute(Session.dinnerSC_3.getCategory(),
                                Session.dinnerSC_3.getSelection())
                                .get();
                        if (flag_breakfast) {

                            String   JSON_Breakfast = JSON_STRING_FoodDetail;
                            Session.foodDinner_3 = getdiet(JSON_Breakfast);
                            Fat= Session.foodDinner_3.getFat();
                            Calories= Session.foodDinner_3.getCalories();
                            Protein= Session.foodDinner_3.getProtein();
                            Carbohydrate= Session.foodDinner_3.getCarbohydrate();


                            Toast.makeText(DinnerFood.this, ""+ Session.foodDinner_3.getCalories(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }else if(Session.dinnerSC_4.getCategory().equals("")|| Session
                        .dinnerSC_4.getSelection().equals("")&& Session.dinnerrcounter==4)
                {
                    Session.dinnerSC_4.setSelection(Session.dinnerSelection);
                    Session.dinnerSC_4.setCategory(Session.dinnerCategory);


                    BackgroundTaskGetJson_FoodDetail bk = new BackgroundTaskGetJson_FoodDetail();
                    try {
                        //food category refers  food table
                        boolean flag_breakfast = bk.execute(Session.dinnerSC_4.getCategory(),
                                Session.dinnerSC_4.getSelection())
                                .get();
                        if (flag_breakfast) {

                            String   JSON_Breakfast = JSON_STRING_FoodDetail;
                            Session.foodDinner_4 = getdiet(JSON_Breakfast);
                            Fat= Session.foodDinner_4.getFat();
                            Calories= Session.foodDinner_4.getCalories();
                            Protein= Session.foodDinner_4.getProtein();
                            Carbohydrate= Session.foodDinner_4.getCarbohydrate();


                            Toast.makeText(DinnerFood.this, ""+ Session.foodDinner_4.getCalories(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }



                i.putExtra("Protein", Protein.toString());
                i.putExtra("Fat", Fat.toString());
                i.putExtra("Calories", Calories.toString());
                i.putExtra("Carbohydrate", Carbohydrate.toString());


//                startActivity(i);

                if(Session.dinnerrcounter<5)
                    startActivity(i);
                else
                    Toast.makeText(DinnerFood.this, "You have selected the maximum item in " +
                            "this Cource", Toast
                            .LENGTH_SHORT)
                            .show();

            }
        });



    }




    class  BackgroundTaskGetJson extends AsyncTask<String ,Void,Boolean> {

        ProgressDialog pdLoading = new ProgressDialog(DinnerFood.this);

        HttpURLConnection httpURLConnection;
        URL url = null;
        String getJSONdata_url;

        @Override
        protected void onPreExecute() {



            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected Boolean doInBackground(String ... Args) {


//           searchQuery="baby foods";
            searchQuery=Args[0];



            try {

                getJSONdata_url = "http://"+ Session.IPV4+"/test_getTable.php";
                // Enter URL address where your php file resides
                url = new URL(getJSONdata_url);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
                // return e.toString();
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

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("table", searchQuery);
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
                return false;
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

                    // Pass data to onPostExecute method

                    JSON_STRING = result.toString().trim();
                    if (JSON_STRING.equals("Could not find data")||(JSON_STRING.equals(""))) {
                        return false;
                    } else
                        return true;
                    //   return (result.toString());

                } else {
                    return false;
                    //("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
                //e.toString();
            } finally {
                httpURLConnection.disconnect();
            }

        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {

            //this method will be running on UI thread
            pdLoading.cancel();
            BACKGROUND_RESULT=result;
            Toast.makeText(getApplicationContext(), "Result in Post "+result, Toast.LENGTH_SHORT).show();

            if(!BACKGROUND_RESULT){
                Toast.makeText(getApplicationContext(), " Get the JSON_STRING first:...", Toast
                        .LENGTH_SHORT).show();
            }else {
              /*  Toast.makeText(MainActivity.this, " wow www www Successfuly get JSON_STRING...",
                        Toast.LENGTH_SHORT)
                        .show();
*/
                setList();
            }

        }
    }





    public  void setList(){

        tableDataAdapter=new TableDataAdapter(this, R.layout.activity_list_row);
        listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(tableDataAdapter);

        try {
            jsonObject=new JSONObject(JSON_STRING);
            jsonArray=jsonObject.getJSONArray("table");


            int count=0;
            String food_name,foodURL;
            StringBuilder foodnameSB=new StringBuilder();
            while (count<jsonArray.length()) {

                jsonObjectexact = jsonArray.getJSONObject(count);
                food_name = jsonObjectexact.getString("food_name");

                foodURL=jsonObjectexact.getString("url");


                foodURL=foodURL.replaceAll("\\\\","");
                //Toast.makeText(getApplicationContext(),"Url : "+foodURL,Toast.LENGTH_LONG).show();
                TableData row=new TableData(food_name,foodURL);


                tableDataAdapter.add(row);

                foodnameSB.append(food_name);
                count++;

            }
            //  Toast.makeText(this, "Result : "+foodnameSB, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }










    class  BackgroundTaskGetJson_FoodDetail extends AsyncTask<String ,Void,Boolean> {


        ProgressDialog pdLoading = new ProgressDialog(DinnerFood.this);

        HttpURLConnection httpURLConnection;
        URL url = null;
        String getJSONdata_url;
        @Override
        protected void onPreExecute() {

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected Boolean doInBackground(String ... Args) {


            String tablenam=Args[0];
            String dietname=Args[1];

            try {

                getJSONdata_url = "http://"+ Session.IPV4+"/getDiet.php";
                // Enter URL address where your php file resides
                url = new URL(getJSONdata_url);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
                // return e.toString();
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
                        .appendQueryParameter("table",tablenam)
                        .appendQueryParameter("diet", dietname);
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
                return false;
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

                    // Pass data to onPostExecute method

                    JSON_STRING_FoodDetail="";
                    JSON_STRING_FoodDetail = result.toString().trim();
                    if (JSON_STRING_FoodDetail.equals("Could not find data")||(JSON_STRING_FoodDetail.equals(""))) {
                        return false;
                    } else
                        return true;
                    //   return (result.toString());

                } else {
                    return false;
                    //("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
                //e.toString();
            } finally {
                httpURLConnection.disconnect();
            }

        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {

            //this method will be running on UI thread
            pdLoading.cancel();
            BACKGROUND_RESULT_FoodDetail=result;
            //Toast.makeText(getApplicationContext(), "Result in Post "+result, Toast
            //      .LENGTH_SHORT).show();

            if(!BACKGROUND_RESULT_FoodDetail){
                Toast.makeText(getApplicationContext(), " Get the JSON_STRING_FoodDetail first:...", Toast
                        .LENGTH_SHORT).show();
            }
        }
    }

    public FoodIntegridients getdiet(String jsonString){

        FoodIntegridients food=new FoodIntegridients();

        JSONObject jsonObject,jsonObjectexact;
        JSONArray jsonArray;

        try {
            jsonObject=new JSONObject(jsonString);
            jsonArray=jsonObject.getJSONArray("table");

            int count=0;String food_name,foodURL;
            //     StringBuilder foodnameSB=new StringBuilder();
            while (count<jsonArray.length()) {

                jsonObjectexact = jsonArray.getJSONObject(count);
                food.setCalories(jsonObjectexact.getDouble("calories"));
                food.setCarbohydrate(jsonObjectexact.getDouble("carbohydrate"));
                food.setFat(jsonObjectexact.getDouble("fat")) ;
                food.setProtein(jsonObjectexact.getDouble("protein"));
                foodURL= jsonObjectexact.getString("fooddetail_url");
                foodURL=foodURL.replaceAll("\\\\","");
                food.setFooddetail_url(foodURL);

//                food.setFood_category(tablenam);
//               food.setFoodtitle(dietname);

                count++;
            }
            return  food;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return  food;
    }











}
