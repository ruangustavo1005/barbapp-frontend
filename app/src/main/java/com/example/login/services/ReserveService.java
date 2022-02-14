package com.example.login.services;

import com.example.login.models.Product;
import com.example.login.models.Reserve;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReserveService {

    @POST("reserve")
    public Call<Reserve> create(@Header("Authorization") String authorization, @Body Reserve reserve);

    @PUT("reserve/{id}")
    public Call<Reserve> update(@Header("Authorization") String authorization, @Body Reserve reserve, @Path("id") int reserveId);

    @DELETE("reserve/{id}")
    public Call<String> delete(@Header("Authorization") String authorization, @Path("id") int reserveId);

    @GET("reserve")
    public Call<List<Reserve>> list(@Header("Authorization") String authorization);

    @GET("reserve/barber/{id}")
    public Call<List<Reserve>> listFromBarber(@Header("Authorization") String authorization, @Path("id") int barberId);

    @GET("reserve/barbershop/{id}")
    public Call<List<Reserve>> listFromBarbershop(@Header("Authorization") String authorization, @Path("id") int barbershopId);

    @GET("reserve/user/{id}")
    public Call<List<Reserve>> listFromUser(@Header("Authorization") String authorization, @Path("id") int userId);

    @GET("reserve/user/{id}/last")
    public Call<Reserve> getLastFromUser(@Header("Authorization") String authorization, @Path("id") int userId);

    @PATCH("reserve/{id}/accept")
    public Call<Object> acceptReserve(@Header("Authorization") String authorization, @Path("id") int reserveId);

    @PATCH("reserve/{id}/recuse")
    public Call<Object> recuseReserve(@Header("Authorization") String authorization, @Path("id") int reserveId);

}
