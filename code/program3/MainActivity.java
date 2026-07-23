package com.example.labcycle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(v ->
                Toast.makeText(this, "LinearLayout Button 1", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(v ->
                Toast.makeText(this, "LinearLayout Button 2", Toast.LENGTH_SHORT).show());
        button3.setOnClickListener(v ->
                Toast.makeText(this, "RelativeLayout Button", Toast.LENGTH_SHORT).show());
    }
}
