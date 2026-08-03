package com.example.a2screen_welcome;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(v-> {
            Intent intent = new Intent(MainActivity.this, 
                                       SecondaryActivity.class);
            startActivity(intent);
        });
    }
}
