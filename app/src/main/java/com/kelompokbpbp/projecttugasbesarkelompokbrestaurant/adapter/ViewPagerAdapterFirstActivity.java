package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.first_time_activity.Fragment_FirstActivity_First;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.first_time_activity.Fragment_FirstActivity_Setting;

public class ViewPagerAdapterFirstActivity extends FragmentPagerAdapter {
    public ViewPagerAdapterFirstActivity(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch(position){
            case 0 :
                fragment = new Fragment_FirstActivity_First();
                break;
            case 1 :
                fragment = new Fragment_FirstActivity_Setting();
                break;
        }

        assert fragment != null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
