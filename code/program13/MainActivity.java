package com.example.context_menu;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View layout;
    TextView tx1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.main);
        tx1 = findViewById(R.id.tx1);
        registerForContextMenu(tx1);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, 
                                    ContextMenu.ContextMenuInfo menuinfo){
        super.onCreateContextMenu(menu, v, menuinfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }
    
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.red){
            layout.setBackgroundColor(Color.RED);
        }
        if (item.getItemId() == R.id.blue) {
            layout.setBackgroundColor(Color.BLUE);
        }
        if (item.getItemId() == R.id.green) {
            layout.setBackgroundColor(Color.GREEN);
        }
        if (item.getItemId() == R.id.yellow) {
            layout.setBackgroundColor(Color.YELLOW);
        }
        return false;
    }
}
