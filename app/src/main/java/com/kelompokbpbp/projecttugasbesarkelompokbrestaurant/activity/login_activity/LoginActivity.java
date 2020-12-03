package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.main_activity.MainActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity.RegisterActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MessageResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.UserResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin , btnRegister;
    private TextInputLayout tvUsername,tvPassword;
    private ProgressBar pbLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        tvUsername = findViewById(R.id.textInputUsername);
        tvPassword = findViewById(R.id.textInputPassword);

        pbLogin = findViewById(R.id.pb_login);

        Uri uri = getIntent().getData();

        if(uri != null){
            AppPreference appPreference = new AppPreference(getApplicationContext());

            if(appPreference.getUserToken() != null){
                appPreference.setUserToken(null);
                appPreference.setLoginUsername(null);
            }

            String path = uri.getPath();
            String query = uri.getQuery();
            Call<MessageResponse> client = RetrofitClient.getRetrofit().verifyEmail(path+"?"+query);

            client.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"User Verified Success",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"User Verified Failed",Toast.LENGTH_SHORT).show();
                    }

                    Log.d("Verify",path +"?"+ query);
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Internet Problem",Toast.LENGTH_SHORT).show();
                }
            });
        }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserLogin();
            }
        });
    }

    private void checkUserLogin(){
        String username = tvUsername.getEditText().getText().toString().trim();
        String password = tvPassword.getEditText().getText().toString();
        Call<UserResponse> login = RetrofitClient.getRetrofit().userLogin(username,password);

        pbLogin.setVisibility(View.VISIBLE);
        login.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                pbLogin.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Berhasil : " + response.body().getMessage(),Toast.LENGTH_SHORT).show();

                    AppPreference appPreference = new AppPreference(getApplicationContext());

                    appPreference.setUserToken(response.body().getToken());
                    appPreference.setLoginUsername(response.body().getData().getUsername());

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    try {
                        JSONObject error = null;
                        if (response.errorBody() != null) {
                            error = new JSONObject(response.errorBody().string());
                        }
                        Toast.makeText(LoginActivity.this,"Login Gagal : " + error.getString("message"),Toast.LENGTH_SHORT).show();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                pbLogin.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,"Internet Problem ?",Toast.LENGTH_SHORT).show();
            }
        });
    }
}