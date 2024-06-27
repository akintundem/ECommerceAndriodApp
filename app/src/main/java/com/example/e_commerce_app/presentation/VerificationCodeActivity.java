package com.example.e_commerce_app.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.implementation.UserSessionManager;
import com.example.e_commerce_app.implementation.cognito.authentication.VerificationImplementation;

public class VerificationCodeActivity extends AppCompatActivity {

    private EditText editTextVerificationCode;
    private Button buttonVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_code_layout);

        editTextVerificationCode = findViewById(R.id.editTextVerificationCode);
        buttonVerify = findViewById(R.id.buttonVerify);

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verificationCode = editTextVerificationCode.getText().toString().trim();
                VerificationImplementation.verifyCode("Hi", verificationCode, verifyHandler);
            }
        });
    }

    private final GenericHandler verifyHandler = new GenericHandler() {
        @Override
        public void onSuccess() {
            // Handle successful confirmation
            Intent intent = new Intent(VerificationCodeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent returning to it with back button
        }

        @Override
        public void onFailure(Exception exception) {
            // Handle confirmation failure
            Toast.makeText(VerificationCodeActivity.this, "Verification failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}
