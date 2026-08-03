package com.example.pg16_studentdetails;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button submit = findViewById(R.id.submit);
        EditText name = findViewById(R.id.name);
        EditText mobile = findViewById(R.id.mobile);
        RadioGroup department = findViewById(R.id.department);
        RadioGroup gender = findViewById(R.id.gender);
        CheckBox ai = findViewById(R.id.ai);
        CheckBox cloud = findViewById(R.id.cloud);
        CheckBox auto = findViewById(R.id.autocad);
        CheckBox ship = findViewById(R.id.ship);

        submit.setOnClickListener(v -> {
            int depId = department.getCheckedRadioButtonId();
            RadioButton rdep = findViewById(depId);
            int genId = gender.getCheckedRadioButtonId();
            RadioButton rgen = findViewById(genId);
            StringBuilder sb = new StringBuilder();

            if (ai.isChecked())
                sb.append("AI/ML\n");

            if (cloud.isChecked())
                sb.append("Cloud Computing\n");

            if (auto.isChecked())
                sb.append("AutoCAD\n");

            if (ship.isChecked())
                sb.append("Ship Technology\n");

            Intent intent = new Intent(MainActivity.this, Details.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("mobile", mobile.getText().toString());
            intent.putExtra("courses", sb.toString());
            intent.putExtra("department", rdep.getText().toString());
            intent.putExtra("gender", rgen.getText().toString());
            startActivity(intent);
        });
    }
}
