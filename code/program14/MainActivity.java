package com.example.radiocheckbox;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup gender;
    CheckBox ten, twelve, UG, PG;
    Button submit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gender = findViewById(R.id.gender);
        ten = findViewById(R.id.ten);
        twelve = findViewById(R.id.twelve);
        UG = findViewById(R.id.UG);
        PG = findViewById(R.id.PG);
        submit = findViewById(R.id.submit);
        
        submit.setOnClickListener(v ->{
            int selectedId = gender.getCheckedRadioButtonId();
            RadioButton rb = findViewById(selectedId);
            String gen = rb.getText().toString();
            StringBuilder education = new StringBuilder();
            
            if (ten.isChecked()) education.append("Tenth ");
            if (twelve.isChecked()) education.append("Plus Two ");
            if (UG.isChecked()) education.append("Undergraduate ");
            if (PG.isChecked()) education.append("Postgraduate ");
            
            Toast.makeText(this, gen + " | " + education, 
                           Toast.LENGTH_LONG).show();
        });
    }
}
