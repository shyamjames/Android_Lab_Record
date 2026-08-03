package com.example.pg17_employeedetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity {
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        result = findViewById(R.id.result);
        Intent intent = getIntent();
        
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String id = intent.getStringExtra("id");
            String salaryStr = intent.getStringExtra("salary");
            
            double basic = 0;
            try {
                if (salaryStr != null) {
                    basic = Double.parseDouble(salaryStr);
                }
            } catch (Exception e) {
                // handle parsing error
            }
            
            double hra = basic * 0.15;
            double da = basic * 0.10;
            double gross = basic + hra + da;

            result.setText(
                    "Employee Details\n\n" +
                    "Name : " + name +
                    "\nEmployee ID : " + id +
                    "\n\nBasic Salary : " + basic +
                    "\nHRA (15%) : " + hra +
                    "\nDA (10%) : " + da +
                    "\nGross Salary : " + gross
            );
        }
    }
}
