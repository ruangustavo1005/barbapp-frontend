package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.models.Invite;
import com.example.login.models.User;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.BarbershopBarberService;
import com.example.login.services.InviteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_details);
        getSupportActionBar().hide();

        User userLogged = MainActivity.getUserLogged();
        int inviteId = Integer.parseInt(getIntent().getStringExtra("inviteId"));
        Invite invite = new Invite();

        InviteService inviteService = ConexaoRetrofit.getInstance().getRetrofit().create(InviteService.class);

        inviteService.getById(userLogged.getToken(), inviteId).enqueue(new Callback<Invite>() {
            @Override
            public void onResponse(Call<Invite> call, Response<Invite> response) {
                if (response.code() == 200) {
                    invite.setId(response.body().getId());
                    invite.setSchedule(response.body().getSchedule());
                    invite.setSituation(response.body().getSituation());
                    invite.setUser(response.body().getUser());
                    invite.setBarber(response.body().getBarber());
                    invite.setBarbershop(response.body().getBarbershop());
                }
            }

            @Override
            public void onFailure(Call<Invite> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar buscar o convite: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        TextView labelTitulo = findViewById(R.id.labelTituloInvite);
        labelTitulo.setText("Você foi convidado para ser barbeiro na barbearia \"" + getIntent().getStringExtra("inviteBarbershop") + "\"");

        Button accept = findViewById(R.id.btnAcceptInvite);
        accept.setOnClickListener((View v) -> {
            inviteService.accept(userLogged.getToken(), inviteId).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Convite aceito", Toast.LENGTH_SHORT).show();

                        BarbershopBarberService barbershopBarberService = ConexaoRetrofit.getInstance().getRetrofit().create(BarbershopBarberService.class);
                        barbershopBarberService.addBarber(userLogged.getToken(), invite.getBarbershop().getId(), userLogged.getId()).enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                if (response.code() == 200) {
                                    Intent intent = new Intent(v.getContext(), home.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar entra na barbearia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro ao tentar aceitar o convite: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        Button recuse = findViewById(R.id.btnRecuseInvite);
        recuse.setOnClickListener((View v) -> {
            inviteService.recuse(userLogged.getToken(), inviteId).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Convite recusado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), home.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro ao tentar recusar o convite: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
}