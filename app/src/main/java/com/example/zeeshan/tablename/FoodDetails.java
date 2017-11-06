package com.example.zeeshan.tablename;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FoodDetails extends AppCompatActivity {


    TextView TVFOODNAME,TVFOODDETAIL,TVCALORIES;
    Button BTNLIPIDS,BTNCHD,BTNPROTEIN,OK,CANCEL;
String COURCE_CATEGORY="";

    String protein,fat,carbhydrarte,calories;

    Intent intent;
    String FOODNAMESStr;
    int POSITION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


        CANCEL=(Button)findViewById(R.id.Cancel);
        OK=(Button)findViewById(R.id.Ok);
        BTNLIPIDS=(Button)findViewById(R.id.BTNlipids);
        BTNCHD=(Button)findViewById(R.id.BTNcarbohydrate);
        BTNPROTEIN=(Button)findViewById(R.id.BTNproteins);
        TVCALORIES=(TextView) findViewById(R.id.TVcalories);
        TVFOODDETAIL=(TextView)findViewById(R.id.TVfooddescription);
        TVFOODNAME=(TextView)findViewById(R.id.TVfoodname);
        intent=getIntent();

        protein=intent.getStringExtra("Protein");
        calories=intent.getStringExtra("Calories");
        carbhydrarte=intent.getStringExtra("Carbohydrate");
        fat=intent.getStringExtra("Fat");

        FOODNAMESStr=intent.getStringExtra("foodname");
        POSITION=Integer.parseInt(intent.getStringExtra("position"));
        COURCE_CATEGORY=intent.getStringExtra("Cource");
        TVFOODNAME.setText(FOODNAMESStr);

        TVCALORIES.setText(calories+" cal");
        TVFOODNAME.setText(FOODNAMESStr);

        BTNPROTEIN.setText(protein);
        BTNCHD.setText(carbhydrarte);
        BTNLIPIDS.setText(fat);


    }
    public  void Add(View view)
    {


        if(Session.cource_selection=="breakfast"){

            if(Session.breakfastcounter==0){

        Session.breakfastcounter++;
                finish();
            }else if(Session.breakfastcounter==1){
                Session.breakfastcounter++;
                finish();
            }else if(Session.breakfastcounter==2){
                Session.breakfastcounter++;
                finish();
            }else if(Session.breakfastcounter==3){
                Session.breakfastcounter++;
                finish();
            }else if(Session.breakfastcounter==4){
                Session.breakfastcounter++;
                finish();
            }

        }
        else if(Session.cource_selection=="lunch"){

            if(Session.lunchcounter==0){
                Session.lunchcounter++;
                finish();
            }else if(Session.lunchcounter==1){
                Session.lunchcounter++;
                finish();
            }else if(Session.lunchcounter==2){
                Session.lunchcounter++;
                finish();
            }else if(Session.lunchcounter==3){
                Session.lunchcounter++;
                finish();
            }else if(Session.lunchcounter==4){
                Session.lunchcounter++;
                finish();
            }
        } else if(Session.cource_selection=="dinner") {

            if (Session.dinnerrcounter == 0) {

                Session.dinnerrcounter++;
                finish();
            } else if (Session.dinnerrcounter == 1) {
                Session.dinnerrcounter++;
                finish();
            } else if (Session.dinnerrcounter == 2) {
                Session.dinnerrcounter++;
                finish();
            } else if (Session.dinnerrcounter == 3) {
                Session.dinnerrcounter++;
                finish();
            } else if (Session.dinnerrcounter == 4) {
                Session.dinnerrcounter++;
                finish();
            }
        }
    }


    public  void cancel(View view){



        if(Session.cource_selection=="breakfast")
        {

            if(Session.breakfastcounter==0){
                Session.breakfastSC_0.setSelection("");
                Session.breakfastSC_0.setCategory("");
                finish();
            }
            else if(Session.breakfastcounter==1){
                Session.breakfastSC_1.setSelection("");
                Session.breakfastSC_1.setCategory("");
                finish();
            }
            else if(Session.breakfastcounter==2){
                Session.breakfastSC_2.setSelection("");
                Session.breakfastSC_2.setCategory("");
                finish();
            }
            else if(Session.breakfastcounter==3){
                Session.breakfastSC_3.setSelection("");
                Session.breakfastSC_3.setCategory("");
                finish();
            }
            else if(Session.breakfastcounter==4){
                Session.breakfastSC_4.setSelection("");
                Session.breakfastSC_4.setCategory("");
                finish();
            }

        }else
        if(Session.cource_selection=="lunch"){

            if(Session.lunchcounter==0){
                Session.lunchSC_0.setSelection("");
                Session.lunchSC_0.setCategory("");
                finish();
            }
            else if(Session.lunchcounter==1){
                Session.lunchSC_1.setSelection("");
                Session.lunchSC_1.setCategory("");
                finish();
            }
            else if(Session.lunchcounter==2){
                Session.lunchSC_2.setSelection("");
                Session.lunchSC_2.setCategory("");
                finish();
            }
            else if(Session.lunchcounter==3){
                Session.lunchSC_3.setSelection("");
                Session.lunchSC_3.setCategory("");
                finish();
            }
            else if(Session.lunchcounter==4){
                Session.lunchSC_4.setSelection("");
                Session.lunchSC_4.setCategory("");
                finish();
            }

        }else  if(Session.cource_selection=="dinner"){

            if(Session.dinnerrcounter==0){
                Session.dinnerSC_0.setSelection("");
                Session.dinnerSC_0.setCategory("");
                finish();
            }
            else if(Session.dinnerrcounter==1){
                Session.dinnerSC_1.setSelection("");
                Session.dinnerSC_1.setCategory("");
                finish();
            }
            else if(Session.dinnerrcounter==2){
                Session.dinnerSC_2.setSelection("");
                Session.dinnerSC_2.setCategory("");
                finish();
            }
            else if(Session.dinnerrcounter==3){
                Session.dinnerSC_3.setSelection("");
                Session.dinnerSC_3.setCategory("");
                finish();
            }
            else if(Session.dinnerrcounter==4){
                Session.dinnerSC_4.setSelection("");
                Session.dinnerSC_4.setCategory("");
                finish();
            }

        }

        finish();
    }



}
