package com.example.zeeshan.tablename;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Maryum on 10/2/2017.
 */

public class Starter2 extends AppCompatActivity {

    RelativeLayout getStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starter_two);
        getStarted= (RelativeLayout) findViewById(R.id.btnGetStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home_page = new Intent(Starter2.this,MainActivity.class);
                startActivity(home_page);


            }

        });


    }
}
