package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.login.models.Barbershop;
import com.example.login.models.Invite;
import com.example.login.models.User;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.InviteService;
import com.example.login.services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteBarbers extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_barbers);

        getSupportActionBar().hide();

        String barbershopId = getIntent().getStringExtra("barbershopId");

        context = this;

        ListView list = findViewById(R.id.barbersList);

        UserService userService = ConexaoRetrofit.getInstance().getRetrofit().create(UserService.class);
        userService.listBarbers(MainActivity.getUserLogged().getToken()).enqueue(new Callback<List<User>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<String> barbeiros = new ArrayList<>();

                response.body().forEach(user -> barbeiros.add(user.getId() + " - " + user.getName()));

                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, barbeiros);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar listar os barbeiros: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnItemClickListener((parent, view, position, id) -> {
            User userLogged = MainActivity.getUserLogged();

            Invite invite = new Invite();

            invite.setSchedule(new Date());
            invite.setSituation(1);
            invite.getUser().setId(userLogged.getId());
            invite.getBarber().setId(Integer.parseInt(list.getAdapter().getItem(position).toString().split(" - ")[0]));
            invite.getBarbershop().setId(Integer.parseInt(getIntent().getStringExtra("barbershopId")));

            InviteService inviteService = ConexaoRetrofit.getInstance().getRetrofit().create(InviteService.class);
            inviteService.create(userLogged.getToken(), invite).enqueue(new Callback<Barbershop>() {
                @Override
                public void onResponse(Call<Barbershop> call, Response<Barbershop> response) {

                    Intent intent = new Intent(view.getContext(), barbershopDetails.class);
                    intent.putExtra("barbershopId", barbershopId);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Barbershop> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro ao tentar criar o convite: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}