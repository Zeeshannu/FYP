package com.example.zeeshan.tablename;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeeshan.tablename.Model.Processing;

public class CompleteProfile extends AppCompatActivity {


    String name,genderText,activityLevel;
    int age,height,weight,gender;
    Button SaveButton;
    TextView NameEditText,BodytypeEditText,BEEEditText,BMIEditText,WeightEditText,HeightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

//getting data from intent
        final Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        name=bundle.getString("Name");
        age=bundle.getInt("Age");
        height=bundle.getInt("Height");
        weight=bundle.getInt("Weight");
        genderText=bundle.getString("GenderText");
       activityLevel=bundle.getString("activity");

//binding to the UI
        NameEditText=(TextView)findViewById(R.id.nameEditText);
        BEEEditText=(TextView)findViewById(R.id.beeEditText);
        WeightEditText=(TextView)findViewById(R.id.weightEditText);
        HeightEditText=(TextView)findViewById(R.id.heightEditText);
        BodytypeEditText=(TextView)findViewById(R.id.bodytypeEditText);
        BMIEditText=(TextView)findViewById(R.id.bmiEditText);
           //Toast.makeText(getApplicationContext(),"Save to Db", Toast.LENGTH_SHORT).show();
        SaveButton=(Button)findViewById(R.id.saveButton);



        SaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();


                Session.Protein =  Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
                Session.Cabohydrates = Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
                Session.Fat =  Math.round(((.15*Session.Calorie)/9)*1000.0)/1000.0;

                Session.totalMC.setCalories(Session.Calorie);
                Session.totalMC.setFat(Session.Fat);
                Session.totalMC.setCarbohydrate(Session.Cabohydrates);
                Session.totalMC.setProtein(Session.Protein);

                Processing p=new Processing();

                Session.breakfastMC=p.initialize(Session.breakfastMC,40);
                Session.lunchMC=p.initialize(Session.lunchMC,35);
                Session.dinnerMC=p.initialize(Session.dinnerMC,25);

                Toast.makeText(CompleteProfile.this, "Session.totalMC "+Session.totalMC
                        .getCalories(), Toast
                        .LENGTH_SHORT)
                        .show();
                Toast.makeText(CompleteProfile.this, "Session.breakfastMC"+Session.breakfastMC.getCalories(), Toast.LENGTH_SHORT).show();

                Toast.makeText(CompleteProfile.this, "Session.LunchMC"+Session.lunchMC.getCalories(), Toast.LENGTH_SHORT).show();
                Toast.makeText(CompleteProfile.this, "Session.DinnerMC"+Session.dinnerMC
                        .getCalories(), Toast.LENGTH_SHORT).show();












                Intent intent=new Intent(getApplicationContext(),DietTime.class);
                startActivity(intent);


            }
        });










        NameEditText.setText(name);
        HeightEditText.setText(String.valueOf(height));
        WeightEditText.setText(String.valueOf(weight));
        int BMI=bmicalculator(height,weight);

        BMIEditText.setText(String.valueOf(BMI));
        BEEEditText.setText(String.valueOf(beecalculator(height,weight,age,genderText)));
        BodytypeEditText.setText(bodytype(BMI));

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


//                Protein = (15% x Calorie)/4
//                Cabohydrates (CHO) = (15% x Calorie)/4
//                Fat = (15% x Calorie)/9

        //For Men
//        BMR = 66.4730 + 13.7516w + 5.0033h -6.7550a
            if(genderText.equals("Male")){
            ageFloat=(float)(age*6.755);
            weightFloat=(float)(weight*13.75);
            height=height*5;
            BEE=(float)(66.47+ weightFloat+height-ageFloat);

              if(activityLevel.equals("Sedentary (little or no exercise)")){
                 Session.Calorie = Math.round((BEE * 1.2)*1.0)/1.0 ; }
               else if(activityLevel.equals("Lightly active (light exercise)")){
                   Session.Calorie = Math.round((BEE * 1.375)*1.0)/1.0 ; }

          else if(activityLevel.equals("Moderately active (moderate exercise)")){
                   Session.Calorie = Math.round((BEE * 1.55)*1.0)/1.0 ; }

           else if(activityLevel.equals("Very active (hard exercise)")){
                 Session.Calorie = Math.round((BEE * 1.725)*1.0)/1.0 ; }

     else if(activityLevel.equals("Extra active (very hard exercise)")){
              Session.Calorie = Math.round((BEE * 1.9)*1.0)/1.0 ; }
//
//                else
//
            else
              {Session.Calorie = Math.round((BEE * 1.55)*1.0)/1.0 ;}



//                //Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0
//                Session.Protein =  Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
//                Session.Cabohydrates = Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
//                Session.Fat =  Math.round(((.15*Session.Calorie)/9)*1000.0)/1000.0;
//
//
//                Toast.makeText(this, "Pro"+Session.Protein, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "Cabohydrates"+Session.Cabohydrates, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "Fat"+Session.Fat, Toast.LENGTH_SHORT).show();
//
//                Toast.makeText(this, "Calories"+Session.Calorie, Toast.LENGTH_SHORT).show();

            }
//        For women
//        BMR = 655.0955 + 9.5634w + 1.8496h -4.6756a

//
//        Sedentary (little or no exercise)
//        Calorie = BMR x 1.2
//        Lightly active (light exercise/sports 1-3 days/week)
//        Calorie = BMR x 1.375
//        Moderately active (moderate exercise)
//        Calorie = BMR x 1.55
//        Very active (hard exercise/sports 6-7 days a week)
//        Calorie = BMR x 1.725
//        Extra active (very hard exercise)
//        Calorie = BMR x 1.9
        if(genderText.equals("Female")){
            ageFloat=(float)(age*4.675);
            weightFloat=(float)(weight*9.56);
            float heightfloat=(float) (height*1.8496);
            BEE=(float)(665+ weightFloat+heightfloat-ageFloat);




         if(activityLevel.equals("Sedentary (little or no exercise)")){
           Session.Calorie =  Math.round((BEE * 1.2)*1.0)/1.0;
//                //(Math.round((given.getFat()/given.getCalories())*10000.0)
//                  //      /10000.0)
        }

    else if(activityLevel.equals("Lightly active (light exercise/sports 1-3 days/week)")){
            Session.Calorie = Math.round((BEE * 1.375)*1.0)/1.0 ; }
        else if(activityLevel.equals("Moderately active (moderate exercise)")){
          Session.Calorie = Math.round((BEE * 1.55)*1.0)/1.0; }
   else if(activityLevel.equals("Very active (hard exercise/sports 6-7 days a week)")){
            Session.Calorie =  Math.round((BEE * 1.725)*1.0)/1.0;}
   else if(activityLevel.equals("Extra active (very hard exercise)")){
            Session.Calorie =  Math.round((BEE * 1.9)*1.0)/1.0;}
            else {


             Session.Calorie = Math.round((BEE * 1.55) * 1.0) / 1.0;
         }

//            Session.Protein = Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
//            Toast.makeText(this, "Pro"+Session.Protein, Toast.LENGTH_SHORT).show();
//
//
//            Session.Cabohydrates = Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
//            Toast.makeText(this, "Cabohydrates"+Session.Cabohydrates, Toast.LENGTH_SHORT).show();
//
//
//            Session.Fat = Math.round(((.15*Session.Calorie)/9)*1000.0)/1000.0;
//            Toast.makeText(this, "Fat"+Session.Fat, Toast.LENGTH_SHORT).show();
//
//            Toast.makeText(this, "Calories"+Session.Calorie, Toast.LENGTH_SHORT).show();

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

    }



