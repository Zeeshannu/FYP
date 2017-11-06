package com.example.zeeshan.tablename.Model;

/**
 * Created by Zeeshan on 10/15/2017.
 */

public class MacroNutrient {
    public MacroNutrient(MacroNutrient macroNutrient) {
        this.calories = macroNutrient.getCalories();
        this.carbohydrate = macroNutrient.getCarbohydrate();
        this.fat = macroNutrient.getFat();
        this.protein =macroNutrient.getProtein();
    }
public MacroNutrient(){
    this.calories = 0.0;
    this.carbohydrate = 0.0;
    this.fat = 0.0;
    this.protein = 0.0;
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

    Double calories,carbohydrate,fat,protein;

}
