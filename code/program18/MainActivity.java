package com.example.broadcast;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String MY_ACTION = "com.example.broadcast.MY_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send = findViewById(R.id.send);
        send.setOnClickListener(v -> {
            Intent intent = new Intent(MY_ACTION);
            intent.putExtra("message", "Hello from MainActivity!!");
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });
    }
}
