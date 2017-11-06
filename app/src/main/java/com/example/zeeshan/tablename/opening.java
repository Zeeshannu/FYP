package com.example.zeeshan.tablename;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;


public class opening extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);
        Bitmap logo= BitmapFactory.decodeResource(opening.this.getResources(), R.drawable.logo);
        ImageView imageView=(ImageView)findViewById(R.id.Logo);
        //logo= CommonResources.getResizedBitmap(logo,320,270);
        //imageView.setImageBitmap(logo);
        // imageView.setImageResource(R.mipmap.paintAppLogo);



        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub

                String username = readFileOnInternalStorage();

                if(username==null) {
                    finish();
                    Intent home_page = new Intent(opening.this,Slider.class);
                    startActivity(home_page);
                }
                else {
                    finish();
                    Intent home_page = new Intent(opening.this, Slider.class);
                    startActivity(home_page);
                }




            }
        }, 5000);

    }

    public String readFileOnInternalStorage(){


        String text=null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new
                    File(getApplicationContext().getFilesDir() + File.separator + "User.txt")));
            String read;
            StringBuilder builder = new StringBuilder("");

            while ((read = bufferedReader.readLine()) != null) {
                builder.append(read);
            }
            text=builder.toString();

            bufferedReader.close();
        }
        catch (Exception e){

        }
        return text;
    }



}
