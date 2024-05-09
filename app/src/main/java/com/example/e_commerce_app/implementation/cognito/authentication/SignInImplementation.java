package com.example.e_commerce_app.implementation.cognito.authentication;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.example.e_commerce_app.implementation.cognito.CognitoSettings;
import com.example.e_commerce_app.implementation.cognito.authorization.UserTokenManager;

public class SignInImplementation {
    public static void signIn(String email, String password, AuthenticationHandler authenticationHandler) {
        // Get an instance of the CognitoUserPool
        CognitoUser user = CognitoSettings.getInstance().getUserPool().getUser(email);
        UserTokenManager.getInstance().setUser(user);
        // Perform the sign-in operation
        user.getSessionInBackground(authenticationHandler);
    }
}
