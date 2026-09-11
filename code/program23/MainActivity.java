package com.example.assignment_orderplacer;

import android.content.Intent; import android.os.Bundle; import android.view.View; import android.widget.Button;
import android.widget.CheckBox; import android.widget.EditText; import android.widget.RadioButton; import android.widget.RadioGroup; import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity; import java.util.ArrayList;

public class MainActivity extends AppCompatActivity { EditText name, mobile;
RadioGroup mealtype;
CheckBox pizza, burger, sandwich, softdrink; Button placeOrder;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

name = findViewById(R.id.name); mobile = findViewById(R.id.mobile);
mealtype = findViewById(R.id.mealtype); pizza = findViewById(R.id.pizza);
burger = findViewById(R.id.burger);
sandwich = findViewById(R.id.sandwich);
softdrink = findViewById(R.id.softdrink); placeOrder = findViewById(R.id.placeOrder);

placeOrder.setOnClickListener(new View.OnClickListener() { @Override
public void onClick(View v) {
String customerName = name.getText().toString().trim(); String mobileNo = mobile.getText().toString().trim();
if (customerName.isEmpty()) { name.setError("Customer Name cannot be empty");
Toast.makeText(MainActivity.this, "Please enter customer name", Toast.LENGTH_SHORT).show();
return;
}
if (mobileNo.length() != 10) {
mobile.setError("Mobile number must be exactly 10 digits"); Toast.makeText(MainActivity.this, "Mobile number must contain 10 digits",
Toast.LENGTH_SHORT).show(); return;
}

int selectedMealId = mealtype.getCheckedRadioButtonId(); if (selectedMealId == -1) {
Toast.makeText(MainActivity.this, "Please select a meal type", Toast.LENGTH_SHORT).show();
return;
}
RadioButton rb = findViewById(selectedMealId); String selectedMeal = rb.getText().toString();

ArrayList<String> selectedItems = new ArrayList<>(); int totalBill = 0;

if (pizza.isChecked()) { selectedItems.add("Pizza - ₹250"); totalBill += 250;
}
if (burger.isChecked()) { selectedItems.add("Burger - ₹150");
totalBill += 150;
}
if (sandwich.isChecked()) { selectedItems.add("Sandwich - ₹120"); totalBill += 120;
}
if (softdrink.isChecked()) { selectedItems.add("Soft Drink - ₹60"); totalBill += 60;
}

if (selectedItems.isEmpty()) {
Toast.makeText(MainActivity.this, "Please select at least one food item", Toast.LENGTH_SHORT).show();
return;
}

Intent intent = new Intent(MainActivity.this, SummaryActivity.class); intent.putExtra("name", customerName);
intent.putExtra("mobile", mobileNo); intent.putExtra("mealType", selectedMeal); intent.putStringArrayListExtra("selectedItems", selectedItems); intent.putExtra("totalBill", totalBill);
startActivity(intent);
}
});
}
}