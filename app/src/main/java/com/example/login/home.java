package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.models.Barbershop;
import com.example.login.models.Reserve;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.BarbershopService;
import com.example.login.services.ReserveService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((TextView) findViewById(R.id.textViewUsuarioLogado)).setText(MainActivity.getUserLogged().getName());

        context = this;

        getSupportActionBar().hide();

        ReserveService reserveService = ConexaoRetrofit.getInstance().getRetrofit().create(ReserveService.class);
        reserveService.getLastFromUser(MainActivity.getUserLogged().getToken(), MainActivity.getUserLogged().getId()).enqueue(new Callback<Reserve>() {
            @Override
            public void onResponse(Call<Reserve> call, Response<Reserve> response) {
                if (response.code() == 200) {
                    Reserve reserve = response.body();

                    TextView data = findViewById(R.id.textViewDataUltimaReserva);
                    data.setText((new SimpleDateFormat("dd/MM/yyyy HH:mm")).format(reserve.getSchedule()));

                    TextView descricaoBarbearia = findViewById(R.id.textViewBarbeariaUltimaReserva);
                    descricaoBarbearia.setText(reserve.getBarbershop().getName());

                    TextView situacao = findViewById(R.id.textViewSituacaoUltimaReserva);
                    situacao.setText(reserve.getDescriptionSituation());
                }
                else if (response.code() == 404) {
                    TextView data = findViewById(R.id.textViewDataUltimaReserva);
                    data.setText("Você ainda não possui reservas. Quando você fizer uma, ela irá aparecer aqui!");
                }
                else if (response.code() == 400) {
                    try {
                        Toast.makeText(getApplicationContext(), "Houve um erro ao tentar recuperar a última reserva: " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Houve um erro ao tentar recuperar a última reserva.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Reserve> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar recuperar a última reserva: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        BarbershopService barbershopService = ConexaoRetrofit.getInstance().getRetrofit().create(BarbershopService.class);

        barbershopService.list(MainActivity.getUserLogged().getToken()).enqueue(new Callback<List<Barbershop>>() {
            @Override
            public void onResponse(Call<List<Barbershop>> call, Response<List<Barbershop>> response) {
                if (response.body() != null) {
                    ListView list = findViewById(R.id.invitesList);

                    List<String> barbearias = new ArrayList<>();
                    for (Barbershop barbershop : response.body()) {
                        barbearias.add(barbershop.getId() + " - " + barbershop.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, barbearias);
                    list.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Barbershop>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar listar as barbearias: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ListView list = findViewById(R.id.invitesList);

        list.setOnItemClickListener((parent, view, position, id) -> {
            String barbearia = (String) list.getItemAtPosition(position);
            String idBarbearia = barbearia.split(" - ")[0];
            Intent intent = new Intent(this, barbershopDetails.class);
            intent.putExtra("barbershopId", idBarbearia);
            startActivity(intent);
        });

        Button btn = findViewById(R.id.btnReserves);
        btn.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, reserves_list.class);
            startActivity(intent);
        });

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button btnCreateBarbershop = findViewById(R.id.btnCreateBarbershop);
        if(MainActivity.getUserLogged().isIs_barber()) {
            btnCreateBarbershop.setOnClickListener((View v) -> {
                Intent intent = new Intent(this, CreateBarbershop.class);
                startActivity(intent);
            });
        } else {
            btnCreateBarbershop.setVisibility(View.GONE);
        }

        Button buttonInvite = findViewById(R.id.btnInvites);
        if(MainActivity.getUserLogged().isIs_barber()) {
            buttonInvite.setOnClickListener((View v) -> {
                Intent intent = new Intent(this, MyInvites.class);
                startActivity(intent);
            });
        } else {
            buttonInvite.setVisibility(View.GONE);
        }
    }
}