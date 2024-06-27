package com.example.e_commerce_app.implementation;

<<<<<<< HEAD
import com.google.gson.JsonObject;

public class UserSessionManager {
    private static UserSessionManager instance;

    private JsonObject recommendedProducts;
=======
public class UserSessionManager {
    private static UserSessionManager instance;

    // User data fields
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
>>>>>>> 3-product-listing

    // Private constructor to prevent instantiation outside the class
    private UserSessionManager() {}

    // Method to get the singleton instance
    public static synchronized UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

<<<<<<< HEAD
    public JsonObject getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(JsonObject recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }
}
=======
    // Method to set user data
    public void setUserDetails(String email, String firstName, String lastName, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Method to get user email
    public String getEmail() {
        return email;
    }

    // Method to get user first name
    public String getFirstName() {
        return firstName;
    }

    // Method to get user last name
    public String getLastName() {
        return lastName;
    }

    // Method to get user phone number
    public String getPhone() {
        return phone;
    }
}
>>>>>>> 3-product-listing
