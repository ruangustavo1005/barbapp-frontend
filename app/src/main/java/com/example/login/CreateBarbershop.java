package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.models.Barbershop;
import com.example.login.models.User;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.BarbershopService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateBarbershop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_barbershop);

        getSupportActionBar().hide();

        Button buttonAdd = findViewById(R.id.btnAddBarbershop);
        buttonAdd.setOnClickListener((View v) -> {
            Barbershop barbershop = new Barbershop();

            TextView name = findViewById(R.id.txtNomeBarbearia);
            if (name.length() > 0) {
                barbershop.setName(name.getText().toString());
            }
            else {
                return;
            }

            TextView phone = findViewById(R.id.txtTelefoneBarbearia);
            if (phone.length() > 0) {
                barbershop.setPhone(phone.getText().toString());
            }
            else {
                return;
            }

            TextView address = findViewById(R.id.txtTelefoneBarbearia);
            if (address.length() > 0) {
                barbershop.setAddress(address.getText().toString());
            }
            else {
                return;
            }

            User userLogged = MainActivity.getUserLogged();

            BarbershopService barbershopService = ConexaoRetrofit.getInstance().getRetrofit().create(BarbershopService.class);
            barbershopService.create(userLogged.getToken(), barbershop, userLogged.getId()).enqueue(new Callback<Barbershop>() {
                @Override
                public void onResponse(Call<Barbershop> call, Response<Barbershop> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Barbearia criada", Toast.LENGTH_SHORT).show();
                        Intent intent =  new Intent(v.getContext(), home.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Barbershop> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro ao tentar inserir a barbearia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
}