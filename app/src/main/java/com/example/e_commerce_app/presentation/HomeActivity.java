package com.example.e_commerce_app.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.e_commerce_app.R;
import com.example.e_commerce_app.implementation.network.Connect;
import com.example.e_commerce_app.implementation.network.OnDataReceivedListener;
import com.example.e_commerce_app.implementation.objects.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Connect.getInstance().getPokemonData(new OnDataReceivedListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                JsonArray jsonArray = JsonParser.parseString(jsonObject.get("message").getAsString()).getAsJsonArray();
                productList = new ArrayList<>();
                Gson gson = new Gson();
                for (JsonElement element : jsonArray) {
                    Product product = gson.fromJson(element, Product.class);
                    productList.add(product);
                }
                productAdapter = new ProductAdapter(productList, HomeActivity.this);
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                // Handle the failure
                t.printStackTrace();
            }
        });
    }
}
