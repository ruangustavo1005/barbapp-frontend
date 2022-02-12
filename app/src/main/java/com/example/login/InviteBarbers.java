package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class InviteBarbers extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_barbers);

        getSupportActionBar().hide();

        context = this;

        ListView list = findViewById(R.id.barbersList);
        List<String> barbearias = new ArrayList<>();
        barbearias.add("Leozim");
        barbearias.add("Ruanzin");
        barbearias.add("Dantin");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, barbearias);
        list.setAdapter(adapter);


    }
}