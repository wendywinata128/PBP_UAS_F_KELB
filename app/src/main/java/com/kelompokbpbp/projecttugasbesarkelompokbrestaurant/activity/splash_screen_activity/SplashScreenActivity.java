package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.splash_screen_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.admin_activity.HomeAdminActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.first_time_activity.FirstTimeActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity.LoginActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.main_activity.MainActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LinearLayout layoutContent = findViewById(R.id.layoutContent);

        layoutContent.setAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.anim_splash_screen));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppPreference appPreference = new AppPreference(SplashScreenActivity.this);
                if(appPreference.getFirstRun()){
                    Intent intent = new Intent(SplashScreenActivity.this, FirstTimeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    if(appPreference.getLoginUsername() == null){
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent;
                        if(!appPreference.getLoginUsername().equals("wendy128")){
                            intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        }else{
                            intent = new Intent(SplashScreenActivity.this, HomeAdminActivity.class);
                        }
                        startActivity(intent);
                    }
                    finish();
                }
            }
        },3000);
        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
    }
}