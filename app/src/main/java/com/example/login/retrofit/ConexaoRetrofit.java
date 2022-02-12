package com.example.login.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexaoRetrofit {

    private static ConexaoRetrofit instance;

    private Retrofit retrofit;

    private ConexaoRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.4:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public synchronized static ConexaoRetrofit getInstance() {
        if (instance == null) {
            instance = new ConexaoRetrofit();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
