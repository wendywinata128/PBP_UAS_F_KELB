package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;
//Kelompok PBP B , Wendy , Elvina , Wenny

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.databinding.DataBindingUtil;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.bottomNavigation);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}