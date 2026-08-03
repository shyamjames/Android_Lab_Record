package com.example.pg04_calculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Button ad, sub, div, mult;
    EditText no1, no2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        ad = findViewById(R.id.ad);
        sub = findViewById(R.id.sub);
        div = findViewById(R.id.div);
        mult =findViewById(R.id.mult);
        no1 = findViewById(R.id.no1);
        no2 = findViewById(R.id.no2);

        ad.setOnClickListener(v -> {
            int a = Integer.parseInt(no1.getText().toString());
            int b = Integer.parseInt(no2.getText().toString());
            int res = a + b;
            result.setText(res+"");
        });

        sub.setOnClickListener(v -> {
            int a = Integer.parseInt(no1.getText().toString());
            int b = Integer.parseInt(no2.getText().toString());
            int res = a - b;
            result.setText(res+"");
        });

        mult.setOnClickListener(v -> {
            int a = Integer.parseInt(no1.getText().toString());
            int b = Integer.parseInt(no2.getText().toString());
            int res = a * b;
            result.setText(res+"");

        });

        div.setOnClickListener(v -> {
            int a = Integer.parseInt(no1.getText().toString());
            int b = Integer.parseInt(no2.getText().toString());
            if(b!=0) {
                int res = a / b;
                result.setText(res+"");
            }
            else
                result.setText("Error: Divsion / 0");
        });
    }
}
