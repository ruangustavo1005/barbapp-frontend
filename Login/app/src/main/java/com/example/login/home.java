package com.example.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ListView list = findViewById(R.id.barbershopList);
        List<String> barbearias = new ArrayList<>();
        barbearias.add("Barbearia do Zeca");
        barbearias.add("Sebastião Cortes");
        barbearias.add("Juarez barbers");
        barbearias.add("Barbearia do Zeca");
        barbearias.add("Sebastião Cortes");
        barbearias.add("Juarez barbers");
        barbearias.add("Barbearia do Zeca");
        barbearias.add("Sebastião Cortes");
        barbearias.add("Juarez barbers");
        barbearias.add("Barbearia do Zeca");
        barbearias.add("Sebastião Cortes");
        barbearias.add("Juarez barbers");
        barbearias.add("Barbearia do Zeca");
        barbearias.add("Sebastião Cortes");
        barbearias.add("Juarez barbers");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, barbearias);
        list.setAdapter(adapter);

        Button btn = findViewById(R.id.btnReserves);
        btn.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, reserves_list.class);
            startActivity(intent);
        });
    }
}