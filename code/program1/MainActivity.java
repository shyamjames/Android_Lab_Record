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
    EditText name,email;
    Button btn;
    TextView t1,t2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nametxt);
        email = findViewById(R.id.emailTxt);
        btn = findViewById(R.id.btnclick);
        t1 = findViewById(R.id.txtView1);
        t2 = findViewById(R.id.txtView2);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String n1 = name.getText().toString();
                String n2 = email.getText().toString();
                t1.setText(n1);
                t2.setText(n2);
            }
        });
    }
}
