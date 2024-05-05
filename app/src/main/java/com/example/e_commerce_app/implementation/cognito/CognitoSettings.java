package com.example.e_commerce_app.implementation.cognito;
import android.content.Context;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CognitoSettings {

    private static CognitoSettings instance;
    private final Context applicationContext;
    private String userPoolId;
    private String clientId;
    private String clientSecret;
    private Regions region;

    private CognitoSettings(Context applicationContext) {
        this.applicationContext = applicationContext;
        loadCredentialsFromJson();

    }

    public static synchronized void init(Context applicationContext) {
        if (instance == null) {
            instance = new CognitoSettings(applicationContext);
        }
    }

    public static synchronized CognitoSettings getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CognitoSettings has not been initialized. Call init() first.");
        }
        return instance;
    }

    private void loadCredentialsFromJson() {
        try {
            // Load JSON configuration file from assets
            InputStream inputStream = applicationContext.getAssets().open("cognito_config.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonConfig = new String(buffer, StandardCharsets.UTF_8);

            // Parse JSON and extract credentials
            JsonObject jsonObject = JsonParser.parseString(jsonConfig).getAsJsonObject();
            userPoolId = jsonObject.get("UserPoolId").getAsString();
            clientId = jsonObject.get("ClientId").getAsString();
            clientSecret = jsonObject.get("ClientSecret").getAsString();
            region = Regions.fromName(jsonObject.get("Region").getAsString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CognitoUserPool getUserPool() {
        return new CognitoUserPool(applicationContext, userPoolId, clientId, clientSecret, region);
    }
}
