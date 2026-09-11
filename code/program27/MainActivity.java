package com.example.pg27_dbview;

import android.database.Cursor; import android.os.Bundle;
import android.widget.ArrayAdapter; import android.widget.Button; import android.widget.EditText; import android.widget.GridView; import android.widget.ListView; import android.widget.Toast;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity { DBHelper dbHelper;
EditText name; Button submit; ListView list; GridView grid;
ArrayList<String> productList; ArrayAdapter<String> adapter;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

dbHelper = new DBHelper(this); name = findViewById(R.id.name); submit = findViewById(R.id.submit); list = findViewById(R.id.list);
grid = findViewById(R.id.grid);
productList = new ArrayList<>();
adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList); list.setAdapter(adapter);
grid.setAdapter(adapter); loadProducts();
submit.setOnClickListener(v -> {
String pName = name.getText().toString().trim(); if (!pName.isEmpty()) {
dbHelper.insertProduct(pName);
Toast.makeText(MainActivity.this, "Product added", Toast.LENGTH_SHORT).show(); name.setText("");
loadProducts();
} else {
Toast.makeText(MainActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
}
});
}

private void loadProducts() { productList.clear();
Cursor cursor = dbHelper.getAllProducts(); while (cursor.moveToNext()) {
productList.add(cursor.getString(1));
}
cursor.close(); adapter.notifyDataSetChanged();
}
}