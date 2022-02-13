package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.models.Product;
import com.example.login.retrofit.ConexaoRetrofit;
import com.example.login.services.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        getSupportActionBar().hide();

        int productId = Integer.parseInt(getIntent().getStringExtra("productId"));

        Button excluir = findViewById(R.id.btnDeleteProduct);
        TextView nome = findViewById(R.id.txtNomeProdutoView);
        TextView valor = findViewById(R.id.txtValorProdutoView);

        nome.setEnabled(false);
        valor.setEnabled(false);

        ProductService productService = ConexaoRetrofit.getInstance().getRetrofit().create(ProductService.class);

        productService.listFromId(MainActivity.getUserLogged().getToken(), productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        Product product = response.body();
                        nome.setText(product.getName());
                        valor.setText(String.valueOf(product.getValue()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro ao tentar visualizar o produto: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        excluir.setOnClickListener((View view) -> {
            productService.delete(MainActivity.getUserLogged().getToken(), productId).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Produto excluído", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), barbershopDetails.class);
                        intent.putExtra("barbershopId", getIntent().getStringExtra("barbershopId"));
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro ao tentar excluir o produto: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}