package com.example.e_commerce_app.implementation.cognito.authorization;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;

import java.util.Date;

public class UserTokenManager {
    private static UserTokenManager instance;
    private String accessToken;
    private String idToken;
    private String refreshToken;

    private Date accessTokenExpiration;
    private Date idTokenExpiration;

    // Private constructor to prevent instantiation from outside
    private UserTokenManager() {}

    // Get the singleton instance
    public static synchronized UserTokenManager getInstance() {
        if (instance == null) {
            instance = new UserTokenManager();
        }
        return instance;
    }

    // Set the tokens from the user session
    public void setTokens(CognitoUserSession userSession) {
        accessToken = userSession.getAccessToken().getJWTToken();
        idToken = userSession.getIdToken().getJWTToken();
        refreshToken = userSession.getRefreshToken().getToken();
        accessTokenExpiration = userSession.getAccessToken().getExpiration();
        idTokenExpiration = userSession.getIdToken().getExpiration();
    }

    // Getters for tokens
    public String getAccessToken() {
        return accessToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    // Method to clear tokens (e.g., on user sign out)
    public void clearTokens() {
        accessToken = null;
        idToken = null;
        refreshToken = null;
    }
}



















