package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.main_activity.MainActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;


public class Fragment_register_2 extends Fragment {

    private TextInputLayout tvUsername,tvPassword,tvMatchPassword;
    private String fullName,phoneNumber;
    private MaterialButton btnRegister;


    public Fragment_register_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_2, container, false);
        tvUsername = view.findViewById(R.id.textInputUsername);
        tvPassword = view.findViewById(R.id.textInputPassword);
        tvMatchPassword = view.findViewById(R.id.textInputMatchPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        fullName = getArguments().getString("Full Name");
        phoneNumber = getArguments().getString("Phone Number");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation()) {
                    registerUser();
                }
            }
        });
    }

    private boolean checkValidation(){
        boolean validation = true;

        if(tvUsername.getEditText().getText().toString().contains(" ") ||
            tvUsername.getEditText().getText().toString().isEmpty()){
            tvUsername.setError("Username Cannot contains space");
            validation = false;
        }

        if(tvPassword.getEditText().getText().toString().length() < 6){
            tvPassword.setError("Password Must be at least 6 digit");
            validation = false;
        }

        if(!(tvPassword.getEditText().getText().toString()
                .equals(tvMatchPassword.getEditText().getText().toString()))){
            tvMatchPassword.setError("Password is not same");
            validation = false;
        }

        return validation;
    }

    private void registerUser(){
        class RegisterUser extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                User data = new User(fullName, phoneNumber, tvUsername.getEditText().getText().toString(),
                        tvPassword.getEditText().getText().toString());

                DatabaseClient.getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .insert(data);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(Fragment_register_2.this.getContext(), "User Registered Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Fragment_register_2.this.getContext(), MainActivity.class));
                getActivity().finishAndRemoveTask();
            }
        }
        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
    }
}