package com.example.zeeshan.tablename;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


public void dietplan(View view){
    Intent intent=new Intent(getApplicationContext(),DietTime.class);

    Toast.makeText(this, "this is dietplan", Toast.LENGTH_SHORT).show();
startActivity(intent);
}














































}

