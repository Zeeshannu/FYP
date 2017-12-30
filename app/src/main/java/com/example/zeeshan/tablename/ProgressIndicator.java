package com.example.zeeshan.tablename;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProgressIndicator extends AppCompatActivity {



    private TextView mStartWeight;
    private TextView mGoalWeight;
    private TextView mMaxWeight;
    private TextView mTogoalWeight;
    private TextView mAverageWeight;
    private TextView mLostWeight;
    private TextView mMinimumWeight;
    private TextView mTotallostWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_indicator);

        mStartWeight = (TextView) findViewById(R.id.start_weight);
        mGoalWeight = (TextView) findViewById(R.id.goal_weight);
        mMaxWeight = (TextView) findViewById(R.id.max_weight);
        mTogoalWeight = (TextView) findViewById(R.id.togoal_weight);
        mAverageWeight = (TextView) findViewById(R.id.average_weight);
        mLostWeight = (TextView) findViewById(R.id.lost_weight);
        mMinimumWeight = (TextView) findViewById(R.id.minimum_weight);
        mTotallostWeight = (TextView) findViewById(R.id.totallost_weight);


    }






























}
