package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest.ActivityUtil;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest.RegisterPresenter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest.RegisterService;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest.RegisterView;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity.LoginActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

public class Fragment_Register_1 extends Fragment implements RegisterView {
    private MaterialButton btnContinue,btnLogin;
    private TextInputLayout tvFullName,tvEmail;
    private RegisterPresenter presenter;

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
        tvEmail = view.findViewById(R.id.textInputEmail);
        btnLogin = view.findViewById(R.id.btnMoveLogin);

        presenter = new RegisterPresenter(this,new RegisterService());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onContinueClicked();
                /*if(CheckValidation()){
                    Fragment fragment = new Fragment_register_2();
                    Bundle bundle = new Bundle();
                    bundle.putString("Full Name",tvFullName.getEditText().getText().toString());
                    bundle.putString("Email",tvEmail.getEditText().getText().toString());
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.register_container,fragment).addToBackStack(null).commit();
                }*/
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Fragment_Register_1.this.getContext(), LoginActivity.class));
            }
        });
    }

    /*private boolean CheckValidation(){
        boolean validation = true;

        if(tvFullName.getEditText().getText().toString().isEmpty()){
            tvFullName.setError("Full name cannot be empty");
            validation = false;
        }

        return validation;
    }*/

    @Override
    public String getNama() {
        return tvFullName.getEditText().getText().toString();
    }

    @Override
    public void showNamaError(String message) {
        tvFullName.getEditText().setError(message);
    }

    @Override
    public String getEmail() {
        return tvEmail.getEditText().getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        tvEmail.getEditText().setError(message);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void showUsernameError(String message) {

    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void showPasswordError(String message) {

    }

    @Override
    public String getMatchPass() {
        return null;
    }

    @Override
    public void showMatchPassError(String message) {

    }

    @Override
    public String getPhotoProfile() {
        return null;
    }

    @Override
    public void showPhotoError(String message) {

    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void showAddressError(String message) {

    }

    @Override
    public void startMainActivity() {

    }

    @Override
    public void startLoginActivity() {
    }

    @Override
    public void startRegisterActivity() {
    }

    @Override
    public void startUserProfileActivity(User user) {

    }

    @Override
    public void showRegisterError(String message) {

    }

    @Override
    public void showErrorResponse(String message) {

    }

    @Override
    public void continueValidationSuccess() {
        Fragment fragment = new Fragment_register_2();
        Bundle bundle = new Bundle();
        bundle.putString("Full Name",tvFullName.getEditText().getText().toString());
        bundle.putString("Email",tvEmail.getEditText().getText().toString());
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.register_container,fragment).addToBackStack(null).commit();
    }
}