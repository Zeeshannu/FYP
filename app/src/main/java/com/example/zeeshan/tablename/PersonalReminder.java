package com.example.zeeshan.tablename;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class PersonalReminder extends AppCompatActivity {


    private Switch mBreakfastSwitch;
    private TextView mBreakfastTime;
    private ImageButton mSaveplan;
    private TextView mTextView;
    private EditText mReminderText;
    Calendar cal1;
    public  boolean breakfastFlag=false;


    private void assignViews() {
        mBreakfastSwitch = (Switch) findViewById(R.id.breakfast_switch);
        mBreakfastTime = (TextView) findViewById(R.id.breakfast_time);
        mSaveplan = (ImageButton) findViewById(R.id.saveplan);
        mTextView = (TextView) findViewById(R.id.textView);
        mReminderText = (EditText) findViewById(R.id.reminder_text);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_reminder);
        assignViews();



        mBreakfastSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mBreakfastSwitch.isChecked()){
                    getTime(mBreakfastTime);
                    breakfastFlag=true;
                    Toast.makeText(PersonalReminder.this,
                            ""+breakfastFlag+""+cal1.getTimeInMillis()/1000, Toast
                            .LENGTH_LONG)
                            .show();

                }else{
                    breakfastFlag=false;
                    mBreakfastTime.setText("");
                }
                    }
        });







    }










    public void getTime(final TextView textView){

         cal1=Calendar.getInstance();

        TimePickerDialog abc=new TimePickerDialog(PersonalReminder.this, new TimePickerDialog
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
