package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.models.Barbershop;

public class AddProduct extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        context = this;

        getSupportActionBar().hide();
        getSupportActionBar().hide();

        Button button = findViewById(R.id.btnAdd);
        button.setOnClickListener((View v) -> {
            Intent intent = new Intent(v.getContext(), barbershopDetails.class);
            startActivity(intent);
        });

    }
}