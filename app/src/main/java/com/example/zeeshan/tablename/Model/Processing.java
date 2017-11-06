package com.example.zeeshan.tablename.Model;

import com.example.zeeshan.tablename.FoodIntegridients;
import com.example.zeeshan.tablename.Session;

import java.util.Random;

/**
 * Created by Zeeshan on 10/15/2017.
 */

public class Processing {

    MacroNutrient EstimatedBreakfast=new MacroNutrient();
    MacroNutrient Estimatedlunch=new MacroNutrient();
    MacroNutrient EstimatedDiner=new MacroNutrient();

    public FoodIntegridients foodintegridientBreakFast=new FoodIntegridients();
    public FoodIntegridients oneCaloriefoodintegridientBreakFast=new FoodIntegridients();


    public Processing(){}

    public Processing(FoodIntegridients foodBreakFast){
//        this.foodintegridientBreakFast=foodBreakFast;
//
//        //this will give the estimated macronutrient fro breakfast
//        this.EstimatedBreakfast=initialize(EstimatedBreakfast);


        //calculate 1 calorie MacrNurrient
       // calculateOneCalorieFoodintegridient(foodBreakFast,oneCaloriefoodintegridientBreakFast);



    }

    public FoodItem expetedItem =new FoodItem();

public FoodItem food=new FoodItem();

//overloading method for optional parameters
    public MacroNutrient initialize(MacroNutrient EstimatedBreakfast){
        return  initialize(EstimatedBreakfast,40);
    }

    //functio to initaialize the expected quantity for the Cource(Breakfast,Lunch,Dinner)
    public MacroNutrient initialize(MacroNutrient EstimatedBreakfast, int weight) {

        double percentWeight = weight * .01;
        EstimatedBreakfast.setProtein(Math.round((Session.Protein * percentWeight)*10000.0)
                /10000.0);
        EstimatedBreakfast.setCalories(Math.round((Session.Calorie * percentWeight)*1.0)
                /1.0);
        EstimatedBreakfast.setCarbohydrate(Math.round((Session.Cabohydrates * percentWeight)
                *10000.0)/10000.0);
        EstimatedBreakfast.setFat(Math.round((Session.Fat * percentWeight)*10000.0)/10000.0);
        return EstimatedBreakfast;
    }

    public FoodItem recommendedDiet(FoodIntegridients ical, MacroNutrient estimated){
        FoodItem recommended=new FoodItem();
        recommended.setCalories(estimated.getCalories()*ical.getCalories());
        recommended.setFat(estimated.getCalories()*ical.getFat());
        recommended.setProtein(estimated.getCalories()*ical.getProtein());
        recommended.setCarbohydrate(estimated.getCalories()*ical.getCarbohydrate());

        //recommended.setWeight();







        return  recommended;
    }



    //A ->tested

    public void calculateOneCalorieFoodintegridient(FoodIntegridients given /*main selescte food*/ ,
                                                    FoodItem foodItem /*food to
                                                            initialize for 1 calorie*/){


        if(given.getCalories()==0){

            foodItem.setWeight(0.0);
            foodItem.setCalories(0.0);
            foodItem.setError(0.0);
            foodItem.setCarbohydrate(0.0);
            foodItem.setProtein(0.0);
            foodItem.setFat(0.0);
            foodItem.setFoodtitle("");

        }
        else
        foodItem.setCalories(1.0);
        foodItem.setFat(Math.round((given.getFat()/given.getCalories())*10000.0)
                /10000.0);
        foodItem.setCarbohydrate(Math.round(given.getCarbohydrate()/given.getCalories()*10000.0)
                /10000.0);
        foodItem.setProtein(Math.round(given.getProtein()/given.getCalories()*10000.0)
                /10000.0);
        foodItem.setWeight(Math.round((100/given.getCalories())*10000.0)
                /10000.0);


    }



    public FoodItem generateGenes(Double calories /*from estimed calories from MacroNutrient*/,
                                  FoodItem foodItem
                                  /*calculatedOneCalorieFoodintegridient*/){

FoodItem f=new FoodItem();
        f.setCalories(Math.round((foodItem.getCalories()*calories)*1.0)
                /1.0);

        f.setFat(Math.round((foodItem.getFat()*calories)*10000.0)
                /10000.0);
        f.setCarbohydrate(Math.round(foodItem.getCarbohydrate()*calories*10000.0)
                /10000.0);
        f.setProtein(Math.round(foodItem.getProtein()*calories*10000.0)
                /10000.0);
        f.setWeight(Math.round(foodItem.getWeight()*calories*10000.0)
                /10000.0);

        return  f;
    }



    public   int generateRandom(int calories){
        int rendomNumber=0;

        int minimum= (int) (calories-(calories*.1));
        int maximum=(int) (calories+(calories*.1));
        int difference=maximum-minimum;

        //create an instance of random class
        Random random=new Random();
        rendomNumber=minimum+random.nextInt(difference+1);
        return  rendomNumber;
    }



    public void fitnessFunction(FoodItem foodItem /*gene that is created*/ , MacroNutrient
            expetedMacronutrient /*expeted for this cource*/){
        Double error=0.0;
        error+=/*Math.abs*/((foodItem.getCalories()-expetedMacronutrient.getCalories())/4);
        error+=/*Math.abs*/((foodItem.getCarbohydrate()-expetedMacronutrient.getCarbohydrate())/4);
        error+=/*Math.abs*/((foodItem.getFat()-expetedMacronutrient.getFat())/4);
        error+=/*Math.abs*/((foodItem.getProtein()-expetedMacronutrient.getProtein())/4);
        foodItem.setError(error);

    }
}
