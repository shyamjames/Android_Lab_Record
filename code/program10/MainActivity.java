package com.example.pg10_videomusic;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;
import android.media.MediaPlayer;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    VideoView videoView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnMusic = findViewById(R.id.music);
        Button btnVideo = findViewById(R.id.video);
        
        videoView = findViewById(R.id.videoView);
        videoView.setZOrderOnTop(true);
        
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        
        btnMusic.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });
        
        btnVideo.setOnClickListener(v -> {
            Uri uri = Uri.parse("android.resource://" + getPackageName() 
                                + "/" + R.raw.video);
            videoView.setVideoURI(uri);
            MediaController controller = new MediaController(MainActivity.this);
            videoView.setMediaController(controller);
            controller.setAnchorView(videoView);
            videoView.start();
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
