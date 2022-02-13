package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.login.models.Invite;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.InviteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyInvites extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invites);

        getSupportActionBar().hide();
        context = this;

        ListView list = findViewById(R.id.invitesList);
        List<String> invites = new ArrayList<>();

        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, InviteDetails.class);
            intent.putExtra("inviteId", list.getAdapter().getItem(position).toString().split(" - ")[0]);
            intent.putExtra("inviteBarbershop", list.getAdapter().getItem(position).toString().split(" - ")[1]);
            startActivity(intent);
        });

        InviteService inviteService = ConexaoRetrofit.getInstance().getRetrofit().create(InviteService.class);
        inviteService.getAllFromBarber(MainActivity.getUserLogged().getToken(), MainActivity.getUserLogged().getId()).enqueue(new Callback<List<Invite>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<Invite>> call, Response<List<Invite>> response) {
                if (response.code() == 200) {
                    response.body().forEach(invite -> invites.add(invite.getId() + " - " + invite.getBarbershop().getName()));

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, invites);
                    list.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Invite>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar listar os convites: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}