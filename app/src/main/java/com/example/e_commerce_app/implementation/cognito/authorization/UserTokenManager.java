package com.example.e_commerce_app.implementation.cognito.authorization;

<<<<<<< HEAD
=======
import android.content.Intent;
import android.widget.Toast;

>>>>>>> 3-product-listing
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
<<<<<<< HEAD
=======
import com.example.e_commerce_app.presentation.DashboardActivity;
import com.example.e_commerce_app.presentation.SignInActivity;
>>>>>>> 3-product-listing


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class UserTokenManager {
    private static UserTokenManager instance;

    CognitoUser user;

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

    public JSONObject getTokens() {
        JSONObject tokens = new JSONObject();
        try {
            tokens.put("accessToken", accessTokenExpiration);
            tokens.put("idToken", idToken);
            tokens.put("refreshToken", refreshToken);
            tokens.put("accessTokenExpiration", accessTokenExpiration);
            tokens.put("idTokenExpiration", idTokenExpiration);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    public void refreshTokens(){
        UserTokenManager.getInstance().getUser().getSessionInBackground(handler);
    }

    AuthenticationHandler handler = new AuthenticationHandler() {
        @Override
        public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
            setTokens(userSession);
        }

        @Override
        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
            // Not used in this scenario
        }

        @Override
        public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

        }

        @Override
        public void authenticationChallenge(ChallengeContinuation continuation) {

        }

        @Override
        public void onFailure(Exception exception) {
            // Show a toast message on sign-in failure

        }

    };

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

    public CognitoUser getUser() {
        return user;
    }

    public void setUser(CognitoUser user) {
        this.user = user;
    }

    // Method to clear tokens (e.g., on user sign out)
    public void clearTokens() {
        accessToken = null;
        idToken = null;
        refreshToken = null;
    }
}



















