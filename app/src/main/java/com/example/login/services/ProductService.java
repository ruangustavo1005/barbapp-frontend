package com.example.login.services;

import com.example.login.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    @POST("barbershop/{id}/products")
    public Call<Product> create(@Header("Authorization") String authorization, @Body Product product, @Path("id") int barbershopId);

    @PUT("products/{id}")
    public Call<Product> update(@Header("Authorization") String authorization, @Body Product product, @Path("id") int productId);

    @GET("barbershop/{id}/products")
    public Call<List<Product>> list(@Header("Authorization") String authorization, @Path("id") int barbershopId);

    @GET("products/{id}")
    public Call<List<Product>> listFromId(@Header("Authorization") String authorization, @Path("id") int productId);

    @DELETE("products/{id}")
    public Call<String> delete(@Header("Authorization") String authorization, @Path("id") int productId);

}
