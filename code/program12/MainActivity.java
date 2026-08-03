package com.example.pg12_optionsmenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Toast.makeText(this, "Settings Selected", 
                           Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.about) {
            Toast.makeText(this, "About Selected", 
                           Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.exit) {
            finish();
        }
        return true;
    }
}
