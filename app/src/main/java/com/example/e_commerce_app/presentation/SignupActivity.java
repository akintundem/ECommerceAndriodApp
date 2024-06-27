package com.example.e_commerce_app.presentation;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.services.cognitoidentityprovider.model.SignUpResult;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.implementation.UserSessionManager;
import com.example.e_commerce_app.implementation.cognito.authentication.SignupImplementation;
<<<<<<< HEAD
=======
import com.example.e_commerce_app.implementation.serverComms.ServerCommunication;
>>>>>>> 3-product-listing

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextPhone;
    private Button buttonSignUp;
    private TextView textViewSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitity_sign_up);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewSignIn = findViewById(R.id.textViewSignIn);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
//                signUp();
=======
                signUp();
>>>>>>> 3-product-listing
            }
        });

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, SignInActivity.class));
            }
        });
    }

<<<<<<< HEAD
//    private void signUp() {
//        String firstName = editTextFirstName.getText().toString().trim();
//        String lastName = editTextLastName.getText().toString().trim();
//        String email = editTextEmail.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//        String phone = editTextPhone.getText().toString().trim();
//        UserSessionManager.getInstance().setUserDetails(email,firstName,lastName,phone);
//        SignupImplementation.signUp(firstName,lastName,email,password,phone,signUpHandler);
//
//    }
=======
    private void signUp() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        UserSessionManager.getInstance().setUserDetails(email,firstName,lastName,phone);
        SignupImplementation.signUp(firstName,lastName,email,password,phone,signUpHandler);

    }
>>>>>>> 3-product-listing



    private final SignUpHandler signUpHandler = new SignUpHandler() {
        @Override
        public void onSuccess(CognitoUser user, SignUpResult signUpResult) {
            // Move to the dashboard activity on successful sign-up
            Intent intent = new Intent(SignupActivity.this, VerificationCodeActivity.class);
            startActivity(intent);
        }

        @Override
        public void onFailure(Exception exception) {
            // Show a toast message on sign-up failure
            Log.e("SignUpFailure", "Sign-up failed: " + exception.getMessage());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SignupActivity.this, "Invalid: Sign up cannot be completed.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}
