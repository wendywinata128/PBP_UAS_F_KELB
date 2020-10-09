package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.first_time_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.ViewPagerAdapterFirstActivity;
import com.rd.PageIndicatorView;

public class FirstTimeActivity extends AppCompatActivity  {
    private ViewPager viewPager;
    private PageIndicatorView dotsPager;
    private Boolean checkedItem = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        viewPager = findViewById(R.id.viewPager);
        dotsPager = findViewById(R.id.dotsPager);
        viewPager.setAdapter(new ViewPagerAdapterFirstActivity(getSupportFragmentManager()));

    }

}