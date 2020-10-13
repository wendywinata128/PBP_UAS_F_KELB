package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.main_activity;
//Kelompok PBP B , Wendy , Elvina , Wenny

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.databinding.DataBindingUtil;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AppPreference appPreference = new AppPreference(this.getApplicationContext());
        appPreference.setFirstRun();

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this,"Channel 1")
                .setContentTitle("Restoku")
                .setContentText("Selamat Datang " + appPreference.getLoginUsername())
                .setSmallIcon(R.drawable.logo_restoku);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0,notificationCompat.build());


        BottomNavigationView navView = findViewById(R.id.bottomNavigation);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}