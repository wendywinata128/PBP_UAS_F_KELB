package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest;

import android.content.Context;
import android.content.Intent;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity.LoginActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.main_activity.MainActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity.Fragment_Register_1;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity.Fragment_register_2;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity.RegisterActivity;

public class ActivityUtil {
    private Context context;

    public void startMainActivity() {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
