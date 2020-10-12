package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.geo_location_activity.GeoLocationActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

public class EditAddAddressFragment extends Fragment {
    private Boolean editAddress;
    private TextInputLayout tvAdressName,tvAddressDetail;
    private TextView tvCaption;
    private String addressName,addressDetail,addressLocation="-";
    private MaterialButton btnCancel,btnOk,btnSetAddress;
    private Alamat dataAlamat;
    public static final int GEO_LOCATION_RESULT = 11001;

    public EditAddAddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_add_address,container,false);
        tvAdressName = view.findViewById(R.id.input_name_layout);
        tvAddressDetail = view.findViewById(R.id.input_address_layout);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnOk = view.findViewById(R.id.btn_update);
        btnSetAddress = view.findViewById(R.id.btnSetAddress);
        tvCaption = view.findViewById(R.id.tv_caption);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editAddress = getArguments().getBoolean("Edit Address",false);

        btnSetAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAddAddressFragment.this.getContext(), GeoLocationActivity.class);
                intent.putExtra("My Location",addressLocation);
                startActivityForResult(intent,GEO_LOCATION_RESULT);
            }
        });

        if(editAddress){
            dataAlamat = (Alamat) getArguments().getSerializable("Address Data");
            btnCancel.setText(R.string.delete_address);
            btnOk.setText(R.string.update_address);
            tvCaption.setText(R.string.edit_address);
            tvAdressName.getEditText().setText(dataAlamat.getAddressName());
            tvAddressDetail.getEditText().setText(dataAlamat.getAddressDetail());
            addressLocation = dataAlamat.getJsonAddress();
            editAddress();

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteAddress();
                    endTransaction();
                }
            });

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressName = tvAdressName.getEditText().getText().toString();
                    addressDetail = tvAddressDetail.getEditText().getText().toString();
                    editAddress();
                    endTransaction();
                }
            });
        }else{
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    endTransaction();
                }
            });
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressName = tvAdressName.getEditText().getText().toString();
                    addressDetail = tvAddressDetail.getEditText().getText().toString();
                    addAddress();
                    endTransaction();
                }
            });
        }
    }

    private void endTransaction(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_profile, new ProfilFragment()).commit();
    }

    private void addAddress(){
        class AddAddress extends AsyncTask<Void,Void,Void>{
            AppPreference appPreference = new AppPreference(getActivity().getApplicationContext());
            String username = appPreference.getLoginUsername();
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getActivity().getApplicationContext())
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

                DatabaseClient.getInstance(getActivity().getApplicationContext())
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
                DatabaseClient.getInstance(getActivity().getApplicationContext())
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
}