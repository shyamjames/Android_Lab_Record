package com.example.librarydatabase;
 
import android.database.Cursor;
import android.os.Bundle;
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
 
    EditText bookid, bookname, authorname, pubname, publishyear, price, stock;
    Button insert, view;
    DBHelper DB;
 
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
 
        bookid = findViewById(R.id.bookid);
        bookname = findViewById(R.id.bookname);
        authorname = findViewById(R.id.authorname);
        pubname = findViewById(R.id.pubname);
        publishyear = findViewById(R.id.publishyear);
        price = findViewById(R.id.price);
        stock = findViewById(R.id.stock);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);
 
        insert.setOnClickListener(view -> {
            String idTXT = bookid.getText().toString();
            String nameTXT = bookname.getText().toString();
            String authorTXT = authorname.getText().toString();
            String pubTXT = pubname.getText().toString();
            String yearTXT = publishyear.getText().toString();
            String priceTXT = price.getText().toString();
            String stockTXT = stock.getText().toString();
 
            if (idTXT.isEmpty() || nameTXT.isEmpty() || authorTXT.isEmpty() || pubTXT.isEmpty() || yearTXT.isEmpty() || priceTXT.isEmpty() || stockTXT.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                return;
            }
 
            Boolean checkInsertData = DB.insertBookData(idTXT, nameTXT, authorTXT, pubTXT, yearTXT, priceTXT, stockTXT);
            if (checkInsertData) {
                Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                // Clear fields after insertion
                bookid.setText("");
                bookname.setText("");
                authorname.setText("");
                pubname.setText("");
                publishyear.setText("");
                price.setText("");
                stock.setText("");
            } else {
                Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
 
        view.setOnClickListener(view -> {
            Cursor res = DB.getdata();
            if (res.getCount() == 0) {
                Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("Book ID: ").append(res.getString(0)).append("\n");
                buffer.append("Book Name: ").append(res.getString(1)).append("\n");
                buffer.append("Author Name: ").append(res.getString(2)).append("\n");
                buffer.append("Publisher: ").append(res.getString(3)).append("\n");
                buffer.append("Year: ").append(res.getString(4)).append("\n");
                buffer.append("Price: ").append(res.getString(5)).append("\n");
                buffer.append("Stock: ").append(res.getString(6)).append("\n\n");
            }
 
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Book Details");
            builder.setMessage(buffer.toString());
            builder.show();
        });
    }
}