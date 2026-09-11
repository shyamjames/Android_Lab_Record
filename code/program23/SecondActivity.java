package com.example.assignment_orderplacer;

import android.content.Intent; import android.os.Bundle; import android.widget.Button; import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class SummaryActivity extends AppCompatActivity { TextView tvDetails;
Button btnGenerateInvoice;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_summary); tvDetails = findViewById(R.id.tvDetails);
btnGenerateInvoice = findViewById(R.id.btnGenerateInvoice);

Intent intent = getIntent();
String name = intent.getStringExtra("name"); String mobile = intent.getStringExtra("mobile");
String mealType = intent.getStringExtra("mealType");
ArrayList<String> selectedItems = intent.getStringArrayListExtra("selectedItems"); int totalBill = intent.getIntExtra("totalBill", 0);

StringBuilder itemsStr = new StringBuilder(); if (selectedItems != null) {
for (String item : selectedItems) { itemsStr.append("- ").append(item).append("\n");
}
}
String details = "Customer Name: " + name + "\n\n" + "Mobile Number: " + mobile + "\n\n" +
"Meal Type: " + mealType + "\n\n" +
"Selected Items:\n" + itemsStr.toString() + "\n" + "Total Bill Amount: ₹" + totalBill;
tvDetails.setText(details);

btnGenerateInvoice.setOnClickListener(v -> {
Intent nextIntent = new Intent(SummaryActivity.this, InvoiceActivity.class); nextIntent.putExtra("name", name);
nextIntent.putExtra("mobile", mobile); nextIntent.putExtra("mealType", mealType); nextIntent.putStringArrayListExtra("selectedItems", selectedItems);
nextIntent.putExtra("totalBill", totalBill);
startActivity(nextIntent);
});
}
}