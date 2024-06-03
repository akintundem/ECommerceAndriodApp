package com.example.e_commerce_app.implementation;

import com.google.gson.JsonObject;

public class UserSessionManager {
    private static UserSessionManager instance;

    private JsonObject recommendedProducts;

    // Private constructor to prevent instantiation outside the class
    private UserSessionManager() {}

    // Method to get the singleton instance
    public static synchronized UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public JsonObject getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(JsonObject recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }
}