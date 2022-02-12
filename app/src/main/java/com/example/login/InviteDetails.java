package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InviteDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_details);

        getSupportActionBar().hide();

        String inviteId = getIntent().getStringExtra("inviteId");

        Button accept = findViewById(R.id.btnAcceptInvite);
        accept.setOnClickListener((View v) -> {
            //PUT /invite/2/accept
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });

        Button recuse = findViewById(R.id.btnRecuseInvite);
        recuse.setOnClickListener((View v) -> {
            //PUT /invite/2/recuse
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });


    }
}