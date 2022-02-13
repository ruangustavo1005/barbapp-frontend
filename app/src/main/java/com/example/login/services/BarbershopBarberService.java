package com.example.login.services;

import com.example.login.models.Barbershop;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BarbershopBarberService {

    @POST("barbershop/{barbershop}/barber/{barber}")
    public Call<Object> addBarber(@Header("Authorization") String authorization, @Path("barbershop") int barbershopId, @Path("barber") int barberId);

}
