package com.example.zeeshan.tablename;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ViewDietPlan extends Fragment {



    Toolbar toolBar;
    View v;


    Initializer initializer;
    public void InitializerObject(Initializer i)
    {
        initializer=i;

    }
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.view_diet_plan,container,false);
        // toolSlider = (SlidingDrawer) vfindViewById((R.id.slidingDrawerTools));





        return v;
    }


}
