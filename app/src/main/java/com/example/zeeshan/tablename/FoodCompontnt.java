package com.example.zeeshan.tablename;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FoodCompontnt extends AppCompatActivity {


    TextView CAL,CAR,FAT,PRO,DETAI_URL,CAT,TITLE;

    Intent i ;

    FoodIntegridients food=new FoodIntegridients();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodcompontnt);
        CAL=(TextView)findViewById(R.id.calorie);
        CAR=(TextView)findViewById(R.id.carbohydrate);
        FAT=(TextView)findViewById(R.id.fat);
        PRO=(TextView)findViewById(R.id.protein);
        DETAI_URL=(TextView)findViewById(R.id.fooddetailurl);
        CAT=(TextView)findViewById(R.id.category);
        TITLE=(TextView)findViewById(R.id.title);

        i= getIntent();
        food =  i.getParcelableExtra("recordFood");

    }


    public void fun(){

        CAL.setText(food.getCalories().toString());
        CAR.setText(food.getCarbohydrate().toString());
        FAT.setText(food.getFat().toString());
        PRO.setText(food.getProtein().toString());

        DETAI_URL.setText(food.getFooddetail_url());
        CAT.setText(food.getFood_category());
        TITLE.setText(food.getFoodtitle());


    }
}
