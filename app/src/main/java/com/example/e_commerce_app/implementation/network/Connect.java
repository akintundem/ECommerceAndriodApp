//package com.example.e_commerce_app.implementation.network;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.JsonSyntaxException;
//
//import java.io.IOException;
//
//import okhttp3.OkHttpClient;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class connect {
//    private ApiService apiService;
//    private static connect instance;
//
//    // Private constructor to prevent instantiation outside the class
//    private connect() {
//        // Initialize Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("") // Base URL for your local server
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        apiService = retrofit.create(ApiService.class);
//    }
//
//    // Method to get the singleton instance
//    public static synchronized connect getInstance() {
//        if (instance == null) {
//            instance = new connect();
//        }
//        return instance;
//    }
//
//    // Method to get recommendations
//    public void getRecommend() {
//        Call<ResponseBody> call = apiService.getRecommend();
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    // Handle the successful response here
//                    try {
//                        String jsonString = response.body().string();
//                        JsonElement jsonElement = JsonParser.parseString(jsonString);
//                        // Work with the JsonElement directly
//                        if (jsonElement.isJsonObject()) {
//                            JsonObject jsonObject = jsonElement.getAsJsonObject();
//                            System.out.println("JSON Response: " + jsonObject);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.err.println("Error reading response: " + e.getMessage());
//                    } catch (JsonSyntaxException e) {
//                        e.printStackTrace();
//                        System.err.println("Error parsing JSON response: " + e.getMessage());
//                    }
//                } else {
//                    // Handle the error response here
//                    try {
//                        System.err.println("Error: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.err.println("Error reading error response: " + e.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                // Handle the failure here
//                t.printStackTrace();
//            }
//        });
//    }
//}


package com.example.e_commerce_app.implementation.network;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.e_commerce_app.implementation.UserSessionManager;
import com.example.e_commerce_app.presentation.CheckoutActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.stripe.android.paymentsheet.PaymentSheet;

public class Connect {
    private ApiService apiService;
    private static Connect instance;

    // Private constructor to prevent instantiation outside the class
    private Connect() {
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/") // Base URL for PokeAPI
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    // Method to get the singleton instance
    public static synchronized Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public void getPokemonData(OnDataReceivedListener listener) {
        Call<ResponseBody> call = apiService.getRecommend();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String jsonString = response.body().string();
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
                        UserSessionManager.getInstance().setRecommendedProducts(jsonObject);
                        listener.onSuccess(jsonObject);
                    } catch (IOException | JsonSyntaxException e) {
                        e.printStackTrace();
                        listener.onFailure(e);
                    }
                } else {
                    try {
                        String errorString = response.errorBody().string();
                        listener.onFailure(new Exception(errorString));
                    } catch (IOException e) {
                        e.printStackTrace();
                        listener.onFailure(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                listener.onFailure(t);
            }
        });
    }


//
//            public void onResponse(@NonNull Call<PaymentSheetResponse> call, @NonNull Response<PaymentSheetResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    PaymentSheetResponse responseBody = response.body();
//                    paymentIntentClientSecret = responseBody.getPaymentIntent();
//                    customerConfig = new PaymentSheet.CustomerConfiguration(
//                            responseBody.getCustomer(),
//                            responseBody.getEphemeralKey()
//                    );
//                    PaymentConfiguration.init(getApplicationContext(), responseBody.getPublishableKey());
//                }
//            }
//








//    private void onPaymentSheetResult(@NonNull PaymentSheetResult paymentSheetResult) {
//        switch (paymentSheetResult) {
//            case Completed:
//                Toast.makeText(this, "Payment complete!", Toast.LENGTH_SHORT).show();
//                break;
//            case Canceled:
//                Toast.makeText(this, "Payment canceled!", Toast.LENGTH_SHORT).show();
//                break;
//            case Failed:
//                Toast.makeText(this, "Payment failed!", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }



}
