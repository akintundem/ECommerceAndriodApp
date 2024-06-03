package com.example.e_commerce_app.implementation.network;

import com.google.gson.JsonObject;

public interface OnDataReceivedListener {
    void onSuccess(JsonObject jsonObject);
    void onFailure(Throwable t);
}
