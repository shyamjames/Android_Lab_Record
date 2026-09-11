package com.example.assignment_hospitalbill;

import android.content.Intent; import android.os.Bundle; import android.view.Menu; import android.view.MenuItem; import android.view.View; import android.widget.Button;
import android.widget.CheckBox; import android.widget.EditText; import android.widget.RadioButton; import android.widget.RadioGroup; import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity; import java.util.ArrayList;
public class MainActivity extends AppCompatActivity { EditText id, name, age;
RadioGroup gender;
CheckBox consultation, bloodtest, xray, mri; Button next;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

id = findViewById(R.id.id);
name = findViewById(R.id.name); age = findViewById(R.id.age);
gender = findViewById(R.id.gender); consultation = findViewById(R.id.consultation); bloodtest = findViewById(R.id.bloodtest);
xray = findViewById(R.id.xray); mri = findViewById(R.id.mri); next = findViewById(R.id.next);

next.setOnClickListener(new View.OnClickListener() { @Override
public void onClick(View v) {
String pid = id.getText().toString().trim();
String pname = name.getText().toString().trim(); String page = age.getText().toString().trim();

if (pid.isEmpty()) { id.setError("Patient ID is required");
Toast.makeText(MainActivity.this, "Please enter Patient ID", Toast.LENGTH_SHORT).show();
return;
}
if (pname.isEmpty()) { name.setError("Name is required");
Toast.makeText(MainActivity.this, "Please enter Patient Name", Toast.LENGTH_SHORT).show();
return;
}
if (page.isEmpty()) { age.setError("Age is required");
Toast.makeText(MainActivity.this, "Please enter Age", Toast.LENGTH_SHORT).show();
return;
}
int selected = gender.getCheckedRadioButtonId(); if (selected == -1) {
Toast.makeText(MainActivity.this, "Please select Gender", Toast.LENGTH_SHORT).show();
return;
}
RadioButton rb = findViewById(selected);
String pgender = rb.getText().toString(); ArrayList<String> services = new ArrayList<>(); int fee = 0;

if (consultation.isChecked()) { services.add("General Checkup (₹300)"); fee += 300;
}
if (bloodtest.isChecked()) { services.add("Blood Test (₹500)"); fee += 500;
}
if (xray.isChecked()) { services.add("X-Ray Scan (₹800)"); fee += 800;
}
if (mri.isChecked()) { services.add("MRI Scan (₹2500)"); fee += 2500;
}

if (services.isEmpty()) {
Toast.makeText(MainActivity.this, "Please select at least one medical service", Toast.LENGTH_SHORT).show();
return;
}

Intent intent = new Intent(MainActivity.this, DepartmentActivity.class); intent.putExtra("id", pid);
intent.putExtra("name", pname); intent.putExtra("age", page); intent.putExtra("gender", pgender); intent.putStringArrayListExtra("services", services); intent.putExtra("fee", fee);
startActivity(intent);
}
});
}
@Override
public boolean onCreateOptionsMenu(Menu menu) { getMenuInflater().inflate(R.menu.options_menu, menu); return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) { int itemid = item.getItemId();
if (itemid == R.id.home || itemid == R.id.register) { id.setText("");
name.setText("");
age.setText(""); gender.clearCheck(); consultation.setChecked(false); bloodtest.setChecked(false); xray.setChecked(false); mri.setChecked(false);
Toast.makeText(this, "New Registration Form Ready", Toast.LENGTH_SHORT).show(); return true;
} else if (itemid == R.id.exit) { finish();
return true;
}
return super.onOptionsItemSelected(item);
}
}