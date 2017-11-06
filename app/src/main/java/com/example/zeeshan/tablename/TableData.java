package com.example.zeeshan.tablename;

/**
 * Created by Zeeshan on 9/28/2017.
 */

public class TableData {

    String foodname;
    String foodImageURL;

    public String getFoodImageURL() {
        return foodImageURL;
    }

    public void setFoodImageURL(String foodImageURL) {
        this.foodImageURL = foodImageURL;

    }

    public TableData(String foodname,String foodImageURL) {
        this.foodname = foodname;
        this.foodImageURL=foodImageURL;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }
}
