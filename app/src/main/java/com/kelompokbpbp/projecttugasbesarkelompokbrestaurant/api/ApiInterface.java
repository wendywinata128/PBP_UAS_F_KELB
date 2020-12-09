package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.AllUserResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.CartResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MenuResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MessageResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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

    @GET("/api/users")
    Call<AllUserResponse> getUserList();

    @GET("/api/menu")
    Call<MenuResponse> menuList();

    @GET("/api/carts/{username}")
    Call<CartResponse> getCartsByUsername(@Path("username") String username);

    @PUT("/api/user/edit/{id}")
    @FormUrlEncoded
    Call<UserResponse> getUserAfterEdit(@Path("id") String id,
                                        @Field("name") String name,
                                        @Field("address") String address,
                                        @Field("email") String email,
                                        @Field("photo") String photo);

    @POST("/api/cart/insert")
    @FormUrlEncoded
    Call<MessageResponse> insertCart(@Field("username") String username,
                                     @Field("menu_name") String menu_name,
                                     @Field("status") String status,
                                     @Field("price") String price,
                                     @Field("amount") String amount);

    @POST("/api/carts/{id}/incrementCount")
    Call<MessageResponse> incrementItemCart(@Path("id") String id);

    @POST("/api/carts/{id}/decrementCount")
    Call<MessageResponse> decrementItemCart(@Path("id") String id);

    @POST("/api/carts/{id}/delete")
    Call<MessageResponse> deleteItemCart(@Path("id") String id);

    @GET
    Call<MessageResponse> verifyEmail(@Url String url);

    @POST("/api/transaction/{username}")
    @FormUrlEncoded
    Call<MessageResponse> payTransaction(@Path("username") String username,
                                         @Field("method") String method);

    @POST("/api/menu/insert")
    @FormUrlEncoded
    Call<MessageResponse> insertMenu(@Field("name") String name,
                                  @Field("price") String price,
                                  @Field("type") String type,
                                  @Field("photo") String photo);

    @PUT("/api/menu/update/{id}")
    @FormUrlEncoded
    Call<MessageResponse> updateMenu(@Path("id") String id,
                                  @Field("name") String name,
                                  @Field("price") String price,
                                  @Field("type") String type,
                                  @Field("photo") String photo);

    @POST("/api/menu/delete/{id}")
    Call<MessageResponse> deleteMenu(@Path("id") String id);
}
