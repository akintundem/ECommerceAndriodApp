package com.example.e_commerce_app.implementation.cognito.authentication;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.example.e_commerce_app.implementation.cognito.CognitoSettings;
import com.example.e_commerce_app.implementation.cognito.authorization.UserTokenManager;

public class VerificationImplementation {
    public static void verifyCode(String email, String verificationCode, GenericHandler callback) {
        // Get an instance of the CognitoUserPool
        CognitoUser user = CognitoSettings.getInstance().getUserPool().getUser(email);
        UserTokenManager.getInstance().setUser(user);

        // Confirm the sign-up operation with the verification code
        user.confirmSignUpInBackground(verificationCode, true, callback);
    }

}
