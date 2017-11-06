package com.example.zeeshan.tablename;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.net.URLEncoder;

import static com.example.zeeshan.tablename.R.id.TV;


public class MainActivity extends AppCompatActivity {

    ListView list;
    String JSON_STRING, searchQuery;
    TextView textView;
    Spinner spinner;
    Boolean BACKGROUND_RESULT;

    Button button,parseButton;


    //after merging

    JSONObject jsonObject;
    JSONObject jsonObjectexact;
    String tablenam;
    JSONArray jsonArray;
    TextView textViewnew;
    TableDataAdapter tableDataAdapter;
    ListView listView;

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;


    //  ArrayList<Actor_getTable> actorList;
    //Actor_getTableAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textView=(TextView) findViewById(TV);
        button=(Button) findViewById(R.id.getdata);
        spinner=(Spinner)findViewById(R.id.spinnerbeverage);
        parseButton=(Button)findViewById(R.id.parseJSON);


        //constructor of adapter
        tableDataAdapter=new TableDataAdapter(this,R.layout.activity_list_row);

        listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(tableDataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long
                    id)
            {
                // Your code here

                // On selecting a spinner item
                tablenam = adapterView.getItemAtPosition(position).toString();

                if(tablenam.equals(("appetizers"))){

                }
                // Showing selected spinner item
                else
                {
                    BackgroundTaskGetJson bk = new BackgroundTaskGetJson();
                    bk.execute(tablenam);
                }

                Toast.makeText(adapterView.getContext(), "Selected: " + tablenam, Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), ""+t.getFoodname(), Toast.LENGTH_SHORT)
                        .show();



                Intent i = new Intent();
                i.setClass(getApplicationContext(), FoodDetails.class);
                // parameters
                i.putExtra("position", String.valueOf(position + 1));

					/* selected item parameters
					 * 1.	Food name
					 * 2.	image
					 */
                i.putExtra("foodname", t.getFoodname());

                startActivity(i);
            }
        });



    }



    public void beverage() {

        int total = 0;
        String beverage = String.valueOf(spinner.getSelectedItem());
        //Toast.makeText(this, "this is selected: "+beverage, Toast.LENGTH_SHORT).show();
    }



    // Every time when you press search button on keypad an Activity is recreated which in turn calls this function
    //@Override
    /*protected void onNewIntent(Intent intent) {
        // Get search query and create object of class AsyncFetch
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (searchView != null) {
                searchView.clearFocus();
            }
            new AsyncFetch(query).execute();

        }
    }*/


    public void getData(View view) {

       // beverage();



      BackgroundTaskGetJson bk = new BackgroundTaskGetJson();
        bk.execute(tablenam);
       //Toast.makeText(this, "Main ", Toast.LENGTH_SHORT).show();




//        Intent intent =new Intent(getApplicationContext(),DietTime.class);
//        startActivity(intent);


        }


      class  BackgroundTask extends AsyncTask<String ,Void,String>{


        String add_info_url;
        @Override
        protected void onPreExecute() {
            add_info_url="http://192.168.1.107/Final/getTable.php";
        }

        @Override
        protected String doInBackground(String... args) {

            String name,email,mobile;

            //// backgroundTask.execute(name,mobile,email);

            name=args[0];
            mobile=args[1];
            email=args[2];
            try {
                URL url=new URL(add_info_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,
                        "UTF-8"));

                String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");

                bufferedWriter.write(data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return  "one Row Inserted...";

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return  "Insertion error"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                //  return  "Insertion error"+e.getMessage();

            }


            return  "Insertion error";

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
           // Toast.makeText(RegesterData.this, " "+result, Toast.LENGTH_LONG).show();
        }
    }




/*
    //Thes  are the final product for my work ....
    //Thank ALlah

    this is 5.34PM
    9/30/2017*/
class  BackgroundTaskGetJson extends AsyncTask<String ,Void,Boolean> {

    ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

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

                getJSONdata_url = "http://"+Session.IPV4+"/test_getTable.php";
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
            Toast.makeText(MainActivity.this, "Result in Post "+result, Toast.LENGTH_SHORT).show();

            if(!BACKGROUND_RESULT){
                Toast.makeText(MainActivity.this, " Get the JSON_STRING first:...", Toast
                        .LENGTH_SHORT).show();
            }else {

                setList();
            }

        }
    }

    public void parseJson(View view){

        if(!BACKGROUND_RESULT){
        Toast.makeText(this, " Get the JSON_STRING first:...", Toast.LENGTH_SHORT).show();
    }else
    {
        Toast.makeText(this, " wow www www Successfuly get JSON_STRING...", Toast.LENGTH_SHORT)
                .show();

        setList();

        }
}

    public  void setList(){

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

              //  foodURL.replaceAll('\/','/')
                foodURL=foodURL.replaceAll("\\\\","");

            //    Toast.makeText(MainActivity.this,"Url : "+foodURL,Toast.LENGTH_LONG).show();
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


}
