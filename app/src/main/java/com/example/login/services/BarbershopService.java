package com.example.login.services;

import com.example.login.models.Barbershop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BarbershopService {

    @POST("users/{id}/barbershop")
    public Call<Barbershop> create(@Header("Authorization") String authorization, @Body Barbershop barbershop, @Path("id") int userId);

    @PUT("barbershop/{id}")
    public Call<Barbershop> update(@Header("Authorization") String authorization, @Body Barbershop barbershop, @Path("id") int barbershopId);

    @GET("barbershop")
    public Call<List<Barbershop>> list(@Header("Authorization") String authorization);

    @GET("users/{id}/barbershop")
    public Call<List<Barbershop>> listFromUser(@Header("Authorization") String authorization, @Path("id") int userId);

    @DELETE("barbershop/{id}")
    public Call<String> delete(@Header("Authorization") String authorization, @Path("id") int barbershopId);

    @GET("barbershop/{id}")
    public Call<Barbershop> get(@Header("Authorization") String authorization, @Path("id") int barbershopId);

}
