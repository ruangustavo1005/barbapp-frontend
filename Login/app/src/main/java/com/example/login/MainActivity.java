package com.example.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button cadastro = this.findViewById(R.id.btnCadastro);
        cadastro.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, cadastro.class);
            startActivity(intent);
        });

        Button login = this.findViewById(R.id.btnLogin);
        login.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });
    }
}