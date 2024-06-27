package com.example.e_commerce_app.implementation.network;

//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.http.GET;
//
//public interface ApiService {
//    @GET("/recommend")
//    Call<ResponseBody> getRecommend();
//}


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/recommend")
    Call<ResponseBody> getRecommend();
}
