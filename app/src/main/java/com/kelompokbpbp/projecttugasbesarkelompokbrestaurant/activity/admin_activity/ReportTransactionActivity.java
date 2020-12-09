package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.admin_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity.Fragment_Register_1;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.pdf.PdfFragment;

public class ReportTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_transaction);

        getSupportFragmentManager().beginTransaction().replace(R.id.reportTransactionContainer,new PdfFragment()).commit();
    }
}