package com.example.e_commerce_app.implementation.network;
import com.google.gson.annotations.SerializedName;

public class PaymentSheetResponse {
    @SerializedName("paymentIntent")
    private String paymentIntent;

    @SerializedName("customer")
    private String customer;

    @SerializedName("ephemeralKey")
    private String ephemeralKey;

    @SerializedName("sessionKey")
    private String sessionKey;

    @SerializedName("publishableKey")
    private String publishableKey;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    // Getters and Setters
    public String getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(String paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEphemeralKey() {
        return ephemeralKey;
    }

    public void setEphemeralKey(String ephemeralKey) {
        this.ephemeralKey = ephemeralKey;
    }

    public String getPublishableKey() {
        return publishableKey;
    }

    public void setPublishableKey(String publishableKey) {
        this.publishableKey = publishableKey;
    }
}
