package com.example.e_commerce_app.presentation;

import android.os.Bundle;
<<<<<<< HEAD

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
=======
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<JSONObject> productList;
>>>>>>> 3-product-listing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

<<<<<<< HEAD
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
=======
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize product list
        productList = new ArrayList<>();

        // Simulate receiving JSON data from the server
        String jsonString = "[{\"ProductName\": \"Product 1\", \"ProductPicture\": \"url1\", \"ProductPrice\": 10.00}," +
                "{\"ProductName\": \"Product 2\", \"ProductPicture\": \"url2\", \"ProductPrice\": 20.00}," +
                "{\"ProductName\": \"Product 3\", \"ProductPicture\": \"url3\", \"ProductPrice\": 30.00}]";

        try {
            // Parse JSON data
            JSONArray jsonArray = new JSONArray(jsonString);
            // Clear existing product list
            productList.clear();
            // Add products to the list
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                productList.add(jsonObject);
            }
            // Initialize adapter with the product list
            adapter = new ProductAdapter(productList);
            // Set adapter to RecyclerView
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Inflate the menu XML file into the Toolbar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Handle menu item clicks
        if (id == R.id.action_cart) {
            // Handle shopping cart click
            return true;
        } else if (id == R.id.action_search) {
            // Handle search click
            return true;
        }
        return super.onOptionsItemSelected(item);
>>>>>>> 3-product-listing
    }
}
