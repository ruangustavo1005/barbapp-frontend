package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class AddReserve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reserve);

        getSupportActionBar().hide();

        String barbershopId = getIntent().getStringExtra("barbershopId");

        TextView data = findViewById(R.id.txtData);
        TextView hora = findViewById(R.id.txtTime);

        SimpleMaskFormatter smfData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwData = new MaskTextWatcher(data, smfData);
        data.addTextChangedListener(mtwData);

        SimpleMaskFormatter smfHora = new SimpleMaskFormatter("NN:NN:NN");
        MaskTextWatcher mtwHora = new MaskTextWatcher(hora, smfHora);
        hora.addTextChangedListener(mtwHora);

        Button addReserve = findViewById(R.id.btnCreateReserve);
        addReserve.setOnClickListener((View v) -> {
            //POST /reserve/
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });

    }
}