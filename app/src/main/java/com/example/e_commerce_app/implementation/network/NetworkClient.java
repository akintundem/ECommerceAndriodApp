package com.example.e_commerce_app.implementation.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    private static Retrofit retrofit;

    private NetworkClient() {
        // Private constructor to prevent instantiation
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            synchronized (NetworkClient.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:3000/") // Use 10.0.2.2 to access localhost from Android Emulator
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
