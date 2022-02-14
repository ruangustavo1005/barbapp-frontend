package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.login.models.Reserve;
import com.example.login.models.User;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.ReserveService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReserveDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_details);

        getSupportActionBar().hide();

        User userLogged = MainActivity.getUserLogged();
        int reserveId = Integer.parseInt(getIntent().getStringExtra("reserveId"));
        ReserveService reserveService = ConexaoRetrofit.getInstance().getRetrofit().create(ReserveService.class);

        Button buttonAccept = findViewById(R.id.btnAcceptReserve);
        buttonAccept.setOnClickListener((View v) -> {
            reserveService.acceptReserve(userLogged.getToken(), reserveId).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Reserva aceita", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), home.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro ao tentar aceitar a reserva: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        Button buttonRecuse = findViewById(R.id.btnRecuseReserve);
        buttonRecuse.setOnClickListener((View v) -> {
            reserveService.recuseReserve(userLogged.getToken(), reserveId).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Reserva recusada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), home.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro ao tentar recusar a reserva: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}