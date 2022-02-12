package com.example.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.models.User;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.UserService;

import java.net.InetAddress;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static User userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLogged = null;

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button cadastro = this.findViewById(R.id.btnCadastro);
        cadastro.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, cadastro.class);
            startActivity(intent);
        });

        Button login = this.findViewById(R.id.btnLogin);
        login.setOnClickListener((View v) -> {
            User user = new User();
            TextView txtLogin = findViewById(R.id.txtLogin);
            if (txtLogin.getText().length() > 0) {
                user.setEmail(txtLogin.getText().toString());

                TextView txtSenha = findViewById(R.id.txtSenha);
                if (txtSenha.getText().length() > 0) {
                    user.setPassword(txtSenha.getText().toString());

                    UserService userService = ConexaoRetrofit.getInstance().getRetrofit().create(UserService.class);

                    userService.login(user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.code() == 200) {
                                MainActivity.userLogged = response.body();
                                Intent intent = new Intent(v.getContext(), home.class);
                                startActivity(intent);
                            }
                            else if (response.code() == 400) {
                                Toast.makeText(getApplicationContext(), "Login ou senha incorretos", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Erro não esperado: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Informe a senha", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Informe um e-mail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static User getUserLogged() {
        return userLogged;
    }
}