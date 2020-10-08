package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.first_time_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.Fragment_FirstActivity_First;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.ViewPagerAdapterFirstActivity;

public class FirstTimeActivity extends AppCompatActivity implements Fragment_FirstActivity_First.OnChangeListener {
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapterFirstActivity(getSupportFragmentManager()));

    }

    @Override
    public void onContinueClicked() {
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }
}