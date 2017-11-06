package com.example.zeeshan.tablename.Model;

/**
 * Created by Zeeshan on 10/18/2017.
 */

public class FoodselectionCategory {
//
//    String
//    public static String[] breakFastSelection =new String[5];
//    public static String[] lunchSelection=new String[5];
//    public static String[] dinnerSelection=new String[5];
//
//
//    public static String breakFastCategory="";
//    public static String lunchCategory="";
//    public static String dinnerCategory="";

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String selection,category;

    public FoodselectionCategory(String selection, String category) {
        this.selection = selection;
        this.category = category;
    }

    public  void initialize(FoodselectionCategory[] array, int size){
        for (int i=0;i<size;i++){
            array[i]=new FoodselectionCategory();
        }
    }
    public FoodselectionCategory(){
        selection="";
        category="";
    }
}
