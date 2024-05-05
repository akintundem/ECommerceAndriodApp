package com.example.e_commerce_app.presentation;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce_app.R;
import com.example.e_commerce_app.implementation.serverComms.ServerCommunication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DashboardActivity extends AppCompatActivity {

    String musicRequestID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        try {
            musicRequestID = ServerCommunication.getInstance(getApplicationContext()).requestSong("Mona Lisa", "Lojay");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        responseSong(musicRequestID);
    }

    // Method to handle the response received from the server
    public void responseSong(String musicRequestID) {
        ServerCommunication.getInstance(getApplicationContext()).setResponseListener(new ServerCommunication.WebSocketResponseListener() {
            @Override
            public void onResponseReceived(String response) {
                try {
                    // Parse the response JSON
                    JSONObject jsonResponse = new JSONObject(response);

                    JSONObject messageObject = jsonResponse.optJSONObject("message");
                    if (messageObject != null) {
                        // Extract music request ID from the "message" object
                        String responseMusicRequestID = messageObject.optString("request_id");

                        // Check if the received music request ID matches the one provided
                        if (musicRequestID.equals(responseMusicRequestID)) {
                            // Process the response (e.g., extract song data)
                            String songDataString = messageObject.optString("songdata");
                            byte[] songData = Base64.decode(songDataString, Base64.DEFAULT);

                            // Play the song
                            playSong(songData);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Method to play the song
    private void playSong(byte[] songData) {
        // Example using MediaPlayer to play the song data
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            // Create a temporary file to write the song data
            File tempFile = File.createTempFile("temp", ".mp3", getCacheDir());
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(songData);
            fos.close();

            // Set the data source to the temporary file
            mediaPlayer.setDataSource(tempFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            // Placeholder toast message to indicate song is playing
            Toast.makeText(this, "Playing song", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
