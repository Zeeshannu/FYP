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


    String name, bodyType;
    int bmi, bee, height, weight, age;
    Button SaveButton;
    TextView NameEditText, BodytypeEditText, BEEEditText, BMIEditText, WeightEditText, HeightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

//getting data from intent
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("Name");
        bodyType = bundle.getString("BodyType");
        height = bundle.getInt("Height");
        weight = bundle.getInt("Weight");
        bmi = bundle.getInt("BMI");
        bee = bundle.getInt("BEE");
        Session.Calorie= Double.valueOf(bee);
        age = bundle.getInt("Age");


//binding to the UI
        NameEditText = (TextView) findViewById(R.id.nameEditText);
        BEEEditText = (TextView) findViewById(R.id.beeEditText);
        WeightEditText = (TextView) findViewById(R.id.weightEditText);
        HeightEditText = (TextView) findViewById(R.id.heightEditText);
        BodytypeEditText = (TextView) findViewById(R.id.bodytypeEditText);
        BMIEditText = (TextView) findViewById(R.id.bmiEditText);
        //Toast.makeText(getApplicationContext(),"Save to Db", Toast.LENGTH_SHORT).show();
        SaveButton = (Button) findViewById(R.id.saveButton);


        SaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();


//                Protein = (15% x Calorie)/4
//                Cabohydrates (CHO) = (15% x Calorie)/4
//                Fat = (15% x Calorie)/9


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
        BodytypeEditText.setText(bodyType);
        HeightEditText.setText(String.valueOf(height));
        WeightEditText.setText(String.valueOf(weight));
        BMIEditText.setText(String.valueOf(bmi));
        BEEEditText.setText(String.valueOf(bee));
    }
}
    /*parameters for async
    //name,gender,bodytype,aga,height,weight,bmi,bee*/

