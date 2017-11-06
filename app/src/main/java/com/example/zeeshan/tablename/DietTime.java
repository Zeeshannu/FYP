package com.example.zeeshan.tablename;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeeshan.tablename.Model.Processing;

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
import java.util.Timer;
import java.util.TimerTask;


public class DietTime extends AppCompatActivity {


    // FoodselectionCategory breakfastSelection_Cat;
    String JSON_STRING_FoodDetail/* searchQuery,tablenam,dietname*/;

    String /*JSON_Breakfast, */JSON_Lunch, JSON_Dinner;
    Boolean BACKGROUND_RESULT_FoodDetail;

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;


    public  ProgressDialog pDialog;
    Button BREAKFAST, LUNCH, DINNER, GENERATE;
    TextView TVBREAKFAST, TVLUNCH, TVDINNER;
    FoodIntegridients breakFast, lunch, dinner;


    @Override
    protected void onResume() {
        super.onResume();



        TVBREAKFAST.setText("  "+Session.breakfastSC_0.getSelection()+"   "+Session
                .breakfastSC_1.getSelection()+"   "+Session.breakfastSC_2.getSelection()+"   " +
                "   "+Session.breakfastSC_3.getSelection()+"   "+Session
                .breakfastSC_4.getSelection()
                +" ");
        TVLUNCH.setText("  "+Session.lunchSC_0.getSelection()+"   "+Session
                .lunchSC_1.getSelection()+"   "+Session.lunchSC_2.getSelection()+"   " +
                "   "+Session.lunchSC_3.getSelection()+"   "+Session
                .lunchSC_4.getSelection()
                +" ");
        TVDINNER.setText("  "+Session.dinnerSC_0.getSelection()+"   "+Session
                .dinnerSC_1.getSelection()+"   "+Session.dinnerSC_2.getSelection()+"   " +
                "   "+Session.dinnerSC_3.getSelection()+"   "+Session
                .dinnerSC_4.getSelection()
                +" ");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_time);
        BREAKFAST = (Button) findViewById(R.id.BTNbreakfast);
        LUNCH = (Button) findViewById(R.id.BTNlunch);
        DINNER = (Button) findViewById(R.id.BTNdinner);
        GENERATE = (Button) findViewById(R.id.BTNgenerate);
        breakFast = new FoodIntegridients();
        lunch = new FoodIntegridients();
        dinner = new FoodIntegridients();
        TVBREAKFAST = (TextView) findViewById(R.id.TVbreakfast);
        TVLUNCH = (TextView) findViewById(R.id.TVlunch);
        TVDINNER = (TextView) findViewById(R.id.TVdinner);

        //     breakfastSelection_Cat=new FoodselectionCategory();
    }


    public void brakfastFUN(View view) {
        Intent intent = new Intent(getApplicationContext(), BreakfastFood.class);
        startActivity(intent);
    }

    public void lunchFUN(View view) {
        Intent intent = new Intent(getApplicationContext(), LunchFood.class);
        startActivity(intent);
    }

    public void dinnerFUN(View view) {
        Intent intent = new Intent(getApplicationContext(), DinnerFood.class);
        startActivity(intent);
    }


    public void generateFUN(View view) {

        if(Session.breakfastcounter==0&& Session.dinnerrcounter==0&&Session.lunchcounter==0){
            Toast.makeText(this, "Select Diet First", Toast.LENGTH_SHORT).show();
        }
        else{

            final ProgressDialog dialog = new ProgressDialog(DietTime.this);
            dialog.setTitle("Generating Diet Plan ...");
            dialog.setMessage("Please wait.");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();

            long delayInMillis = 10000;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.dismiss();

                    Intent i=new Intent(getApplicationContext(),DisplayDietPlan.class);
                    startActivity(i);
                }
            }, delayInMillis);

//
//        if ((!Session.breakfastSC_0.getSelection().equals("") && !Session.breakfastSC_0.getCategory().equals
//                ("")))
//        {
//
//            Toast.makeText(this, "Session.breakfastSC_0.getCategory()" + Session.breakfastSC_0.getCategory(), Toast
//                    .LENGTH_SHORT).show();
//            Toast.makeText(this, "Session.breakfastSC_0.getCategory()getSelection()" + Session.breakfastSC_0.getSelection(), Toast
//                    .LENGTH_SHORT).show();
//            BackgroundTaskGetJson_FoodDetail bk = new BackgroundTaskGetJson_FoodDetail();
//            try {
//                //food category refers  food table
//                boolean flag_breakfast = bk.execute(Session.breakfastSC_0.getCategory(),
//                        Session.breakfastSC_0.getSelection())
//                        .get();
//                if (flag_breakfast) {
//
//                    String   JSON_Breakfast = JSON_STRING_FoodDetail;
//                    Session.foodBreakFast_0 = getdiet(JSON_Breakfast);
//                    Toast.makeText(this, ""+Session.foodBreakFast_0.getCalories(), Toast.LENGTH_SHORT).show();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            Toast.makeText(this, "You did not select braskfast", Toast.LENGTH_SHORT).show();
//        }

//
//        if((!Session.lunchCategory.equals("")&& !Session.lunchSelection.equals(""))){
//
//            BackgroundTaskGetJson_FoodDetail bk=new BackgroundTaskGetJson_FoodDetail();
//            try {
//                boolean flag_lunch=bk.execute(Session.foodLunch.getFood_category(),Session
//                                .foodLunch.getFoodtitle()
//                        )
//                        .get();
//                if(flag_lunch){
//
//                    JSON_Lunch=JSON_STRING_FoodDetail;
//                    lunch =getdiet(JSON_Lunch);
//
//                            //  JSON_STRING_FoodDetail="";
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//    }else{
//        Toast.makeText(this, "You did not select Lunch", Toast.LENGTH_SHORT).show(); }


//        if((!Session.dinnerCategory.equals("")&& !Session.dinnerSelection.equals("")))
//        {
//
//            BackgroundTaskGetJson_FoodDetail bk=new BackgroundTaskGetJson_FoodDetail();
//            try {
//                boolean flag_dinner=bk.execute(Session.foodDinner.getFood_category(),Session
//                        .foodDinner.getFoodtitle())
//                        .get();
//                if(flag_dinner){
//
//                    JSON_Dinner=JSON_STRING_FoodDetail;
//                    dinner =getdiet(JSON_Dinner);
//                   }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (breakFast.getCalories()!=0.0 ) {
//            Toast.makeText(this, "Brakfast cal in seected diet "+breakFast.getCalories().toString
//                    (), Toast
//                    .LENGTH_SHORT).show();}
//            if (lunch.getCalories()!=0.0) {
//                Toast.makeText(this, "Luch Calories "+lunch.getCalories(), Toast.LENGTH_SHORT).show();
//            }
//            if (dinner.getCalories()!=0.0) {
//                Toast.makeText(this, "Dinner Calories "+dinner.getCalories(), Toast.LENGTH_SHORT).show();
//            }
//
//        pDialog=new ProgressDialog(DietTime.this);
//        pDialog = new ProgressDialog(DietTime.this);
//        pDialog = ProgressDialog.show(getApplicationContext(),null, "Lädt..",true);


            try{
                Toast.makeText(this, "Total breakfast food "+ Session.breakfastcounter, Toast
                        .LENGTH_SHORT)
                        .show();
                Processing p=new Processing();


                if(Session.breakfastcounter==1){

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_0,Session.breakfastOC_0);


                    Session.breakfastOC_0_Gene0=p.generateGenes(Session.breakfastMC.getCalories(),Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene1=p.generateGenes(Session.breakfastMC.getCalories()+10,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene2=p.generateGenes(Session.breakfastMC.getCalories()-10,
                            Session.breakfastOC_0);

//

                }
                else if(Session.breakfastcounter==2){

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_0,Session.breakfastOC_0);
                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_1,Session.breakfastOC_1);


                    Session.breakfastOC_0_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/2,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/2)+10,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/2)-10,
                            Session.breakfastOC_0);

                    Session.breakfastOC_1_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/2,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/2)+10,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/2)-10,
                            Session.breakfastOC_1);


                }

                else if(Session.breakfastcounter==3){

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_0,Session.breakfastOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_1,Session.breakfastOC_1);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_2,Session.breakfastOC_2);



                    Session.breakfastOC_0_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/3,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/3)+10,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/3)-10,
                            Session.breakfastOC_0);

                    Session.breakfastOC_1_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/3,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/3)+10,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/3)-10,
                            Session.breakfastOC_1);

                    Session.breakfastOC_2_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/3,
                            Session.breakfastOC_2);
                    Session.breakfastOC_2_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/3)+10,
                            Session.breakfastOC_2);
                    Session.breakfastOC_2_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/3)-10,
                            Session.breakfastOC_2);


                }


                else if(Session.breakfastcounter==4){

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_0,Session.breakfastOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_1,Session.breakfastOC_1);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_2,Session.breakfastOC_2);


                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_3,Session.breakfastOC_3);



                    Session.breakfastOC_0_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/4,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/4)+10,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/4)-10,
                            Session.breakfastOC_0);

                    Session.breakfastOC_1_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/4,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/4)+10,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/4)-10,
                            Session.breakfastOC_1);

                    Session.breakfastOC_2_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/4,
                            Session.breakfastOC_2);
                    Session.breakfastOC_2_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/4)+10,
                            Session.breakfastOC_2);
                    Session.breakfastOC_2_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/4)-10,
                            Session.breakfastOC_2);

                    Session.breakfastOC_3_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/4,
                            Session.breakfastOC_3);
                    Session.breakfastOC_3_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/4)+10,
                            Session.breakfastOC_3);
                    Session.breakfastOC_3_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/4)-10,
                            Session.breakfastOC_3);

                }
                else if(Session.breakfastcounter==5){

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_0,Session.breakfastOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_1,Session.breakfastOC_1);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_2,Session.breakfastOC_2);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_3,Session.breakfastOC_3);

                    p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_4,Session.breakfastOC_4);


                    Session.breakfastOC_0_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/5,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/5)+10,
                            Session.breakfastOC_0);
                    Session.breakfastOC_0_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/5)-10,
                            Session.breakfastOC_0);


                    Toast.makeText(this, "cal 0   "+Session
                            .breakfastOC_0_Gene0.getWeight(), Toast
                            .LENGTH_SHORT)
                            .show();
                    Session.breakfastOC_1_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/5,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/5)+10,
                            Session.breakfastOC_1);
                    Session.breakfastOC_1_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/5)-10,
                            Session.breakfastOC_1);

                    Toast.makeText(this, "cal 1   "+Session.breakfastOC_1_Gene0.getWeight(), Toast
                            .LENGTH_SHORT)
                            .show();

                    Session.breakfastOC_2_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/5,
                            Session.breakfastOC_2);
                    Session.breakfastOC_2_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/5)+10,
                            Session.breakfastOC_2);
                    Session.breakfastOC_2_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/5)-10,
                            Session.breakfastOC_2);


                    Toast.makeText(this, "cal 2   "+Session.breakfastOC_2_Gene0.getWeight(), Toast
                            .LENGTH_SHORT)
                            .show();

                    Session.breakfastOC_3_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/5,
                            Session.breakfastOC_3);
                    Session.breakfastOC_3_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/5)+10,
                            Session.breakfastOC_3);
                    Session.breakfastOC_3_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/5)-10,
                            Session.breakfastOC_3);



                    Toast.makeText(this, "cal 3   "+Session.breakfastOC_3_Gene0.getWeight(), Toast
                            .LENGTH_SHORT)
                            .show();

                    Session.breakfastOC_4_Gene0=p.generateGenes(Session.breakfastMC.getCalories()/5,
                            Session.breakfastOC_4);
                    Session.breakfastOC_4_Gene1=p.generateGenes((Session.breakfastMC.getCalories()/5)+10,
                            Session.breakfastOC_4);
                    Session.breakfastOC_4_Gene2=p.generateGenes((Session.breakfastMC.getCalories()/5)-10,
                            Session.breakfastOC_4);


                    Toast.makeText(this, "cal 4   "+Session.breakfastOC_4_Gene0.getWeight(), Toast
                            .LENGTH_SHORT)
                            .show();


                }

            }catch (Exception e){
                Toast.makeText(this, "Error in Processing", Toast.LENGTH_SHORT).show();
            }


            try{
                Toast.makeText(this, "Total food Lunch"+ Session.lunchcounter, Toast.LENGTH_SHORT)
                        .show();
                Processing p=new Processing();


                if(Session.lunchcounter==1){
                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_0,Session.lunchOC_0);

                    Session.lunchOC_0_Gene0=p.generateGenes(Session.lunchMC.getCalories(),Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene1=p.generateGenes(Session.lunchMC.getCalories()+10,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene2=p.generateGenes(Session.lunchMC.getCalories()-10,Session
                            .lunchOC_0);


//                Toast.makeText(this, ""+Session.lunchOC_0.getCalories(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, ""+Session.lunchOC_0.getWeight(), Toast.LENGTH_SHORT).show();


                }
                else if(Session.lunchcounter==2){
                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_0,Session.lunchOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_1,Session.lunchOC_1);


                    Session.lunchOC_0_Gene0=p.generateGenes(Session.lunchMC.getCalories()/2,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene1=p.generateGenes((Session.lunchMC.getCalories()/2)+10,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene2=p.generateGenes((Session.lunchMC.getCalories()/2)-10,Session
                            .lunchOC_0);

                    Session.lunchOC_1_Gene0=p.generateGenes(Session.lunchMC.getCalories()/2,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene1=p.generateGenes((Session.lunchMC.getCalories()/2)+10,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene2=p.generateGenes((Session.lunchMC.getCalories()/2)-10,Session
                            .lunchOC_1);



                }

                else if(Session.lunchcounter==3){

                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_0,Session.lunchOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_1,Session.lunchOC_1);


                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_2,Session.lunchOC_2);

                    Session.lunchOC_0_Gene0=p.generateGenes(Session.lunchMC.getCalories()/3,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene1=p.generateGenes((Session.lunchMC.getCalories()/3)+10,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene2=p.generateGenes((Session.lunchMC.getCalories()/3)-10,Session
                            .lunchOC_0);

                    Session.lunchOC_1_Gene0=p.generateGenes(Session.lunchMC.getCalories()/3,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene1=p.generateGenes((Session.lunchMC.getCalories()/3)+10,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene2=p.generateGenes((Session.lunchMC.getCalories()/3)-10,Session
                            .lunchOC_1);


                    Session.lunchOC_2_Gene0=p.generateGenes(Session.lunchMC.getCalories()/3,Session
                            .lunchOC_2);
                    Session.lunchOC_2_Gene1=p.generateGenes((Session.lunchMC.getCalories()/3)+10,Session
                            .lunchOC_2);
                    Session.lunchOC_2_Gene2=p.generateGenes((Session.lunchMC.getCalories()/3)-10,Session
                            .lunchOC_2);


                }


                else if(Session.lunchcounter==4){


                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_0,Session.lunchOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_1,Session.lunchOC_1);


                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_2,Session.lunchOC_2);


                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_3,Session.lunchOC_3);

                    Session.lunchOC_0_Gene0=p.generateGenes(Session.lunchMC.getCalories()/4,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene1=p.generateGenes((Session.lunchMC.getCalories()/4)+10,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene2=p.generateGenes((Session.lunchMC.getCalories()/4)-10,Session
                            .lunchOC_0);

                    Session.lunchOC_1_Gene0=p.generateGenes(Session.lunchMC.getCalories()/4,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene1=p.generateGenes((Session.lunchMC.getCalories()/4)+10,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene2=p.generateGenes((Session.lunchMC.getCalories()/4)-10,Session
                            .lunchOC_1);


                    Session.lunchOC_2_Gene0=p.generateGenes(Session.lunchMC.getCalories()/4,Session
                            .lunchOC_2);
                    Session.lunchOC_2_Gene1=p.generateGenes((Session.lunchMC.getCalories()/4)+10,Session
                            .lunchOC_2);
                    Session.lunchOC_2_Gene2=p.generateGenes((Session.lunchMC.getCalories()/4)-10,Session
                            .lunchOC_2);


                    Session.lunchOC_3_Gene0=p.generateGenes(Session.lunchMC.getCalories()/4,Session
                            .lunchOC_3);
                    Session.lunchOC_3_Gene1=p.generateGenes((Session.lunchMC.getCalories()/4)+10,Session
                            .lunchOC_3);
                    Session.lunchOC_3_Gene2=p.generateGenes((Session.lunchMC.getCalories()/4)-10,Session
                            .lunchOC_3);


                }

                else if(Session.lunchcounter==5){

                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_0,Session.lunchOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_1,Session.lunchOC_1);


                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_2,Session.lunchOC_2);

                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_3,Session.lunchOC_3);


                    p.calculateOneCalorieFoodintegridient(Session.foodLunch_4,Session.lunchOC_4);

                    Session.lunchOC_0_Gene0=p.generateGenes(Session.lunchMC.getCalories()/5,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene1=p.generateGenes((Session.lunchMC.getCalories()/5)+10,Session
                            .lunchOC_0);
                    Session.lunchOC_0_Gene2=p.generateGenes((Session.lunchMC.getCalories()/5)-10,Session
                            .lunchOC_0);

                    Session.lunchOC_1_Gene0=p.generateGenes(Session.lunchMC.getCalories()/5,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene1=p.generateGenes((Session.lunchMC.getCalories()/5)+10,Session
                            .lunchOC_1);
                    Session.lunchOC_1_Gene2=p.generateGenes((Session.lunchMC.getCalories()/5)-10,Session
                            .lunchOC_1);


                    Session.lunchOC_2_Gene0=p.generateGenes(Session.lunchMC.getCalories()/5,Session
                            .lunchOC_2);
                    Session.lunchOC_2_Gene1=p.generateGenes((Session.lunchMC.getCalories()/5)+10,Session
                            .lunchOC_2);
                    Session.lunchOC_2_Gene2=p.generateGenes((Session.lunchMC.getCalories()/5)-10,Session
                            .lunchOC_2);




                    Session.lunchOC_3_Gene0=p.generateGenes(Session.lunchMC.getCalories()/5,Session
                            .lunchOC_3);
                    Session.lunchOC_3_Gene1=p.generateGenes((Session.lunchMC.getCalories()/5)+10,Session
                            .lunchOC_3);
                    Session.lunchOC_3_Gene2=p.generateGenes((Session.lunchMC.getCalories()/5)-10,Session
                            .lunchOC_3);

                    Session.lunchOC_4_Gene0=p.generateGenes(Session.lunchMC.getCalories()/5,Session
                            .lunchOC_4);
                    Session.lunchOC_4_Gene1=p.generateGenes((Session.lunchMC.getCalories()/5)+10,Session
                            .lunchOC_4);
                    Session.lunchOC_4_Gene2=p.generateGenes((Session.lunchMC.getCalories()/5)-10,Session
                            .lunchOC_4);
//                Toast.makeText(this, "gen 0 "+Session.lunchOC_0_Gene0.getWeight(), Toast.LENGTH_SHORT)
//                        .show();
//                Toast.makeText(this, "gen 1 "+Session.lunchOC_1_Gene0.getWeight(), Toast
//                        .LENGTH_SHORT)
//                        .show();
//                Toast.makeText(this, "gen 2 "+Session.lunchOC_2_Gene0.getWeight(), Toast
//                        .LENGTH_SHORT)
//                        .show();
//
//                Toast.makeText(this, "gen 3 "+Session.lunchOC_3_Gene0.getWeight(), Toast
//                        .LENGTH_SHORT)
//                        .show();
//
//                Toast.makeText(this, "gen 4 "+Session.lunchOC_4_Gene0.getWeight(), Toast
//                        .LENGTH_SHORT)
//                        .show();
//
                }

            }catch (Exception e){
                Toast.makeText(this, "Error in Processing", Toast.LENGTH_SHORT).show();
            }



            try{
                Toast.makeText(this, "Total food dinner"+ Session.dinnerrcounter, Toast.LENGTH_SHORT)
                        .show();
                Processing p=new Processing();


                if(Session.dinnerrcounter==1){

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_0,Session.dinnerOC_0);
                    Session.dinnerOC_0_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_0);

                    Session.dinnerOC_0_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_0);
                    Session.dinnerOC_0_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_0);

                    Toast.makeText(this, "dinner "+Session.dinnerOC_0_Gene0.getWeight()+" " +
                            "cal"+Session.dinnerOC_0_Gene0.getCalories(), Toast
                            .LENGTH_SHORT)
                            .show();


                    Toast.makeText(this, "dinner "+Session.dinnerOC_0_Gene1.getWeight()+" " +
                            "cal"+Session.dinnerOC_0_Gene1.getCalories(), Toast
                            .LENGTH_SHORT)
                            .show();    Toast.makeText(this, "dinner "+Session
                            .dinnerOC_0_Gene1.getWeight()+" " +
                            "cal"+Session.dinnerOC_0_Gene1.getCalories(), Toast
                            .LENGTH_SHORT)
                            .show();


                }
                else if(Session.dinnerrcounter==2){

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_0,Session.dinnerOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_1,Session.dinnerOC_1);

                    Session.dinnerOC_0_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_0);

                    Session.dinnerOC_0_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_0);
                    Session.dinnerOC_0_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_0);


                    Session.dinnerOC_1_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_1);

                    Session.dinnerOC_1_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_1);
                    Session.dinnerOC_1_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_1);


                }

                else if(Session.dinnerrcounter==3){

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_0,Session.dinnerOC_0);
                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_1,Session.dinnerOC_1);

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_2,Session.dinnerOC_2);


                    Session.dinnerOC_0_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_0);

                    Session.dinnerOC_0_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_0);
                    Session.dinnerOC_0_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_0);


                    Session.dinnerOC_1_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_1);

                    Session.dinnerOC_1_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_1);
                    Session.dinnerOC_1_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_1);

                    Session.dinnerOC_2_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_2);

                    Session.dinnerOC_2_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_2);
                    Session.dinnerOC_2_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_2);


                }


                else if(Session.dinnerrcounter==4){

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_0,Session.dinnerOC_0);

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_1,Session.dinnerOC_1);

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_2,Session.dinnerOC_2);

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_3,Session.dinnerOC_3);



                    Session.dinnerOC_0_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_0);

                    Session.dinnerOC_0_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_0);
                    Session.dinnerOC_0_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_0);


                    Session.dinnerOC_1_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_1);

                    Session.dinnerOC_1_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_1);
                    Session.dinnerOC_1_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_1);

                    Session.dinnerOC_2_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_2);

                    Session.dinnerOC_2_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_2);
                    Session.dinnerOC_2_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_2);

                    Session.dinnerOC_3_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_3);

                    Session.dinnerOC_3_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_3);
                    Session.dinnerOC_3_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_3);






                }
                else if(Session.dinnerrcounter==5){

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_0,Session.dinnerOC_0);
                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_1,Session.dinnerOC_1);

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_2,Session.dinnerOC_2);

                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_3,Session.dinnerOC_3);
                    p.calculateOneCalorieFoodintegridient(Session.foodDinner_4,Session.dinnerOC_4);

                    Session.dinnerOC_0_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_0);

                    Session.dinnerOC_0_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_0);
                    Session.dinnerOC_0_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_0);


                    Session.dinnerOC_1_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_1);

                    Session.dinnerOC_1_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_1);
                    Session.dinnerOC_1_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_1);

                    Session.dinnerOC_2_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_2);

                    Session.dinnerOC_2_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_2);
                    Session.dinnerOC_2_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_2);

                    Session.dinnerOC_3_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_3);

                    Session.dinnerOC_3_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_3);
                    Session.dinnerOC_3_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_3);


                    Session.dinnerOC_4_Gene0=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter), Session.dinnerOC_4);

                    Session.dinnerOC_4_Gene1=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)+10, Session.dinnerOC_4);
                    Session.dinnerOC_4_Gene2=p.generateGenes((Session.dinnerMC.getCalories()/Session
                            .dinnerrcounter)-10, Session.dinnerOC_4);



                }

            }catch (Exception e){
                Toast.makeText(this, "Error in Processing", Toast.LENGTH_SHORT).show();
            }




        }




//        FoodItem breakfastOneCalorie=new FoodItem();//breakfastOneCalorie is used to store the 1
//        // calorie macronutrient
//        p.calculateOneCalorieFoodintegridient(Session.foodBreakFast_0,breakfastOneCalorie);
//
//        MacroNutrient breakFastExpectedMacronutrient=new MacroNutrient();
//        //breakFastExpectedMacronutrient is used to store the expected macronutrient for the
//        // breakfast food
//
//        breakFastExpectedMacronutrient=p.initialize(breakFastExpectedMacronutrient);
//
//
//        FoodItem gene1=new FoodItem();
//        gene1=p.generateGenes(/*p.generateRandom(breakFastExpectedMacronutrient.getCalories()
//        .intValue())*/400,
//                breakfastOneCalorie);
//        p.fitnessFunction(gene1,breakFastExpectedMacronutrient);
//
//            Toast.makeText(this, " calories  :  "+gene1.getCalories(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "carbo "+gene1.getCarbohydrate(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Protein "+gene1.getProtein(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Fat "+ gene1.getFat(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Weight "+ gene1.getWeight(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Fitness value  "+ gene1.getError(), Toast.LENGTH_SHORT).show();




    }




//    public int counterCOURCEITEM(int counter){
//        if(counter<5)
//            return counter+1;
//
//        return  counter;
//    }

    //    this is 5.34PM
    //  9/30/2017*/

    class  BackgroundTaskGetJson_FoodDetail extends AsyncTask<String ,Void,Boolean> {


        ProgressDialog pdLoading = new ProgressDialog(DietTime.this);

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

                getJSONdata_url = "http://"+Session.IPV4+"/getDiet.php";
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

    public  FoodIntegridients getdiet(String jsonString){

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


    private class WaitTime extends AsyncTask<Void, Void, Void> {

        //pDialog = ProgressDialog.show(this,null, "Lädt..",true);

        //ProgressDialog mDialog=new ProgressDialog(DietTime.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }
        protected void onPostExecute() {
            pDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            pDialog.dismiss();
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... params) {
            long delayInMillis = 2000;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pDialog.dismiss();
                }
            }, delayInMillis);
            return null;
        }
    }


}


