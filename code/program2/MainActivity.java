package com.example.labcycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText m1, m2, m3;
    Button btn;
    TextView t1, t2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        m1 = findViewById(R.id.mark1);
        m2 = findViewById(R.id.mark2);
        m3 = findViewById(R.id.mark3);
        btn = findViewById(R.id.btncal);
        t1 = findViewById(R.id.textView4);
        t2 = findViewById(R.id.textView5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1 = Integer.parseInt(m1.getText().toString());
                int n2 = Integer.parseInt(m2.getText().toString());
                int n3 = Integer.parseInt(m3.getText().toString());
                int sums = n1 + n2 + n3;
                int avg = sums / 3;
                t1.setText("Sum is " + sums);
                t2.setText("Average is " + avg);
            }
        });
    }
}
