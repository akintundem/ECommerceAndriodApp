package com.example.e_commerce_app.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.e_commerce_app.implementation.network.Connect;
import com.example.e_commerce_app.implementation.network.PaymentSheetResponse;
import com.stripe.android.paymentsheet.*;




import com.example.e_commerce_app.R;
import com.stripe.android.view.CardInputWidget;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = "CheckoutActivity";

    PaymentSheet paymentSheet;
    private String paymentClientSecret;

    Button buttonSubmitPayment = findViewById(R.id.payButton);

    PaymentSheet.CustomerConfiguration customerConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        paymentSheet = new PaymentSheet(this, this::onPaymentSheetResult);
        buttonSubmitPayment.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   presentPaymentSheet();
               }
           }
        );

    }

    private void presentPaymentSheet() {
        Connect.getInstance().getApiService().createPaymentCheckoutSession().enqueue(new Callback<PaymentSheetResponse>() {
            @Override
            public void onResponse(Call<PaymentSheetResponse> call, Response<PaymentSheetResponse> response) {
                PaymentSheetResponse sheetResponse = response.body();
                paymentClientSecret = sheetResponse.getSessionKey();
                if (response.isSuccessful() && response.body() != null) {
                    customerConfig = new PaymentSheet.CustomerConfiguration(
                            sheetResponse.getCustomer(),
                            sheetResponse.getEphemeralKey()
                    );

                } else {
                    Toast.makeText(getApplicationContext(), "Error Encountered. Please try again later.", Toast.LENGTH_SHORT).show();}
            }
            @Override
            public void onFailure(Call<PaymentSheetResponse> call, Throwable t) {
                // Display a toast informing the user about the server contact failure
                Toast.makeText(getApplicationContext(), "Unable to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }

        });

        PaymentSheet.Address address =
                new PaymentSheet.Address.Builder()
                        .country("US")
                        .build();

        PaymentSheet.BillingDetails billingDetails =
                new PaymentSheet.BillingDetails.Builder()
                        .address(address)
                        .email("foo@bar.com")
                        .build();

        final PaymentSheet.Configuration configuration = new PaymentSheet.Configuration.Builder("Ecom, Inc.")
                .customer(customerConfig)
                .defaultBillingDetails(billingDetails)
                .allowsDelayedPaymentMethods(true)
                .build();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        paymentSheet.presentWithPaymentIntent(
                paymentClientSecret,
                configuration
        );
    }

    void onPaymentSheetResult(final PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Canceled) {
            Log.d(TAG, "Canceled");
            Toast.makeText(getApplicationContext(), "Payment canceled", Toast.LENGTH_SHORT).show();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            Log.e(TAG, "Got error: ", ((PaymentSheetResult.Failed) paymentSheetResult).getError());
            Toast.makeText(getApplicationContext(), "Payment failed: " + ((PaymentSheetResult.Failed) paymentSheetResult).getError().getMessage(), Toast.LENGTH_SHORT).show();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            // Write code to display checkout
            Log.d(TAG, "Completed");
            Toast.makeText(getApplicationContext(), "Payment completed", Toast.LENGTH_SHORT).show();
            // You can add further actions here related to a successful payment completion
        }
    }

}
