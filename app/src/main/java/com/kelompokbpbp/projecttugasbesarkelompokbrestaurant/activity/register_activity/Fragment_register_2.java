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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity.LoginActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.main_activity.MainActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.UserResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_register_2 extends Fragment {

    private TextInputLayout tvUsername,tvPassword,tvMatchPassword;
    private String fullName,email;
    private MaterialButton btnRegister;
    private ProgressBar pbRegister;


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
        pbRegister = view.findViewById(R.id.pb_register);
        btnRegister = view.findViewById(R.id.btnRegister);
        fullName = getArguments().getString("Full Name");
        email = getArguments().getString("Email");
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
            tvUsername.setError("Username cannot contains space");
            validation = false;
        }

        if(tvPassword.getEditText().getText().toString().length() < 6){
            tvPassword.setError("Password must be at least 6 digit");
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
        User data = new User(fullName, email, tvUsername.getEditText().getText().toString(),
                tvPassword.getEditText().getText().toString(), "-");

        Call<UserResponse> client = RetrofitClient.getRetrofit().userRegister(
                data.getNama(),
                data.getUsername(),
                data.getEmail(),
                data.getPassword(),
                "avatar_default.png",
                data.getUsername());

        pbRegister.setVisibility(View.VISIBLE);
        client.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    pbRegister.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"Success : "+response.body().getMessage(),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                }else{
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(),error.optString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Internet Problem ?",Toast.LENGTH_SHORT).show();
            }
        });
    }
}