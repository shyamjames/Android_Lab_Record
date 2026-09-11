package com.example.assignment_orderplacer;

import android.content.Intent; import android.os.Bundle; import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity; import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity { TextView tvInvoiceDetails, tvThankYou;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_invoice); tvInvoiceDetails = findViewById(R.id.tvInvoiceDetails); tvThankYou = findViewById(R.id.tvThankYou);

Intent intent = getIntent();
String name = intent.getStringExtra("name"); String mobile = intent.getStringExtra("mobile");
String mealType = intent.getStringExtra("mealType");
ArrayList<String> selectedItems = intent.getStringArrayListExtra("selectedItems"); int totalBill = intent.getIntExtra("totalBill", 0);

StringBuilder itemsStr = new StringBuilder(); if (selectedItems != null) {
for (String item : selectedItems) { itemsStr.append("- ").append(item).append("\n");
}
}
double discountPercent = 0; if (totalBill > 800) {
discountPercent = 15.0;
} else if (totalBill >= 500) { discountPercent = 10.0;
} else {
discountPercent = 0.0;
}

double discountAmount = (totalBill * discountPercent) / 100.0; double finalAmount = totalBill - discountAmount;

String invoiceText = "Customer Name: " + name + "\n\n" + "Mobile Number: " + mobile + "\n\n" +
"Meal Type: " + mealType + "\n\n" +
"Ordered Items:\n" + itemsStr.toString() + "\n" + "Total Bill: ₹" + totalBill + "\n" +
"Discount: ₹" + discountAmount + " (" + (int)discountPercent + "%)\n" + "Final Amount Payable: ₹" + finalAmount;

tvInvoiceDetails.setText(invoiceText);
}
}