package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class reserves_list extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserves_list);

        ListView list = findViewById(R.id.productsList);
        List<String> reservas = new ArrayList<>();
        reservas.add("Reserva com Ciclano às 15h00 do dia 15/11/2021 em Barbearia do Zé");
        reservas.add("Reserva com Beltrano às 13h00 do dia 11/10/2021 em Juarez Cortes");
        reservas.add("Reserva com Beltrano às 16h00 do dia 05/09/2021 em Juarez Cortes");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, reservas);
        list.setAdapter(adapter);
    }
}