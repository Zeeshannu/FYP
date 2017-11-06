package com.example.zeeshan.tablename;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DietReminder extends AppCompatActivity {

    ImageButton imageButtonBreakFast,imageButtonLunch,imageButtonDinner;
    TextView TVBREAKFAST,TVLUNCH,TVDINNER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_reminder);
        imageButtonBreakFast=(ImageButton)findViewById(R.id.BTNbreakfastImage);
        TVBREAKFAST=(TextView)findViewById(R.id.TVbreakfast);
    }



public void breakfastFUN(View view){
    Calendar cal1=Calendar.getInstance();

    TimePickerDialog abc=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            TVBREAKFAST.setText("Hours: "+i+"  Mintues : "+i1);
            //TimeString=BTTIME.getText().toString();
        }
    },cal1.get(Calendar.HOUR_OF_DAY),cal1.get(Calendar.MINUTE),false);
    abc.show();
}

}


