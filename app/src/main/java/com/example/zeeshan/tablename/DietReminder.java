package com.example.zeeshan.tablename;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DietReminder extends AppCompatActivity {


public  boolean breakfastFlag=false,dinnerFlag=false,lunchFlag=false;
    private TextView mBreakfastTime;
    private TextView mLunchTime;
    private TextView mDinnerTime;
    private Switch mBreakfastSwitch;
    private Switch mLunchSwitch;
    private Switch mDinnerSwitch;

    private void assignViews() {
        mBreakfastTime = (TextView) findViewById(R.id.breakfast_time);
        mLunchTime = (TextView) findViewById(R.id.lunch_time);
        mDinnerTime = (TextView) findViewById(R.id.dinner_time);
        mBreakfastSwitch = (Switch) findViewById(R.id.breakfast_switch);
        mLunchSwitch = (Switch) findViewById(R.id.lunch_switch);
        mDinnerSwitch = (Switch) findViewById(R.id.dinner_switch);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_reminder);
        assignViews();

        mLunchSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mLunchSwitch.isChecked()){
                    getTime(mLunchTime);
                    lunchFlag=true;

                }else{
                    lunchFlag=false;
                    mLunchTime.setText("");
                }

            }
        });

        mDinnerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mDinnerSwitch.isChecked()){
                    getTime(mDinnerTime);
                    dinnerFlag=true;
                }else{
                    dinnerFlag=false;
                    mDinnerTime.setText("");
                }
                Toast.makeText(DietReminder.this, ""+dinnerFlag, Toast.LENGTH_SHORT).show();
            }
        });
        mBreakfastSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mBreakfastSwitch.isChecked()){
                    getTime(mBreakfastTime);
                    breakfastFlag=true;
                }else{
                    breakfastFlag=false;
                    mBreakfastTime.setText("");
                }
            }
        });


    }



    public void getTime(final TextView textView){

    Calendar cal1=Calendar.getInstance();

    TimePickerDialog abc=new TimePickerDialog(DietReminder.this, new TimePickerDialog
            .OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

           // Toast.makeText(DietReminder.this, ""+"Hours: "+i+"  Mintues : "+i1, Toast
             //       .LENGTH_SHORT).show();
            textView.setText("Hours: "+i+"  Mintues : "+i1);
            //TimeString=BTTIME.getText().toString();
        }
    },cal1.get(Calendar.HOUR_OF_DAY),cal1.get(Calendar.MINUTE),false);
    abc.show();
}







}


