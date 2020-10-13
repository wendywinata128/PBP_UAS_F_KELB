package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.edit_add_address_activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.geo_location_activity.GeoLocationActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;

public class EditAddAddressActivity extends AppCompatActivity {
    private Boolean editAddress;
    private TextInputLayout tvAdressName,tvAddressDetail;
    private TextView tvCaption;
    private String addressName,addressDetail,addressLocation="-";
    private MaterialButton btnCancel,btnOk,btnSetAddress, btnCancelEdit;
    private Alamat dataAlamat;
    public static final int GEO_LOCATION_RESULT = 11001;

    public EditAddAddressActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_add_address);


        tvAdressName = findViewById(R.id.input_name_layout);
        tvAddressDetail = findViewById(R.id.input_address_layout);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.btn_update);
        btnSetAddress = findViewById(R.id.btnSetAddress);
        tvCaption = findViewById(R.id.tv_caption);
        btnCancelEdit = findViewById(R.id.btn_cancel_2);

        editAddress = getIntent().getBooleanExtra("Edit Address",false);

        btnSetAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAddAddressActivity.this, GeoLocationActivity.class);
                intent.putExtra("My Location",addressLocation);
                startActivityForResult(intent,GEO_LOCATION_RESULT);
            }
        });

        if(editAddress){
            dataAlamat = (Alamat) getIntent().getSerializableExtra("Address Data");
            btnCancel.setText(R.string.delete_address);
            btnOk.setText(R.string.update_address);
            tvCaption.setText(R.string.edit_address);
            tvAdressName.getEditText().setText(dataAlamat.getAddressName());
            tvAddressDetail.getEditText().setText(dataAlamat.getAddressDetail());
            addressLocation = dataAlamat.getJsonAddress();

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteAddress();
                    finish();
                }
            });

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(CheckValidationAddress()) {
                        addressName = tvAdressName.getEditText().getText().toString();
                        addressDetail = tvAddressDetail.getEditText().getText().toString();
                        editAddress();
                        finish();
                    }
                }
            });

            btnCancelEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            Toast.makeText(EditAddAddressActivity.this,addressLocation,Toast.LENGTH_SHORT).show();
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(CheckValidationAddress())
                    {
                        addressName = tvAdressName.getEditText().getText().toString();
                        addressDetail = tvAddressDetail.getEditText().getText().toString();
                        addAddress();
                        finish();
                    }
                }
            });
            btnCancelEdit.setVisibility(View.GONE);
        }


    }

    private void addAddress(){
        class AddAddress extends AsyncTask<Void,Void,Void>{
            AppPreference appPreference = new AppPreference(getApplicationContext());
            String username = appPreference.getLoginUsername();
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .addressDAO()
                        .insert(new Alamat(username,addressLocation,addressName,addressDetail));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }
        }

        AddAddress addAddress = new AddAddress();
        addAddress.execute();
    }

    private void editAddress(){
        class EditAddress extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                dataAlamat.setAddressName(addressName);
                dataAlamat.setAddressDetail(addressDetail);
                dataAlamat.setJsonAddress(addressLocation);

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .addressDAO()
                        .update(dataAlamat);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        EditAddress editAddress = new EditAddress();
        editAddress.execute();
    }

    private void deleteAddress(){
        class DeleteAddress extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .addressDAO()
                        .delete(dataAlamat);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        DeleteAddress deleteAddress = new DeleteAddress();
        deleteAddress.execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GEO_LOCATION_RESULT){
            if(resultCode == GEO_LOCATION_RESULT){
                addressLocation = data.getStringExtra("Address Data");

            }
        }
    }

    private boolean CheckValidationAddress(){
        boolean validation = true;

        if(tvAdressName.getEditText().getText().toString().isEmpty()){
            tvAdressName.setError("Address name cannot be empty");
            validation = false;
        }

        if(tvAddressDetail.getEditText().getText().toString().isEmpty()){
            tvAddressDetail.setError("Address detail cannot be empty");
            validation = false;
        }
        return validation;
    }
}