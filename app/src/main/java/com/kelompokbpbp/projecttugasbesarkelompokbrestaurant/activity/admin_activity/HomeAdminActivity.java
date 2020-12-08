package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;

public class HomeAdminActivity extends AppCompatActivity {
    private CardView cvSetMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        cvSetMenu = findViewById(R.id.cvSetMenu);

        cvSetMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, SetMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}