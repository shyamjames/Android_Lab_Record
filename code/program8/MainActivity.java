package com.example.pg08_alertbox;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Alert");
            builder.setMessage("What do you want to click?");
            
            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(MainActivity.this, "You clicked Yes", 
                               Toast.LENGTH_SHORT).show();
            });
            
            builder.setNegativeButton("No", (dialog, which) -> {
                Toast.makeText(MainActivity.this, "You clicked No", 
                               Toast.LENGTH_SHORT).show();
            });
            
            builder.setNeutralButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });
            
            builder.show();
        });
    }
}
