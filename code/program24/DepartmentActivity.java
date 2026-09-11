package com.example.assignment_hospitalbill;

import android.content.Intent; import android.os.Bundle; import android.view.Menu; import android.view.MenuItem; import android.view.View; import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView; import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity; import java.util.ArrayList;

public class DepartmentActivity extends AppCompatActivity { TextView info;
RadioGroup dept; Button calculate;
String id, name, age, gender; ArrayList<String> services; int fee;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_department);

info = findViewById(R.id.info); dept = findViewById(R.id.dept);
calculate = findViewById(R.id.calculate);

Intent incoming = getIntent();
id = incoming.getStringExtra("id");
name = incoming.getStringExtra("name"); age = incoming.getStringExtra("age"); gender = incoming.getStringExtra("gender");
services = incoming.getStringArrayListExtra("services"); fee = incoming.getIntExtra("fee", 0);
info.setText("Patient: " + name + " (" + id + ")\nAge: " + age + " | Gender: " + gender);

calculate.setOnClickListener(new View.OnClickListener() { @Override
public void onClick(View v) {
int selected = dept.getCheckedRadioButtonId(); if (selected == -1) {
Toast.makeText(DepartmentActivity.this, "Please select a Consultation Department", Toast.LENGTH_SHORT).show();
return;
}

String department = ""; int consult = 0;

if (selected == R.id.deptgen) { department = "General Medicine"; consult = 300;
} else if (selected == R.id.deptcardio) { department = "Cardiology"; consult = 800;
} else if (selected == R.id.deptortho) { department = "Orthopedics"; consult = 600;
} else if (selected == R.id.deptpedia) { department = "Pediatrics"; consult = 400;
}

int total = consult + fee;
Intent intent = new Intent(DepartmentActivity.this, BillActivity.class); intent.putExtra("id", id);
intent.putExtra("name", name); intent.putExtra("age", age); intent.putExtra("gender", gender); intent.putStringArrayListExtra("services", services); intent.putExtra("fee", fee); intent.putExtra("department", department); intent.putExtra("consult", consult); intent.putExtra("total", total);
startActivity(intent);
}
});
}

@Override
public boolean onCreateOptionsMenu(Menu menu) { getMenuInflater().inflate(R.menu.options_menu, menu); return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) { int itemid = item.getItemId();
if (itemid == R.id.home || itemid == R.id.register) {
Intent intent = new Intent(DepartmentActivity.this, MainActivity.class); intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(intent);
return true;
} else if (itemid == R.id.exit) { finishAffinity();
return true;
}
return super.onOptionsItemSelected(item);
}
}