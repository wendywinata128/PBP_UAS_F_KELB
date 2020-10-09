package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.first_time_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.Fragment_FirstActivity_First;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.Fragment_FirstActivity_Setting;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.ViewPagerAdapterFirstActivity;
import com.rd.PageIndicatorView;

public class FirstTimeActivity extends AppCompatActivity implements Fragment_FirstActivity_Setting.OnChangeListener, ViewPager.OnPageChangeListener {
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

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onContinueClicked(boolean data) {
        checkedItem = data;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position == 2){
            if(!checkedItem){
                viewPager.setCurrentItem(position - 1);
                dotsPager.setSelected(position - 1);
                Toast.makeText(this,"You must Filled the Required CheckBox First!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if(position == 2){
            if(!checkedItem){

                viewPager.setCurrentItem(position - 1);
                dotsPager.setSelected(position - 1);
                Toast.makeText(this,"You must Filled the Required CheckBox First!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}