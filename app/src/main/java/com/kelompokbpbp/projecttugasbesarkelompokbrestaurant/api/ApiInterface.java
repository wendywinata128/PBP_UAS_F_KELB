package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.CartResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MenuResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST("/api/user/login")
    @FormUrlEncoded
    Call<UserResponse> userLogin(@Field("username") String username,
                                 @Field("password") String password);

    @POST("/api/user/register")
    @FormUrlEncoded
    Call<UserResponse> userRegister(@Field("name") String name,
                                    @Field("username") String username,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("photo") String photo,
                                    @Field("address") String address);

    @GET("/api/user")
    Call<UserResponse> userDetails(@Header("Authorization") String header);

    @GET("/api/menu")
    Call<MenuResponse> menuList();

    @GET("/api/carts/{username}")
    Call<CartResponse> getCartsByUsername(@Path("username") String username);



}
