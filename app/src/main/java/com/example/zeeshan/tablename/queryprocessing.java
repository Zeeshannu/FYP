package com.example.zeeshan.tablename;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;


/**
 * Created by Mohsin Ali on 12/8/2016.
 */
public class queryprocessing extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSend;
    private EditText editTextEmail;
    private EditText editTextSubject;
    Handler handler;
    long timeRemaining;
    private EditText editTextMessage;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queryactivity);
        timeRemaining = 6000;
        handler = new Handler();
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        //Adding click listener
        buttonSend.setOnClickListener(this);

        //Initializing the views

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void sendEmail() {
        //Getting content for email

        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        reportquery sm = new reportquery(this, subject, message);
        sm.emailAddressClass ="query";
        //Executing sendmail to send email
        sm.execute();

        //Toast.makeText(queryprocessing.this,"Report Submitted",Toast.LENGTH_LONG).show();

        final Runnable runnable = new Runnable() {
            public void run() {
                finish();

                timeRemaining = timeRemaining - 1000;
                if (timeRemaining > 0) {

                    handler.postDelayed(this, 1000);
                }
                else
                {
                    timeRemaining=1000;
                }
            }
        };
        handler.postDelayed(runnable, 1000);

    }

    @Override
    public void onClick(View view) {
        sendEmail();
        Intent intent;
       /* Toast.makeText(queryprocessing.this,"Report Submitted",Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(queryprocessing.this, Initializer.class);
        startActivity(myIntent);*/
    }

    @Override
    public void onStart() {
        super.onStart();
   }

    @Override
    public void onStop() {
        super.onStop();

    }
}

