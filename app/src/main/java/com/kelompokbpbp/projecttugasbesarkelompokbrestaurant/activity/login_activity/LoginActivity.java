package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.main_activity.MainActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity.RegisterActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin , btnRegister;
    private TextInputLayout tvUsername,tvPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        tvUsername = findViewById(R.id.textInputUsername);
        tvPassword = findViewById(R.id.textInputPassword);

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
        class CheckUser extends AsyncTask<Void,Void, User>{

            @Override
            protected User doInBackground(Void... voids) {

                User data = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getUser(tvUsername.getEditText().getText().toString(),
                                tvPassword.getEditText().getText().toString());

                return data;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                if(user == null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                    builder.setMessage("Username or password is incorrect!")
                    .show();
                }else{
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        }
        CheckUser checkUser = new CheckUser();
        checkUser.execute();
    }
}