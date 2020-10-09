package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportFragmentManager().beginTransaction().replace(R.id.register_container,new Fragment_Register_1()).commit();
    }
}