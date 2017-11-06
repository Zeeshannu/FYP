package com.example.zeeshan.tablename.Model;

import android.util.Log;

/**
 * Created by Zeeshan on 10/15/2017.
 */

public class FoodItem {

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getError() {
        return error;
    }

    public void setError(Double error) {
        this.error = error;
    }

    public String getFoodtitle() {
        return foodtitle;
    }

    public void setFoodtitle(String foodtitle) {
        this.foodtitle = foodtitle;
    }

    Double calories,carbohydrate,fat,protein,weight,error;
    String foodtitle;



    public FoodItem(FoodItem processing)
    {
        this.calories = processing.getCalories();
        this.carbohydrate = processing.getCarbohydrate();
        this.fat = processing.getFat();
        this.protein = processing.getProtein();
        this.foodtitle = processing.getFoodtitle();
    }
    public  FoodItem(){

        this.weight=0.0;
        this.error=0.0;
        this.calories = 0.0;
        this.carbohydrate = 0.0;
        this.fat = 0.0;
        this.protein = 0.0;
        this.foodtitle = "";

    }


    public void displayresult(){
        Log.i("in_cal",getCalories().toString());
        Log.i("in_car",getCarbohydrate().toString());
        Log.i("in_pro",getProtein().toString());
        Log.i("in_fat",getFat().toString());
        Log.i("in_title",getFoodtitle().toString());

    }

}
