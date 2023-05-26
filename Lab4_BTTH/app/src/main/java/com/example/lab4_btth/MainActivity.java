package com.example.lab4_btth;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button playPauseButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPauseButton = findViewById(R.id.btn_playVideo);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://vnno-pt-2-tf-mp3-s1-zmp3.zmdcdn.me/4401a0097f499617cf58/6910732961157684130?authen=exp=1685209474~acl=/4401a0097f499617cf58/*~hmac=5dfbe86aae283e026dac3a2b00027b23&fs=MTY4NTAzNjY3NDmUsIC5M3x3ZWJWNHwxNC4xNzkdUngMjQwLjY4");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playPauseButton.setText("Tạm Dừng");
                } else {
                    mediaPlayer.start();
                    playPauseButton.setText("Đang phát");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the media player when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
