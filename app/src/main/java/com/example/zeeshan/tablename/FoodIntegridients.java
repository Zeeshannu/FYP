package com.example.zeeshan.tablename;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Zeeshan on 10/10/2017.
 */


public class FoodIntegridients {

    public FoodIntegridients(Double calories,
                             Double carbohydrate,
                             Double fat,
                             Double protein,
                             String fooddetail_url,
                             String food_category,
                             String foodtitle)
    {
        this.calories = calories;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.protein = protein;
        this.fooddetail_url = fooddetail_url;
        this.food_category = food_category;
        this.foodtitle = foodtitle;
    }
    public  FoodIntegridients(){

        this.calories = 0.0;
        this.carbohydrate = 0.0;
        this.fat = 0.0;
        this.protein = 0.0;
        this.fooddetail_url ="";
        this.food_category = "";
        this.foodtitle = "";

    }

    Double calories,carbohydrate,fat,protein;
    String fooddetail_url,food_category,foodtitle;

    public void displayresult(){
        Log.i("in_cal",getCalories().toString());
        Log.i("in_car",getCarbohydrate().toString());
        Log.i("in_pro",getProtein().toString());
        Log.i("in_fat",getFat().toString());
        Log.i("in_detailURL",getFooddetail_url().toString());
        Log.i("in_cat",getFood_category().toString());
        Log.i("in_title",getFoodtitle().toString());

    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public String getFooddetail_url() {
        return fooddetail_url;
    }

    public void setFooddetail_url(String fooddetail_url) {
        this.fooddetail_url = fooddetail_url;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getFoodtitle() {
        return foodtitle;
    }

    public void setFoodtitle(String foodtitle) {
        this.foodtitle = foodtitle;
    }


}
