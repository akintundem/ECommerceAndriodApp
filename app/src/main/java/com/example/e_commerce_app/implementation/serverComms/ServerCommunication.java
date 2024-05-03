package com.example.e_commerce_app.implementation.serverComms;

import android.content.Context;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class ServerCommunication {
    private static final String TAG = "ServerCommunication";
    private static final String SERVER_URL = "your_server_url_here";

    public static void requestSong(Context context, String token, String songName, String artistName) throws JSONException {
        // Create a JSON message with song name and artist name
        JSONObject messageObject = new JSONObject();
        messageObject.put("song_name", songName);
        messageObject.put("artist_name", artistName);
        try {
            sendRequest(context, token, "request_song", messageObject);
        } catch (Exception e) {
            refreshAccessToken(context, new TokenRefreshListener() {
                @Override
                public void onTokenRefreshed(String refreshedToken) {
                    // Retry request with refreshed token
                    sendRequest(context, token, "request_song", messageObject);
                }

                @Override
                public void onTokenRefreshFailed() {
                    // Handle token refresh failure
                    Log.e(TAG, "Token refresh failed");
                }
            });
        }
    }

    private static void sendRequest(Context context, String token, String action, JSONObject message) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Create JSON object for the message
        JSONObject messageObject = new JSONObject();
        try {
            messageObject.put("song_name", songName);
            messageObject.put("artist_name", artistName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create JSON object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                SERVER_URL,
                messageObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle server response
                        Log.d(TAG, "Server response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e(TAG, "Error sending message to server: " + error.toString());
                        handleErrorResponse(context, error, new TokenRefreshListener() {
                            @Override
                            public void onTokenRefreshed(String refreshedToken) {
                                // Retry request with refreshed token
                                sendRequest(context, refreshedToken, songName, artistName);
                            }

                            @Override
                            public void onTokenRefreshFailed() {
                                // Handle token refresh failure
                                Log.e(TAG, "Token refresh failed");
                            }
                        });
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                // Return custom headers (e.g., user token)
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + token); // Add user token to headers
                return headers;
            }
        };

        // Add request to the request queue
        requestQueue.add(jsonObjectRequest);
    }

    private static void refreshAccessToken(Context context, TokenRefreshListener listener) {
        // Implement token refresh logic here
        // Example: TokenManager.refreshAccessToken(context, listener);
    }

    private static void handleErrorResponse(Context context, VolleyError error, TokenRefreshListener listener) {
        // Check if the error is due to an invalid or expired access token
        if (error instanceof AuthFailureError && error.networkResponse != null && error.networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
            // Initiate token refresh
            refreshAccessToken(context, listener);
        } else {
            // Handle other types of errors
            Log.e(TAG, "Request failed: " + error.toString());
        }
    }

    interface TokenRefreshListener {
        void onTokenRefreshed(String refreshedToken);
        void onTokenRefreshFailed();
    }
}
