package com.example.zeeshan.tablename;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SlidingDrawer;


public class AboutUs extends Fragment {



    Toolbar toolBar;
    int total_correct=0,total_wrong=0;
    SlidingDrawer expressionsSlider;
    View v;


    Initializer initializer;
    public void InitializerObject(Initializer i)
    {
        initializer=i;

    }
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.about_us,container,false);
        // toolSlider = (SlidingDrawer) vfindViewById((R.id.slidingDrawerTools));





        return v;
    }


}
