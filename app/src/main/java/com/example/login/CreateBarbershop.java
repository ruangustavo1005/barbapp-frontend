package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateBarbershop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_barbershop);

        getSupportActionBar().hide();

        Button buttonAdd = findViewById(R.id.btnAddBarbershop);
        buttonAdd.setOnClickListener((View v) -> {
            int idUsuario = MainActivity.getUserLogged().getId();
            /// POST users/idUsuario/barbershop
            Intent intent =  new Intent(this, home.class);
            startActivity(intent);
        });


    }
}