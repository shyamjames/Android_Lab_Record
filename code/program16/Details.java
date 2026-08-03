package com.example.pg16_studentdetails;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
        String name = getIntent().getStringExtra("name");
        String mobile = getIntent().getStringExtra("mobile");
        String department = getIntent().getStringExtra("department");
        String gender = getIntent().getStringExtra("gender");
        String courses = getIntent().getStringExtra("courses");
        
        TextView details = findViewById(R.id.details);
        details.setText("Name: " + name + 
                        "\nMobile: " + mobile + 
                        "\nDepartment: " + department + 
                        "\nGender: " + gender + 
                        "\nCourses:\n" + courses);
    }
}
