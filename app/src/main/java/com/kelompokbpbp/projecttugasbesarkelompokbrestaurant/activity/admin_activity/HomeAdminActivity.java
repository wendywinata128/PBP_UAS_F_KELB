package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.ProfilFragment;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity.LoginActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;

public class HomeAdminActivity extends AppCompatActivity {
    private CardView cvSetMenu,cvReportTransaction;
    private MaterialButton btnLogoutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        cvSetMenu = findViewById(R.id.cvSetMenu);
        cvReportTransaction = findViewById(R.id.cvReportTransaction);
        btnLogoutAdmin = findViewById(R.id.btnLogoutAdmin);

        cvSetMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, SetMenuActivity.class);
                startActivity(intent);
            }
        });

        cvReportTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdminActivity.this, ReportTransactionActivity.class);
                startActivity(intent);
            }
        });

        btnLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertForLogout().show();
            }
        });
    }

    public Dialog alertForLogout() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

        builder.setMessage(R.string.confirmation)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AppPreference appPreference = new AppPreference(HomeAdminActivity.this);
                        appPreference.setLoginUsername(null);
                        appPreference.setUserToken(null);
                        Intent toLogin = new Intent(HomeAdminActivity.this, LoginActivity.class);
                        toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(toLogin);
                        finish();
                    }
                });
        return builder.create();
    }
}