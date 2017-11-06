package com.example.zeeshan.tablename;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.zeeshan.tablename.Model.Processing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Initializer extends AppCompatActivity {



    AboutUs fragmentAboutUs;
    ViewProfile fragmentProfileSettings;
    ViewDietPlan fragmentViewPlan;
    // ProgressIndicator fragmentProfileSettings;
    Toolbar toolBar;
    private DrawerLayout mDrawer;

    private NavigationView nvDrawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FrameLayout frameLayout;


    static String UserID, UserName, EmailID, Password;
    static Bitmap UserImage;
    static String Age;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.initializer);



        String result = getIntent().getStringExtra("message");
        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

        frameLayout = (FrameLayout) Initializer.this.getWindow().findViewById(R.id.frame);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.drager);
        this.setTitle(String.format("PocketI2Diet"));

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.navigationView);

        setupDrawerContent(nvDrawer);

       // doInBackground();

 /*       final View layoutH = nvDrawer.getHeaderView(0);
        CircleImageView im = (CircleImageView) layoutH.findViewById(R.id.profilePic);
        Log.d("Message", String.valueOf(UserImage));
        if (UserImage != null) {
            Bitmap circleBitmap = Bitmap.createBitmap(UserImage.getWidth(), UserImage.getHeight(), Bitmap.Config.ARGB_8888);

            BitmapShader shader = new BitmapShader(UserImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            paint.setAntiAlias(true);
            Canvas c = new Canvas(circleBitmap);
            c.drawCircle(UserImage.getWidth() / 2, UserImage.getHeight() / 2, UserImage.getWidth() / 2, paint);

            im.setImageBitmap(circleBitmap);
            RelativeLayout rv = (RelativeLayout) layoutH.findViewById(R.id.headerID);
            Drawable drawable = new BitmapDrawable(getResources(), UserImage);
            rv.setBackground(drawable);
        } else {
           /* RelativeLayout rv = (RelativeLayout) layoutH.findViewById(R.id.headerID);
            Drawable drawable = getResources().getDrawable(R.drawable.header);
            rv.setBackground(drawable);
            im.setImageDrawable(getResources().getDrawable(R.drawable.one));*/

    /*    }
        //im.setImageBitmap(UserImage);
        TextView emailID = (TextView) layoutH.findViewById(R.id.userEmailID);
        emailID.setText(EmailID);
        TextView userName = (TextView) layoutH.findViewById(R.id.userName);
        userName.setText(UserName);
*/
        setupDrawerContent(nvDrawer);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolBar, R.string.app_name, R.string.app_name);
        mDrawer.setDrawerListener(actionBarDrawerToggle);

       }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem) {

        setProgressBarVisibility(false);

        frameLayout.setVisibility(View.VISIBLE);
        LayoutInflater inflater = (LayoutInflater) Initializer.this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        if (menuItem.getItemId() == R.id.viewPlan) {
            fragmentViewPlan = new ViewDietPlan();
            fragmentViewPlan .InitializerObject(this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentViewPlan );
            fragmentTransaction.commit();
        } else if (menuItem.getItemId() == R.id.generateDiet) {
            // setTitle(String.format("Submit Questions"));

            Session.Protein =  Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
            Session.Cabohydrates = Math.round(((.15*Session.Calorie)/4)*1000.0)/1000.0;
            Session.Fat =  Math.round(((.15*Session.Calorie)/9)*1000.0)/1000.0;

            Session.totalMC.setCalories(Session.Calorie);
            Session.totalMC.setFat(Session.Fat);
            Session.totalMC.setCarbohydrate(Session.Cabohydrates);
            Session.totalMC.setProtein(Session.Protein);

            Processing p=new Processing();

            Session.breakfastMC=p.initialize(Session.breakfastMC,40);
            Session.lunchMC=p.initialize(Session.lunchMC,35);
            Session.dinnerMC=p.initialize(Session.dinnerMC,25);
//
//                Toast.makeText(CompleteProfile.this, "Session.totalMC "+Session.totalMC
//                        .getCalories(), Toast
//                        .LENGTH_SHORT)
//                        .show();
//                Toast.makeText(CompleteProfile.this, "Session.breakfastMC"+Session.breakfastMC.getCalories(), Toast.LENGTH_SHORT).show();
//
//                Toast.makeText(CompleteProfile.this, "Session.LunchMC"+Session.lunchMC.getCalories(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(CompleteProfile.this, "Session.DinnerMC"+Session.dinnerMC
//                        .getCalories(), Toast.LENGTH_SHORT).show();
            Intent home_page = new Intent(Initializer.this,DietTime.class);
            startActivity(home_page);

        }
        else if
                (menuItem.getItemId() == R.id.help) {
            // Toast.makeText(getApplicationContext(), "Still Working on this Module", Toast.LENGTH_SHORT).show();

            Intent home_page = new Intent(Initializer.this,queryprocessing.class);
            startActivity(home_page);


        } else if (menuItem.getItemId() == R.id.profile) {
            //Toast.makeText(getApplicationContext(), "Still Working on this Module", Toast.LENGTH_SHORT).show();
           /* frameLayout.setVisibility(View.VISIBLE);
            LayoutInflater inflater1 = (LayoutInflater)Initializer.this.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            // setTitle(String.format("Submit Questions"));
*/

            fragmentProfileSettings = new ViewProfile();
            fragmentProfileSettings.InitializerObject(Initializer.this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentProfileSettings);
            fragmentTransaction.commit();
        } else if
                (menuItem.getItemId() == R.id.aboutUs) {
            fragmentAboutUs = new AboutUs();
            fragmentAboutUs.InitializerObject(this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentAboutUs);
            fragmentTransaction.commit();


        } else if
                (menuItem.getItemId() == R.id.logout) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit");
            builder.setMessage("Are You Sure?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    wrtieFileOnInternalStorage();
                    Intent initializer = new Intent(Initializer.this, Login.class);
                    startActivity(initializer);
                    // finish();

                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        } else if
                (menuItem.getItemId() == R.id.progressIndicator) {



            Toast.makeText(Initializer.this,"Under Development", Toast.LENGTH_SHORT).show();


        }
        menuItem.setChecked(true);

        mDrawer.closeDrawers();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are You Sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void wrtieFileOnInternalStorage() {
        try {
            String text = "Nothing";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(getFilesDir() + File.separator + "User.txt")));
            bufferedWriter.write(text);
            bufferedWriter.close();

        } catch (Exception e) {

        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new
                    File(getApplicationContext().getFilesDir() + File.separator + "User.txt")));
            String read;
            StringBuilder builder = new StringBuilder("");

            while ((read = bufferedReader.readLine()) != null) {
                builder.append(read);
            }
            Log.d("Output", builder.toString());

            bufferedReader.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();

        }
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
