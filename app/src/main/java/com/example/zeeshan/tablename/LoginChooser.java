package com.example.zeeshan.tablename;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;


public class LoginChooser extends AppCompatActivity {

    RelativeLayout login,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_chooser);
        login= (RelativeLayout) findViewById(R.id.btnSignin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home_page = new Intent(LoginChooser.this,Login.class);
                startActivity(home_page);


            }

        });

        signUp= (RelativeLayout) findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home_page = new Intent(LoginChooser.this,RegisterNow.class);
                startActivity(home_page);


            }

        });

    }
}
