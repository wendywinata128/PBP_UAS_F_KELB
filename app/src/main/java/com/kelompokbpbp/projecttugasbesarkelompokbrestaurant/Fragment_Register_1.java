package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class Fragment_Register_1 extends Fragment {
    private MaterialButton btnContinue;
    private TextInputLayout tvFullName,tvPhoneNumber;

    public Fragment_Register_1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__register_1, container, false);
        btnContinue = view.findViewById(R.id.btnContinue);
        tvFullName = view.findViewById(R.id.textInputFullName);
        tvPhoneNumber = view.findViewById(R.id.textInputNumber);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckValidation()){
                    Fragment fragment = new Fragment_register_2();
                    Bundle bundle = new Bundle();
                    bundle.putString("Full Name",tvFullName.getEditText().getText().toString());
                    bundle.putString("Phone Number",tvFullName.getEditText().getText().toString());
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.register_container,fragment).addToBackStack(null).commit();
                }
            }
        });
    }

    private boolean CheckValidation(){
        boolean validation = true;

        if(tvFullName.getEditText().getText().toString().isEmpty()){
            tvFullName.setError("Full Name Cannot be Empty");
            validation = false;
        }

        if(tvPhoneNumber.getEditText().getText().toString().isEmpty() ||
                tvPhoneNumber.getEditText().getText().toString().length() < 9 ){
            tvPhoneNumber.setError("Phone Number Must be 8 or more digits");
            validation = false;
        }
        return validation;
    }

}