package com.example.assignment_hospitalbill;

import android.content.Intent; import android.os.Bundle; import android.view.Menu; import android.view.MenuItem; import android.view.View; import android.widget.Button; import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity; import java.util.ArrayList;
public class BillActivity extends AppCompatActivity { TextView details, category;
Button finish;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState); setContentView(R.layout.activity_bill); details = findViewById(R.id.details); category = findViewById(R.id.category); finish = findViewById(R.id.finish);

Intent intent = getIntent();
String id = intent.getStringExtra("id");
String name = intent.getStringExtra("name"); String age = intent.getStringExtra("age");
String gender = intent.getStringExtra("gender");
ArrayList<String> services = intent.getStringArrayListExtra("services"); int fee = intent.getIntExtra("fee", 0);
String department = intent.getStringExtra("department"); int consult = intent.getIntExtra("consult", 0);
int total = intent.getIntExtra("total", 0);

StringBuilder builder = new StringBuilder(); if (services != null) {
for (String s : services) {
builder.append(" • ").append(s).append("\n");
}
}

String cat;
if (total > 3000) {
cat = "Specialized Tertiary Care";
} else if (total >= 1500) {
cat = "Secondary Medical Care";
} else {
cat = "Primary General Care";
}

String bill = "Patient ID: " + id + "\n" + "Patient Name: " + name + "\n" +
"Age: " + age + " | Gender: " + gender + "\n\n" +
"Consultation Dept: " + department + " (₹" + consult + ")\n\n" + "Medical Services:\n" + builder + "\n" +
"Services Charges: ₹" + fee + "\n" +
"	\n" +
"TOTAL HOSPITAL BILL: ₹" + total;

details.setText(bill); category.setText("Treatment Category: " + cat);

finish.setOnClickListener(new View.OnClickListener() { @Override
public void onClick(View v) {
Intent home = new Intent(BillActivity.this, MainActivity.class); home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(home);
}
});
}

@Override
public boolean onCreateOptionsMenu(Menu menu) { getMenuInflater().inflate(R.menu.options_menu, menu); return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) { int itemid = item.getItemId();
if (itemid == R.id.home || itemid == R.id.register) {
Intent intent = new Intent(BillActivity.this, MainActivity.class); intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(intent);
return true;
} else if (itemid == R.id.exit) { finishAffinity();
return true;
}
return super.onOptionsItemSelected(item);
}
}