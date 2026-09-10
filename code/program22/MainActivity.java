package com.example.contactlist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] contacts = {
            "Albin - 9876543210", 
            "Shyam - 9123456780", 
            "Jubin - 9988776655",
            "Sharvin - 9876501234", 
            "Anandhu - 9012345678"
        };

        ListView listView = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);
    }
}
