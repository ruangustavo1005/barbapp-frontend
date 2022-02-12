package com.example.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.models.User;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.UserService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().hide();

        Button button = findViewById(R.id.brnCadastrar);
        button.setOnClickListener((View v) -> {
            User user = new User();

            TextView email = findViewById(R.id.cadastroUsuarioEmail);
            if (email.getText().length() > 0) {
                user.setEmail(email.getText().toString());

                TextView senha = findViewById(R.id.cadastroUsuarioSenha);
                if (senha.getText().length() > 0) {
                    user.setPassword(senha.getText().toString());

                    TextView nome = findViewById(R.id.cadastroUsuarioNome);
                    if (nome.getText().length() > 0) {
                        user.setName(nome.getText().toString());

                        user.setIs_barber(((CheckBox) findViewById(R.id.cadastroUsuarioBarbeiro)).isChecked());

                        UserService userService = ConexaoRetrofit.getInstance().getRetrofit().create(UserService.class);

                        userService.create(user).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso! Efetue seu login", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                                else if (response.code() == 403) {
                                    Toast.makeText(getApplicationContext(), "O e-mail " + user.getEmail() + " já está cadastrado", Toast.LENGTH_SHORT).show();
                                }
                                else if (response.code() == 400) {
                                    try {
                                        Toast.makeText(getApplicationContext(), "Houve um erro no processo: " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                    }
                                    catch (IOException e) {
                                        Toast.makeText(getApplicationContext(), "Houve um erro no processo.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Houve um erro não esperado: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Informe o nome", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Informe a senha", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Informe o e-mail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}