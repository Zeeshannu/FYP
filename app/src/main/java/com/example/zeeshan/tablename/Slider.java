package com.example.zeeshan.tablename;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Slider extends AppCompatActivity {

    private ViewPager viewPager;
    private Intromanager intromanager;
    private TextView[] dots;
    private LinearLayout dotslayout;
    private int[] layouts;
    RelativeLayout start;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        intromanager= new Intromanager(this);
        if(!intromanager.Check())
        {
            intromanager.setFirst(false);
            Intent i= new Intent(Slider.this,Screen1.class);
            startActivity(i);

        }

        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.slider);

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        dotslayout=(LinearLayout)findViewById(R.id.layoutDots);
        start=(RelativeLayout)findViewById(R.id.btnGetStarted);

        layouts= new int[] { R.layout.activity_screen1,R.layout.activity_screen2,R.layout.activity_screen3};
        addBottomDots(0);
        ChangeStatusBarColor();
        viewPagerAdapter= new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListner);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(Slider.this, LoginChooser.class);
                startActivity(i);

            }
        });

       /* next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

               int current= getItems(+1);
                if(current<layouts.length)
                {
                    viewPager.setCurrentItem(current);
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, Main2.class);
                    startActivity(i);
                    finish();
                }
            }
        });*/
    }

    private void addBottomDots(int position)
    {
        dots= new TextView[layouts.length];
        int[] colorActive=getResources().getIntArray(R.array.dot_active);
        int[] colorInactive=getResources().getIntArray(R.array.dot_Inactive);
        dotslayout.removeAllViews();

        for(int i=0; i<dots.length; i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotslayout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            dots[position].setTextColor(colorActive[position]);
        }
    }

    private int getItems(int i)
    {
        return viewPager.getCurrentItem()+1;
    }

    ViewPager.OnPageChangeListener viewListner= new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {
            addBottomDots(position);
            if(position==layouts.length-1)
            {
                // next.setText("PROCEED");
                // skip.setVisibility(View.GONE);
            }
            else
            {
                // next.setText("NEXT");
                // skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void ChangeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )
        {
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public class ViewPagerAdapter extends PagerAdapter {


        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v= layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v=(View)object;
            container.removeView(v);
        }
    }
}
