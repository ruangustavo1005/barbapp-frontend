package com.example.login.services;

import com.example.login.models.Barbershop;
import com.example.login.models.Invite;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InviteService {

    @POST("invite")
    public Call<Barbershop> create(@Header("Authorization") String authorization, @Body Invite invite);

}
