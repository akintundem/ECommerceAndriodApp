package com.example.e_commerce_app.implementation.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/recommend")
    Call<ResponseBody> getRecommend();
    @GET("create-payment-checkout-session")
    Call<PaymentSheetResponse> createPaymentCheckoutSession();

}
