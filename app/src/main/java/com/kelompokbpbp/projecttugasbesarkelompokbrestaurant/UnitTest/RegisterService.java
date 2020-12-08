package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.UserResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterService {
    public void register(final RegisterView view, String name, String username, String email,String password, String photo, String address, final
    RegisterCallback callback){
        Call<UserResponse> userDAOCall = RetrofitClient.getRetrofit().userRegister(name,username,email,password,photo,address);
        userDAOCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                System.out.println("Masuk di RegisterService");
                if(response.isSuccessful()){
                    if(response.body().getMessage().equalsIgnoreCase("Berhasil Register"
                    )){
                        callback.onSuccess(true, response.body().getData());
                    }
                    else{
                        callback.onError();
                        view.showRegisterError(response.body().getMessage());
                    }
                }else{
                    callback.onError();
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        view.showErrorResponse(error.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }
    public Boolean getValid(final RegisterView view, String name, String username, String email,String password, String photo, String address){
        final Boolean[] bool = new Boolean[1];
        register(view, name,username,email,password, photo, address, new RegisterCallback() {
            @Override
            public void onSuccess(boolean value, User user) {
                bool[0] = true;
            }
            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}
