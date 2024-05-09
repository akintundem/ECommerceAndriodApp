package com.example.e_commerce_app.implementation.cognito.authentication;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.example.e_commerce_app.implementation.cognito.CognitoSettings;

public class SignupImplementation {

    public static void signUp(String firstName, String lastName, String email, String password, String phone, SignUpHandler signUpHandler) {
        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("given_name", firstName);
        userAttributes.addAttribute("family_name", lastName);
        userAttributes.addAttribute("email", email);
        userAttributes.addAttribute("phone_number", phone);

        CognitoSettings.getInstance().getUserPool().signUpInBackground(email, password, userAttributes, null, signUpHandler);
    }


}
