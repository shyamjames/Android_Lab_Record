package com.example.studentdatabase;
 
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
 
public class MainActivity extends AppCompatActivity {
 
    DatabaseHelper myDb;
    EditText editName, editAge;
    Button btnAdd, btnDetails;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
 
        myDb = new DatabaseHelper(this);
 
        editName = findViewById(R.id.edit_name);
        editAge = findViewById(R.id.edit_age);
        btnAdd = findViewById(R.id.btn_add);
        btnDetails = findViewById(R.id.btn_details);
 
        addData();
        viewAll();
    }
 
    public void addData() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editName.getText().toString(),
                        editAge.getText().toString());
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "row inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
 
    public void viewAll() {
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }
 
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()) {
                    buffer.append("Id :").append(res.getString(0)).append("\n");
                    buffer.append("Name :").append(res.getString(1)).append("\n");
                    buffer.append("Age :").append(res.getString(2)).append("\n\n");
                }
 
                showMessage("Data", buffer.toString());
            }
        });
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}