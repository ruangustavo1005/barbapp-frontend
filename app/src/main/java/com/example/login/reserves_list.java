package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.models.Reserve;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.ReserveService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class reserves_list extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserves_list);

        context = this;

        getSupportActionBar().hide();

        ReserveService reserveService = ConexaoRetrofit.getInstance().getRetrofit().create(ReserveService.class);
        reserveService.listFromUser(MainActivity.getUserLogged().getToken(), MainActivity.getUserLogged().getId()).enqueue(new Callback<List<Reserve>>() {
            @Override
            public void onResponse(Call<List<Reserve>> call, Response<List<Reserve>> response) {
                if (response.code() == 200 && response.body() != null) {
                    ListView list = findViewById(R.id.reservesList);
                    List<String> reservas = new ArrayList<>();

                    for (Reserve reserve : response.body()) {
                        reservas.add((new SimpleDateFormat("dd/MM/yyyy HH:mm")).format(reserve.getSchedule())
                                .concat(" - ")
                                .concat(" na barbearia \"")
                                .concat(reserve.getBarbershop().getName())
                                .concat("\", com o barbeiro \"")
                                .concat(reserve.getBarber().getName())
                                .concat("\" (")
                                .concat(reserve.getDescriptionSituation())
                                .concat(")"));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, reservas);
                    list.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Reserve>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar listar as reservas: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ListView list = findViewById(R.id.reservesList);
        list.setOnItemClickListener((parent, view, position, id) -> {
            if(MainActivity.getUserLogged().isIs_barber()) {
                Intent intent = new Intent(this, ReserveDetails.class);
                startActivity(intent);
            }
        });

    }
}