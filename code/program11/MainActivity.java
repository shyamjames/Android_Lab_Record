package com.example.pg11_internetvideo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnVideo = findViewById(R.id.play);
        videoView = findViewById(R.id.videoView);
        videoView.setZOrderOnTop(true);
        
        btnVideo.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.learningcontainer.com/"
                                + "wp-content/uploads/2020/05/"
                                + "sample-mp4-file.mp4");
            videoView.setVideoURI(uri);
            MediaController controller = 
                    new MediaController(MainActivity.this);
            videoView.setMediaController(controller);
            controller.setAnchorView(videoView);
            videoView.start();
        });
    }
}
