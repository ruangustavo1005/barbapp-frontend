package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.models.Product;
import com.example.login.models.User;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProduct extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        context = this;

        getSupportActionBar().hide();

        Button button = findViewById(R.id.btnAdd);
        button.setOnClickListener((View v) -> {
            Product product = new Product();

            TextView nome = findViewById(R.id.txtNomeProduto);
            if (nome.getText().length() > 0) {
                product.setName(nome.getText().toString());

                TextView valor = findViewById(R.id.txtValorProduto);
                if (valor.getText().length() > 0) {
                    product.setValue(Float.parseFloat(valor.getText().toString()));

                    ProductService productService = ConexaoRetrofit.getInstance().getRetrofit().create(ProductService.class);
                    User user = MainActivity.getUserLogged();
                    String barbershopId = getIntent().getStringExtra("barbershopId");

                    productService.create(user.getToken(), product, Integer.parseInt(barbershopId)).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.code() == 200) {
                                Intent intent = new Intent(v.getContext(), barbershopDetails.class);
                                intent.putExtra("barbershopId", getIntent().getStringExtra("barbershopId"));
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar inserir o produto: " + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Houve um erro ao tentar inserir o produto: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Informe o valor do produto", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Informe o nome do produto", Toast.LENGTH_SHORT).show();
            }
        });

    }
}