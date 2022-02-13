package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReserveDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_details);

        getSupportActionBar().hide();

        Button buttonAccept = findViewById(R.id.btnAcceptReserve);
        buttonAccept.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });

        Button buttonRecuse = findViewById(R.id.btnRecuseReserve);
        buttonRecuse.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });
    }
}