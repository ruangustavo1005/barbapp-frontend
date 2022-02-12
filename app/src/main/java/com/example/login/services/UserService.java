package com.example.login.services;

import com.example.login.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("users")
    public Call<User> create(@Body User user);

    @GET("users")
    public Call<List<User>> list(@Header("Authorization") String authorization);

    @GET("usersBarbers")
    public Call<List<User>> listBarbers(@Header("Authorization") String authorization);

    @POST("login")
    public Call<User> login(@Body User user);

    @POST("forgot_password")
    public Call<Object> forgotPassword(@Body User user);

    @POST("reset_password")
    public Call<Object> resetPassword(@Body User user);

}
