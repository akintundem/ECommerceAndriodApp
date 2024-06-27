package com.example.e_commerce_app.presentation;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.implementation.cognito.CognitoSettings;
import com.example.e_commerce_app.implementation.cognito.authentication.SignInImplementation;
import com.example.e_commerce_app.implementation.cognito.authentication.SignupImplementation;
import com.example.e_commerce_app.implementation.cognito.authorization.UserTokenManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class SignInActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonSignIn;
    private TextView textViewSignUp;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CognitoSettings.init(getApplicationContext());

        setContentView(R.layout.activity_sign_in);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignupActivity.class));
            }
        });
    }

    private void signIn() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

//        UserTokenManager.getInstance().setTokens(userSession);
<<<<<<< HEAD
        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
=======
        Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
>>>>>>> 3-product-listing
        startActivity(intent);

        SignInImplementation.signIn(username,password,handler);


        // For demo purposes, display a toast message indicating successful sign-in
        Toast.makeText(this, "Sign in successful!", Toast.LENGTH_SHORT).show();
    }

    AuthenticationHandler handler = new AuthenticationHandler() {
        @Override
        public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
            // Set all initial tokens.
            UserTokenManager.getInstance().setTokens(userSession);
<<<<<<< HEAD
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
=======
            Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
>>>>>>> 3-product-listing
            startActivity(intent);
        }

        @Override
        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
            // Provide authentication details for sign-in
            AuthenticationDetails authenticationDetails = new AuthenticationDetails(userId, password, null);

            // Continue with the authentication process
            authenticationContinuation.setAuthenticationDetails(authenticationDetails);
            authenticationContinuation.continueTask();
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SignInActivity.this, "Sign in cannot be completed.", Toast.LENGTH_SHORT).show();
            }
        });
        }

    };
}
