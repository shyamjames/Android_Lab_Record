package com.example.pg17_employeedetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name, id, sal;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        sal = findViewById(R.id.sal);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, 
                                           SecondaryActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("id", id.getText().toString());
                intent.putExtra("salary", sal.getText().toString());
                startActivity(intent);
            }
        });
    }
}
