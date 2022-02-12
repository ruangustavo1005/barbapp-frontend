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
import com.example.login.models.Product;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.BarbershopService;
import com.example.login.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class barbershopDetails extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbershop_details);

        context = this;

        getSupportActionBar().hide();

        String barbershopId = getIntent().getStringExtra("barbershopId");

        Button button = findViewById(R.id.btnAddProduct);
        Button buttonInvite = findViewById(R.id.btnInvite);
        ListView list = findViewById(R.id.barbersList);

        if(!MainActivity.getUserLogged().isIs_barber()) {
            button.setVisibility(View.GONE);
            buttonInvite.setVisibility(View.GONE);
        }

        button.setOnClickListener((View v) -> {
            Intent intent = new Intent(v.getContext(), AddProduct.class);
            intent.putExtra("barbershopId", barbershopId);
            startActivity(intent);
        });
        buttonInvite.setOnClickListener((View v) -> {
            Intent intent = new Intent(v.getContext(), InviteBarbers.class);
            intent.putExtra("barbershopId", barbershopId);
            startActivity(intent);
        });
        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(view.getContext(), ProductDetails.class);
            intent.putExtra("barbershopId", barbershopId);
            intent.putExtra("productId", list.getAdapter().getItem(position).toString().split(" - ")[0]);
            startActivity(intent);
        });

        ProductService productService = ConexaoRetrofit.getInstance().getRetrofit().create(ProductService.class);
        productService.list(MainActivity.getUserLogged().getToken(), Integer.parseInt(barbershopId)).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    List<String> produtos = new ArrayList<>();
                    for (Product product : response.body()) {
                        produtos.add(product.getId() + " - " + product.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, produtos);
                    list.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar listar os produtos: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        BarbershopService barbershopService = ConexaoRetrofit.getInstance().getRetrofit().create(BarbershopService.class);
        barbershopService.get(MainActivity.getUserLogged().getToken(), Integer.parseInt(barbershopId)).enqueue(new Callback<Barbershop>() {
            @Override
            public void onResponse(Call<Barbershop> call, Response<Barbershop> response) {
                if (response.code() == 200 && response.body() != null) {
                    TextView telefone = findViewById(R.id.textViewTelefoneBarbershopDetails);
                    telefone.setText(response.body().getPhone());
                    TextView endereco = findViewById(R.id.textViewEnderecoBarbershopDetails);
                    endereco.setText(response.body().getAddress());
                }
            }

            @Override
            public void onFailure(Call<Barbershop> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar recuperar os dados da barberia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}