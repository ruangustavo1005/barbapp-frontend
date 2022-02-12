package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class barbershopDetails extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbershop_details);

        ListView list = findViewById(R.id.productsList);
        List<String> produtos = new ArrayList<>();
        produtos.add("Pomada para cabelo");
        produtos.add("Spray fixador");
        produtos.add("Camisa Power");
        produtos.add("Pomada para cabelo");
        produtos.add("Spray fixador");
        produtos.add("Camisa Power");
        produtos.add("Pomada para cabelo");
        produtos.add("Spray fixador");
        produtos.add("Camisa Power");
        produtos.add("Pomada para cabelo");
        produtos.add("Spray fixador");
        produtos.add("Camisa Power");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, produtos);
        list.setAdapter(adapter);
    }
}