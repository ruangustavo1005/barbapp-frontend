package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
            String convite = (String) list.getItemAtPosition(position);
            String idConvite = convite.split(" - ")[0];
            intent.putExtra("inviteId", idConvite);
            startActivity(intent);
        });

        invites.add("Barbearia do Leozin");
        invites.add("Barbearia do Ruanzin");
        invites.add("Barbearia do Dantin");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, invites);
        list.setAdapter(adapter);

    }
}