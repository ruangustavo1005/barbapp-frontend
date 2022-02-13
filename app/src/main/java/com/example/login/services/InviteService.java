package com.example.login.services;

import com.example.login.models.Barbershop;
import com.example.login.models.Invite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InviteService {

    @POST("invite")
    public Call<Invite> create(@Header("Authorization") String authorization, @Body Invite invite);

    @GET("invite/{id}")
    public Call<Invite> getById(@Header("Authorization") String authorization, @Path("id") int inviteId);

    @GET("invite/barber/{id}")
    public Call<List<Invite>> getAllFromBarber(@Header("Authorization") String authorization, @Path("id") int barberId);

    @PUT("invite/{id}/accept")
    public Call<Object> accept(@Header("Authorization") String authorization, @Path("id") int inviteId);

    @PUT("invite/{id}/recuse")
    public Call<Object> recuse(@Header("Authorization") String authorization, @Path("id") int inviteId);

}
