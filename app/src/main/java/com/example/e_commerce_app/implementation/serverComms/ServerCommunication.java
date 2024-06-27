package com.example.e_commerce_app.implementation.serverComms;

import android.content.Context;
import android.util.Log;

import com.example.e_commerce_app.implementation.cognito.authorization.UserTokenManager;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class ServerCommunication {

    private static ServerCommunication instance;
    private WebSocketClient client;
    private WebSocketResponseListener responseListener;

    public interface WebSocketResponseListener {
        void onResponseReceived(String response);
    }

    private ServerCommunication(Context context) {
        initializeWebSocket(context);
    }

    public static synchronized ServerCommunication getInstance(Context context) {
        if (instance == null) {
            instance = new ServerCommunication(context);
        }
        return instance;
    }

    public void setResponseListener(WebSocketResponseListener listener) {
        this.responseListener = listener;
    }

    private void initializeWebSocket(Context context) {
        try {
            // Define your WebSocket server URL
            String serverUrl = "ws://10.0.2.2:8888/websocket";

            // Create a WebSocket client
            client = new WebSocketClient(new URI(serverUrl)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    // WebSocket connection opened
                    Log.d("WebSocket", "Connection opened"); // Log message when connection is opened

                }

                @Override
                public void onMessage(String message) {
                    // Received a message from the server
                    // You can handle incoming messages here
                    if (responseListener != null) {
                        responseListener.onResponseReceived(message);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    // WebSocket connection closed
                    // You can handle the connection close event here
                }

                @Override
                public void onError(Exception ex) {
                    // Handle any errors
                    Log.e("WebSocket", "Error creating WebSocket connection: " + ex.getMessage(), ex); // Log error message
                }
            };

            // Connect to the WebSocket server
            client.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(JSONObject token, String action, JSONObject message) {
        try {
            // Construct the JSON object
            JSONObject requestObject = new JSONObject();
            requestObject.put("token", token);
            requestObject.put("action", action);
            requestObject.put("message", message);

            // Convert JSON object to string
            String jsonString = requestObject.toString();

            // Check if the WebSocket client is initialized
            if (client != null && client.isOpen()) {
                // Send the JSON message
                client.send(jsonString);
            }

        } catch (Exception e) {
            // Log the error with a custom message
            Log.e("YourTag", "Error sending request: " + e.getMessage(), e);
        }
    }





    public String requestSong(String songName, String artistName) throws JSONException {
        // Generate a unique request ID
        String requestId = generateRequestId();

        // Create a JSON message with song name, artist name, and request ID
        JSONObject messageObject = new JSONObject();
        messageObject.put("song_name", songName);
        messageObject.put("artist_name", artistName);
        messageObject.put("request_id", requestId);

        try {
            sendRequest(UserTokenManager.getInstance().getTokens(), "request_song", messageObject);
        } catch (Exception e) {
            // Refresh access token
            UserTokenManager.getInstance().refreshTokens();
            // Resend request with refreshed tokens
            sendRequest(UserTokenManager.getInstance().getTokens(), "request_song", messageObject);
        }

        // Return the generated request ID
        return requestId;
    }


    private String generateRequestId() {
        // Generate a unique ID based on current time in milliseconds and a random number
        long timestamp = System.currentTimeMillis();
        int randomNumber = new Random().nextInt(1000); // Change 1000 to desired range
        return String.valueOf(timestamp) + randomNumber;
    }

}
