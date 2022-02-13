package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.models.Barbershop;
import com.example.login.models.Reserve;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.BarbershopService;
import com.example.login.services.ReserveService;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReserve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reserve);

        getSupportActionBar().hide();

        Barbershop barbershop = new Barbershop();

        BarbershopService barbershopService = ConexaoRetrofit.getInstance().getRetrofit().create(BarbershopService.class);
        barbershopService.get(MainActivity.getUserLogged().getToken(), Integer.valueOf(getIntent().getStringExtra("barbershopId"))).enqueue(new Callback<Barbershop>() {
            @Override
            public void onResponse(Call<Barbershop> call, Response<Barbershop> response) {
                if (response.code() == 200) {
                    barbershop.setId(response.body().getId());
                    barbershop.getOwner().setId(response.body().getOwner().getId());
                    TextView labelTituloBarbeariaReserva = findViewById(R.id.labelTituloBarbeariaReserva);
                    labelTituloBarbeariaReserva.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Barbershop> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao buscar a barbearia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        TextView data = findViewById(R.id.txtData);
        TextView hora = findViewById(R.id.txtTime);

        SimpleMaskFormatter smfData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwData = new MaskTextWatcher(data, smfData);
        data.addTextChangedListener(mtwData);

        SimpleMaskFormatter smfHora = new SimpleMaskFormatter("NN:NN:NN");
        MaskTextWatcher mtwHora = new MaskTextWatcher(hora, smfHora);
        hora.addTextChangedListener(mtwHora);

        Button addReserve = findViewById(R.id.btnCreateReserve);
        addReserve.setOnClickListener((View v) -> {
            Reserve reserve = new Reserve();

            reserve.setSituation(Reserve.SITUACAO_ABERTO);

            if (data.getText().length() < 10) {
                Toast.makeText(getApplicationContext(), "Informe uma data completa", Toast.LENGTH_SHORT).show();
                return;
            }
            if (hora.getText().length() < 5) {
                Toast.makeText(getApplicationContext(), "Informe uma hora completa", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] dataPieces = data.getText().toString().split("/");
            String[] horaPieces = hora.getText().toString().split(":");
            reserve.setSchedule(new Date(
                Integer.parseInt(dataPieces[2]) - 1900,
                Integer.parseInt(dataPieces[1]) - 1,
                Integer.parseInt(dataPieces[0]),
                Integer.parseInt(horaPieces[0]) - 3,
                Integer.parseInt(horaPieces[1])
            ));

            reserve.getBarber().setId(barbershop.getOwner().getId());

            if (reserve.getBarber().getId() == 0) {
                return;
            }

            reserve.setBarbershop(barbershop);
            reserve.getUser().setId(MainActivity.getUserLogged().getId());

            ReserveService reserveService = ConexaoRetrofit.getInstance().getRetrofit().create(ReserveService.class);
            reserveService.create(MainActivity.getUserLogged().getToken(), reserve).enqueue(new Callback<Reserve>() {
                @Override
                public void onResponse(Call<Reserve> call, Response<Reserve> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Reserva criada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), home.class);
                        startActivity(intent);
                    }
                    else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "Informe uma hora maior que a atual", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Reserve> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao inserir a reserva: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}