package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;

public class EditAddAddressFragment extends Fragment {
    private Boolean editAddress;
    private TextInputLayout tvAdressName,tvAddressDetail;
    private String addressName,addressDetail;
    private MaterialButton btnCancel,btnOk;

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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editAddress = getArguments().getBoolean("Edit Address",false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_profile, new ProfilFragment()).commit();
            }
        });

        if(editAddress){

        }else{
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressName = tvAdressName.getEditText().getText().toString();
                    addressDetail = tvAddressDetail.getEditText().getText().toString();
                    addAddress();
                    btnCancel.performClick();
                }
            });
        }
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
                        .insert(new Alamat(username,0,0,addressName,addressDetail));
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
}