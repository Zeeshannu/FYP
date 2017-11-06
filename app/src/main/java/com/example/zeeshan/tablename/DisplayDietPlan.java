package com.example.zeeshan.tablename;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayDietPlan extends AppCompatActivity {

    Button b;
    //updatesd code
    Double totalProtein=0.0 , totalCarbohydrate=0.0, totalFat=0.0,totalBreakfastProtein=0.0 ,
            totalBreakfastCarbohydrate=0.0, totalBreakfastFat=0.0,totalLunchProtein=0.0 ,
            totalLunchCarbohydrate=0.0, totalLunchFat=0.0,totalDinnerProtein=0.0 ,
            totalDinnerCarbohydrate=0.0, totalDinnerFat=0.0;

TextView TVTOTALPROTEIN,TVTOTALFAT,TVTOTALCARBOHYDRATE;
//


    TextView TVBREAKFASTCALORIES,TVLUNCHCALORIES,TVDINNERCALORIES;

    TextView TVBREAKFAST1,TVBREAKFAST2,TVBREAKFAST3,TVBREAKFAST4,TVBREAKFAST5,
            TVBREAKFASTWEIGHT1,TVBREAKFASTWEIGHT2,TVBREAKFASTWEIGHT3,TVBREAKFASTWEIGHT4,TVBREAKFASTWEIGHT5;

    TextView TVLUNCH1,TVLUNCH2,TVLUNCH3,TVLUNCH4,TVLUNCH5,
            TVTVLUNCHWEIGHT1,TVTVLUNCHWEIGHT2,TVTVLUNCHWEIGHT3,TVTVLUNCHWEIGHT4,TVTVLUNCHWEIGHT5;
    TextView TVDINNER1,TVDINNER2,TVDINNER3,TVDINNER4,TVDINNER5,
            TVDINNERWEIGHT1,TVDINNERWEIGHT2,TVDINNERWEIGHT3,TVDINNERWEIGHT4,TVDINNERWEIGHT5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_diet_plan);


        b=(Button)findViewById(R.id.saveplan);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DisplayDietPlan.this, " save", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),Initializer.class);
                startActivity(i);
            }
        });
        //binding the total calories for each cource
        TVBREAKFASTCALORIES=(TextView) findViewById(R.id.caloriesbreakfast);
        TVLUNCHCALORIES=(TextView) findViewById(R.id.calorieslunch);
        TVDINNERCALORIES=(TextView) findViewById(R.id.caloriesdinner);


        TVLUNCH1=(TextView) findViewById(R.id.lunch1);
        TVLUNCH2=(TextView) findViewById(R.id.lunch2);
        TVLUNCH3=(TextView) findViewById(R.id.lunch3);
        TVLUNCH4=(TextView) findViewById(R.id.lunch4);
        TVLUNCH5=(TextView) findViewById(R.id.lunch5);

        TVTVLUNCHWEIGHT1=(TextView) findViewById(R.id.lunch1Waitage);
        TVTVLUNCHWEIGHT2=(TextView) findViewById(R.id.lunch2Waitage);
        TVTVLUNCHWEIGHT3=(TextView) findViewById(R.id.lunch3Waitage);
        TVTVLUNCHWEIGHT4=(TextView) findViewById(R.id.lunch4Waitage);
        TVTVLUNCHWEIGHT5=(TextView) findViewById(R.id.lunch5Waitage);


        TVBREAKFAST1=(TextView) findViewById(R.id.breakfast1);
        TVBREAKFAST2=(TextView) findViewById(R.id.breakfast2);
        TVBREAKFAST3=(TextView) findViewById(R.id.breakfast3);
        TVBREAKFAST4=(TextView) findViewById(R.id.breakfast4);
        TVBREAKFAST5=(TextView) findViewById(R.id.breakfast5);

        TVBREAKFASTWEIGHT1=(TextView) findViewById(R.id.breakfast1Waitage);
        TVBREAKFASTWEIGHT2=(TextView) findViewById(R.id.breakfast2Waitage);
        TVBREAKFASTWEIGHT3=(TextView) findViewById(R.id.breakfast3Waitage);
        TVBREAKFASTWEIGHT4=(TextView) findViewById(R.id.breakfast4Waitage);
        TVBREAKFASTWEIGHT5=(TextView) findViewById(R.id.breakfast5Waitage);


        TVDINNER1=(TextView) findViewById(R.id.dinner1);
        TVDINNER2=(TextView) findViewById(R.id.dinner2);
        TVDINNER3=(TextView) findViewById(R.id.dinner3);
        TVDINNER4=(TextView) findViewById(R.id.dinner4);
        TVDINNER5=(TextView) findViewById(R.id.dinner5);

        TVDINNERWEIGHT1=(TextView) findViewById(R.id.dinner1Waitage);
        TVDINNERWEIGHT2=(TextView) findViewById(R.id.dinner2Waitage);
        TVDINNERWEIGHT3=(TextView) findViewById(R.id.dinner3Waitage);
        TVDINNERWEIGHT4=(TextView) findViewById(R.id.dinner4Waitage);
        TVDINNERWEIGHT5=(TextView) findViewById(R.id.dinner5Waitage);



        TVTOTALCARBOHYDRATE=(TextView)findViewById(R.id.totalCarbohydrate);
        TVTOTALFAT=(TextView)findViewById(R.id.totalFat);
        TVTOTALPROTEIN=(TextView)findViewById(R.id.totalProtein);



        setbreakfast();
        setlunch();
        setdinner();
        setcarbohydrate();
        setprotein();
        setfat();

    }

    public void save(View v){
        Intent i=new Intent(getApplicationContext(),Initializer.class);
        startActivity(i);
    }
   public void setbreakfast(){
       if(Session.breakfastcounter==1){
           TVBREAKFAST1.setText(Session.breakfastSC_0.getSelection());
           TVBREAKFASTWEIGHT1.setText(Session.breakfastOC_0_Gene0.getWeight().toString());
           TVBREAKFASTCALORIES.setText(Session.breakfastOC_0_Gene0.getCalories().toString());
           totalBreakfastCarbohydrate+= Session.breakfastOC_0_Gene0.getCarbohydrate();
           totalBreakfastFat+= Session.breakfastOC_0_Gene0.getFat();
           totalBreakfastProtein+= Session.breakfastOC_0_Gene0.getProtein();

       }
       else if(Session.breakfastcounter==2){
           TVBREAKFAST1.setText(Session.breakfastSC_0.getSelection());
           TVBREAKFASTWEIGHT1.setText(Session.breakfastOC_0_Gene0.getWeight().toString());
           TVBREAKFAST2.setText(Session.breakfastSC_1.getSelection());
           TVBREAKFASTWEIGHT2.setText(Session.breakfastOC_1_Gene0.getWeight().toString());

           totalBreakfastCarbohydrate+= Session.breakfastOC_0_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_1_Gene0.getCarbohydrate();

           totalBreakfastFat+= Session.breakfastOC_0_Gene0.getFat()+ Session
                   .breakfastOC_1_Gene0.getFat();
           totalBreakfastProtein+= Session.breakfastOC_0_Gene0.getProtein()+ Session
                   .breakfastOC_1_Gene0.getProtein();

           double cal= Session.breakfastOC_0_Gene0.getCalories()+ Session

                   .breakfastOC_1_Gene0.getCalories();
          // TVBREAKFASTCALORIES.setText((int) cal);

           TVBREAKFASTCALORIES.setText(String.valueOf(cal));


       }else if(Session.breakfastcounter==3){
           TVBREAKFAST1.setText(Session.breakfastSC_0.getSelection());
           TVBREAKFASTWEIGHT1.setText(Session.breakfastOC_0_Gene0.getWeight().toString());
           TVBREAKFAST2.setText(Session.breakfastSC_1.getSelection());
           TVBREAKFASTWEIGHT2.setText(Session.breakfastOC_1_Gene0.getWeight().toString());
           TVBREAKFAST3.setText(Session.breakfastSC_2.getSelection());
           TVBREAKFASTWEIGHT3.setText(Session.breakfastOC_2_Gene0.getWeight().toString());

           totalBreakfastProtein+= Session.breakfastOC_0_Gene0.getProtein()+ Session
                   .breakfastOC_1_Gene0.getProtein()+ Session
                   .breakfastOC_2_Gene0.getProtein();


           totalBreakfastCarbohydrate+= Session.breakfastOC_0_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_1_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_2_Gene0.getCarbohydrate();
           totalBreakfastFat+= Session.breakfastOC_0_Gene0.getFat()+ Session
                   .breakfastOC_1_Gene0.getFat()+ Session
                   .breakfastOC_2_Gene0.getFat();


           double cal= Session.breakfastOC_0_Gene0.getCalories()
                   + Session
                   .breakfastOC_1_Gene0.getCalories()+ Session
                   .breakfastOC_2_Gene0.getCalories();

           TVBREAKFASTCALORIES.setText(String.valueOf(cal));



       }else if(Session.breakfastcounter==4){
           TVBREAKFAST1.setText(Session.breakfastSC_0.getSelection());
           TVBREAKFASTWEIGHT1.setText(Session.breakfastOC_0_Gene0.getWeight().toString());
           TVBREAKFAST2.setText(Session.breakfastSC_1.getSelection());
           TVBREAKFASTWEIGHT2.setText(Session.breakfastOC_1_Gene0.getWeight().toString());
           TVBREAKFAST3.setText(Session.breakfastSC_2.getSelection());
           TVBREAKFASTWEIGHT3.setText(Session.breakfastOC_2_Gene0.getWeight().toString());

           TVBREAKFAST4.setText(Session.breakfastSC_3.getSelection());
           TVBREAKFASTWEIGHT4.setText(Session.breakfastOC_3_Gene0.getWeight().toString());


           totalBreakfastProtein+= Session.breakfastOC_0_Gene0.getProtein()+ Session
                   .breakfastOC_1_Gene0.getProtein()+ Session
                   .breakfastOC_2_Gene0.getProtein()+ Session
                   .breakfastOC_3_Gene0.getProtein();


           totalBreakfastFat+= Session.breakfastOC_0_Gene0.getFat()+ Session
                   .breakfastOC_1_Gene0.getFat()+ Session
                   .breakfastOC_2_Gene0.getFat()+ Session
                   .breakfastOC_3_Gene0.getFat();

           totalBreakfastCarbohydrate+= Session.breakfastOC_0_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_1_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_2_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_3_Gene0.getCarbohydrate();

           TVBREAKFASTCALORIES.setText((String.valueOf (Session.breakfastOC_0_Gene0.getCalories()
                   + Session
                   .breakfastOC_1_Gene0.getCalories()+ Session
                   .breakfastOC_2_Gene0.getCalories()+ Session.breakfastOC_3_Gene0.getCalories()
                  )));

       }
       else if(Session.breakfastcounter==5){
           TVBREAKFAST1.setText(Session.breakfastSC_0.getSelection());
           TVBREAKFASTWEIGHT1.setText(Session.breakfastOC_0_Gene0.getWeight().toString());
           TVBREAKFAST2.setText(Session.breakfastSC_1.getSelection());
           TVBREAKFASTWEIGHT2.setText(Session.breakfastOC_1_Gene0.getWeight().toString());
           TVBREAKFAST3.setText(Session.breakfastSC_2.getSelection());
           TVBREAKFASTWEIGHT3.setText(Session.breakfastOC_2_Gene0.getWeight().toString());

           TVBREAKFAST4.setText(Session.breakfastSC_3.getSelection());
           TVBREAKFASTWEIGHT4.setText(Session.breakfastOC_3_Gene0.getWeight().toString());

           TVBREAKFAST5.setText(Session.breakfastSC_4.getSelection());
           TVBREAKFASTWEIGHT5.setText(Session.breakfastOC_4_Gene0.getWeight().toString());


           totalBreakfastProtein+= Session.breakfastOC_0_Gene0.getProtein()+ Session
                   .breakfastOC_1_Gene0.getProtein()+ Session
                   .breakfastOC_2_Gene0.getProtein()+ Session
                   .breakfastOC_3_Gene0.getProtein()+ Session
                   .breakfastOC_4_Gene0.getProtein();


           totalBreakfastFat+= Session.breakfastOC_0_Gene0.getFat()+ Session
                   .breakfastOC_1_Gene0.getFat()+ Session
                   .breakfastOC_2_Gene0.getFat()+ Session
                   .breakfastOC_3_Gene0.getFat()+ Session
                   .breakfastOC_4_Gene0.getFat();


           TVBREAKFASTCALORIES.setText((String.valueOf(Session.breakfastOC_0_Gene0.getCalories()+ Session
                              .breakfastOC_1_Gene0.getCalories()+ Session
                   .breakfastOC_2_Gene0.getCalories()+ Session.breakfastOC_3_Gene0.getCalories()
                   + Session.breakfastOC_4_Gene0.getCalories())));


           totalBreakfastCarbohydrate+= Session.breakfastOC_0_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_1_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_2_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_3_Gene0.getCarbohydrate()+ Session
                   .breakfastOC_4_Gene0.getCarbohydrate();

       }

   }

    public void setlunch(){
        if(Session.lunchcounter==1){
            TVLUNCH1.setText(Session.lunchSC_0.getSelection());
            TVTVLUNCHWEIGHT1.setText(Session.lunchOC_0_Gene0.getWeight().toString());
            TVLUNCHCALORIES.setText(Session.lunchOC_0_Gene0.getCalories().toString());

            totalLunchCarbohydrate+= Session.lunchOC_0_Gene0.getCarbohydrate();
            totalLunchFat+= Session.lunchOC_0_Gene0.getFat();
            totalLunchProtein+= Session.lunchOC_0_Gene0.getProtein();


        }
        else if(Session.lunchcounter==2){


            totalLunchCarbohydrate+= Session.lunchOC_0_Gene0.getCarbohydrate()+ Session
                    .lunchOC_1_Gene0.getCarbohydrate();

            totalLunchFat+= Session.lunchOC_0_Gene0.getFat()+ Session
                    .lunchOC_1_Gene0.getFat();
            totalLunchProtein+= Session.lunchOC_0_Gene0.getProtein()+ Session
                    .lunchOC_1_Gene0.getProtein();

            TVLUNCH1.setText(Session.lunchSC_0.getSelection());
            TVTVLUNCHWEIGHT1.setText(Session.lunchOC_0_Gene0.getWeight().toString());

            TVLUNCH2.setText(Session.lunchSC_1.getSelection());
            TVTVLUNCHWEIGHT2.setText(Session.lunchOC_1_Gene0.getWeight().toString());


            double cal= Session.lunchOC_0_Gene0.getCalories()+ Session
                    .lunchOC_1_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVLUNCHCALORIES.setText(String.valueOf(cal));


        }else if(Session.lunchcounter==3){



            totalLunchProtein+= Session.lunchOC_0_Gene0.getProtein()+ Session
                    .lunchOC_1_Gene0.getProtein()+ Session
                    .lunchOC_2_Gene0.getProtein();


            totalLunchCarbohydrate+= Session.lunchOC_0_Gene0.getCarbohydrate()+ Session
                    .lunchOC_1_Gene0.getCarbohydrate()+ Session
                    .lunchOC_2_Gene0.getCarbohydrate();
            totalLunchFat+= Session.lunchOC_0_Gene0.getFat()+ Session
                    .lunchOC_1_Gene0.getFat()+ Session
                    .lunchOC_2_Gene0.getFat();


            TVLUNCH1.setText(Session.lunchSC_0.getSelection());
            TVTVLUNCHWEIGHT1.setText(Session.lunchOC_0_Gene0.getWeight().toString());

            TVLUNCH2.setText(Session.lunchSC_1.getSelection());
            TVTVLUNCHWEIGHT2.setText(Session.lunchOC_1_Gene0.getWeight().toString());
            TVLUNCH3.setText(Session.lunchSC_2.getSelection());
            TVTVLUNCHWEIGHT3.setText(Session.lunchOC_2_Gene0.getWeight().toString());



            double cal= Session.lunchOC_0_Gene0.getCalories()+ Session
                    .lunchOC_1_Gene0.getCalories()+ Session.lunchOC_2_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVLUNCHCALORIES.setText(String.valueOf(cal));



        }else if(Session.lunchcounter==4){




            totalLunchProtein+= Session.lunchOC_0_Gene0.getProtein()+ Session
                    .lunchOC_1_Gene0.getProtein()+ Session
                    .lunchOC_2_Gene0.getProtein()+ Session
                    .lunchOC_3_Gene0.getProtein();


            totalLunchCarbohydrate+= Session.lunchOC_0_Gene0.getCarbohydrate()+ Session
                    .lunchOC_1_Gene0.getCarbohydrate()+ Session
                    .lunchOC_2_Gene0.getCarbohydrate()+ Session
                    .lunchOC_3_Gene0.getCarbohydrate();

            totalLunchFat+= Session.lunchOC_0_Gene0.getFat()+ Session
                    .lunchOC_1_Gene0.getFat()+ Session
                    .lunchOC_2_Gene0.getFat()+ Session
                    .lunchOC_3_Gene0.getFat();



            TVLUNCH1.setText(Session.lunchSC_0.getSelection());
            TVTVLUNCHWEIGHT1.setText(Session.lunchOC_0_Gene0.getWeight().toString());

            TVLUNCH2.setText(Session.lunchSC_1.getSelection());
            TVTVLUNCHWEIGHT2.setText(Session.lunchOC_1_Gene0.getWeight().toString());
            TVLUNCH3.setText(Session.lunchSC_2.getSelection());
            TVTVLUNCHWEIGHT3.setText(Session.lunchOC_2_Gene0.getWeight().toString());

            TVLUNCH4.setText(Session.lunchSC_3.getSelection());
            TVTVLUNCHWEIGHT4.setText(Session.lunchOC_3_Gene0.getWeight().toString());


            double cal= Session.lunchOC_0_Gene0.getCalories()+ Session
                    .lunchOC_1_Gene0.getCalories()+ Session.lunchOC_2_Gene0.getCalories()+ Session
                    .lunchOC_3_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVLUNCHCALORIES.setText(String.valueOf(cal));


        }
        else if(Session.lunchcounter==5){




            totalLunchProtein+= Session.lunchOC_0_Gene0.getProtein()+ Session
                    .lunchOC_1_Gene0.getProtein()+ Session
                    .lunchOC_2_Gene0.getProtein()+ Session
                    .lunchOC_3_Gene0.getProtein()+ Session
                    .lunchOC_4_Gene0.getProtein();


            totalLunchCarbohydrate+= Session.lunchOC_0_Gene0.getCarbohydrate()+ Session
                    .lunchOC_1_Gene0.getCarbohydrate()+ Session
                    .lunchOC_2_Gene0.getCarbohydrate()+ Session
                    .lunchOC_3_Gene0.getCarbohydrate()+ Session
                    .lunchOC_4_Gene0.getCarbohydrate();

            totalLunchFat+= Session.lunchOC_0_Gene0.getFat()+ Session
                    .lunchOC_1_Gene0.getFat()+ Session
                    .lunchOC_2_Gene0.getFat()+ Session
                    .lunchOC_3_Gene0.getFat()+ Session
                    .lunchOC_4_Gene0.getFat();


            TVLUNCH1.setText(Session.lunchSC_0.getSelection());
            TVTVLUNCHWEIGHT1.setText(Session.lunchOC_0_Gene0.getWeight().toString());

            TVLUNCH2.setText(Session.lunchSC_1.getSelection());
            TVTVLUNCHWEIGHT2.setText(Session.lunchOC_1_Gene0.getWeight().toString());
            TVLUNCH3.setText(Session.lunchSC_2.getSelection());
            TVTVLUNCHWEIGHT3.setText(Session.lunchOC_2_Gene0.getWeight().toString());

            TVLUNCH4.setText(Session.lunchSC_3.getSelection());
            TVTVLUNCHWEIGHT4.setText(Session.lunchOC_3_Gene0.getWeight().toString());

            TVLUNCH5.setText(Session.lunchSC_4.getSelection());
            TVTVLUNCHWEIGHT5.setText(Session.lunchOC_4_Gene0.getWeight().toString());


            double cal= Session.lunchOC_0_Gene0.getCalories()+ Session
                    .lunchOC_1_Gene0.getCalories()+ Session.lunchOC_2_Gene0.getCalories()+ Session
                    .lunchOC_3_Gene0.getCalories()+ Session
                    .lunchOC_4_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVLUNCHCALORIES.setText(String.valueOf(cal));


        }

    }



    public void setdinner(){

        if(Session.dinnerrcounter==1){
            TVDINNER1.setText(Session.dinnerSC_0.getSelection());
            TVDINNERWEIGHT1.setText(Session.dinnerOC_0_Gene0.getWeight().toString());
            TVDINNERCALORIES.setText(Session.dinnerOC_0_Gene0.getCalories().toString());

            totalDinnerCarbohydrate+= Session.dinnerOC_0_Gene0.getCarbohydrate();
            totalDinnerFat+= Session.dinnerOC_0_Gene0.getFat();
            totalDinnerProtein+= Session.dinnerOC_0_Gene0.getProtein();



        }
        else if(Session.dinnerrcounter==2){

            totalDinnerCarbohydrate+= Session.dinnerOC_0_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_1_Gene0.getCarbohydrate();
            totalDinnerFat+= Session.dinnerOC_0_Gene0.getFat()+ Session.dinnerOC_1_Gene0.getFat();
            totalDinnerProtein+= Session.dinnerOC_0_Gene0.getProtein()+ Session
                    .dinnerOC_1_Gene0.getProtein();


            TVDINNER1.setText(Session.dinnerSC_0.getSelection());
            TVDINNERWEIGHT1.setText(Session.dinnerOC_0_Gene0.getWeight().toString());

            TVDINNER2.setText(Session.dinnerSC_1.getSelection());
            TVDINNERWEIGHT2.setText(Session.dinnerOC_1_Gene0.getWeight().toString());


            double cal= Session.dinnerOC_0_Gene0.getCalories()+ Session
                    .dinnerOC_1_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVDINNERCALORIES.setText(String.valueOf(cal));


        }else if(Session.dinnerrcounter==3){


            totalDinnerCarbohydrate+= Session.dinnerOC_0_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_1_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_2_Gene0.getCarbohydrate();
            totalDinnerFat+= Session.dinnerOC_0_Gene0.getFat()+ Session.
                    dinnerOC_1_Gene0.getFat()
                    + Session.dinnerOC_2_Gene0.getFat();
            totalDinnerProtein+= Session.dinnerOC_0_Gene0.getProtein()+ Session
                    .dinnerOC_1_Gene0.getProtein()+ Session
                    .dinnerOC_2_Gene0.getProtein();


            TVDINNER1.setText(Session.dinnerSC_0.getSelection());
            TVDINNERWEIGHT1.setText(Session.dinnerOC_0_Gene0.getWeight().toString());

            TVDINNER2.setText(Session.dinnerSC_1.getSelection());
            TVDINNERWEIGHT2.setText(Session.dinnerOC_1_Gene0.getWeight().toString());


            TVDINNER3.setText(Session.dinnerSC_2.getSelection());
            TVDINNERWEIGHT3.setText(Session.dinnerOC_2_Gene0.getWeight().toString());

            double cal= Session.dinnerOC_0_Gene0.getCalories()+ Session
                    .dinnerOC_1_Gene0.getCalories()+ Session.dinnerOC_2_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVDINNERCALORIES.setText(String.valueOf(cal));



        }else if(Session.dinnerrcounter==4){



            totalDinnerCarbohydrate+= Session.dinnerOC_0_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_1_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_2_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_3_Gene0.getCarbohydrate();
            totalDinnerFat+= Session.dinnerOC_0_Gene0.getFat()+ Session.
                    dinnerOC_1_Gene0.getFat()
                    + Session.dinnerOC_2_Gene0.getFat()
                    + Session.dinnerOC_3_Gene0.getFat();
            totalDinnerProtein+= Session.dinnerOC_0_Gene0.getProtein()+ Session
                    .dinnerOC_1_Gene0.getProtein()+ Session
                    .dinnerOC_2_Gene0.getProtein()+ Session
                    .dinnerOC_3_Gene0.getProtein();


            TVDINNER1.setText(Session.dinnerSC_0.getSelection());
            TVDINNERWEIGHT1.setText(Session.dinnerOC_0_Gene0.getWeight().toString());

            TVDINNER2.setText(Session.dinnerSC_1.getSelection());
            TVDINNERWEIGHT2.setText(Session.dinnerOC_1_Gene0.getWeight().toString());


            TVDINNER3.setText(Session.dinnerSC_2.getSelection());
            TVDINNERWEIGHT3.setText(Session.dinnerOC_2_Gene0.getWeight().toString());

            TVDINNER4.setText(Session.dinnerSC_3.getSelection());
            TVDINNERWEIGHT4.setText(Session.dinnerOC_3_Gene0.getWeight().toString());

            double cal= Session.dinnerOC_0_Gene0.getCalories()+ Session
                    .dinnerOC_1_Gene0.getCalories()+ Session.dinnerOC_2_Gene0.getCalories()
                    + Session.dinnerOC_3_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVDINNERCALORIES.setText(String.valueOf(cal));



        }
        else if(Session.dinnerrcounter==5){




            totalDinnerCarbohydrate+= Session.dinnerOC_0_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_1_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_2_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_3_Gene0.getCarbohydrate()+ Session
                    .dinnerOC_4_Gene0.getCarbohydrate();
            totalDinnerFat+= Session.dinnerOC_0_Gene0.getFat()+ Session.
                    dinnerOC_1_Gene0.getFat()
                    + Session.dinnerOC_2_Gene0.getFat()
                    + Session.dinnerOC_3_Gene0.getFat()+ Session.dinnerOC_4_Gene0.getFat();
            totalDinnerProtein+= Session.dinnerOC_0_Gene0.getProtein()+ Session
                    .dinnerOC_1_Gene0.getProtein()+ Session
                    .dinnerOC_2_Gene0.getProtein()+ Session
                    .dinnerOC_3_Gene0.getProtein()+ Session
                    .dinnerOC_4_Gene0.getProtein();



            TVDINNER1.setText(Session.dinnerSC_0.getSelection());
            TVDINNERWEIGHT1.setText(Session.dinnerOC_0_Gene0.getWeight().toString());

            TVDINNER2.setText(Session.dinnerSC_1.getSelection());
            TVDINNERWEIGHT2.setText(Session.dinnerOC_1_Gene0.getWeight().toString());


            TVDINNER3.setText(Session.dinnerSC_2.getSelection());
            TVDINNERWEIGHT3.setText(Session.dinnerOC_2_Gene0.getWeight().toString());

            TVDINNER4.setText(Session.dinnerSC_3.getSelection());
            TVDINNERWEIGHT4.setText(Session.dinnerOC_3_Gene0.getWeight().toString());

            TVDINNER5.setText(Session.dinnerSC_4.getSelection());
            TVDINNERWEIGHT5.setText(Session.dinnerOC_4_Gene0.getWeight().toString());

            double cal= Session.dinnerOC_0_Gene0.getCalories()+ Session
                    .dinnerOC_1_Gene0.getCalories()+ Session.dinnerOC_2_Gene0.getCalories()
                    + Session.dinnerOC_3_Gene0.getCalories()+ Session.dinnerOC_4_Gene0.getCalories();
            // TVBREAKFASTCALORIES.setText((int) cal);
            TVDINNERCALORIES.setText(String.valueOf(cal));



        }

    }

    public void setcarbohydrate(){
        totalCarbohydrate+=totalBreakfastCarbohydrate+totalLunchCarbohydrate
                +totalDinnerCarbohydrate;
        TVTOTALCARBOHYDRATE.setText(String.valueOf(totalCarbohydrate));

    }
    public void setfat(){
        totalFat+=totalBreakfastFat+totalLunchFat+totalDinnerFat;
        TVTOTALFAT.setText(String.valueOf(totalFat));

    }
    public void setprotein(){

        totalProtein+=totalBreakfastProtein+totalLunchProtein+totalDinnerProtein;
                TVTOTALPROTEIN.setText(String.valueOf(totalProtein));

    }

}
