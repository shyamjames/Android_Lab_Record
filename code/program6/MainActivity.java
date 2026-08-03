package com.example.pg06_datetime;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView1 = findViewById(R.id.textView1);
        final TextView textView2 = findViewById(R.id.textView2);
        final TextView textView3 = findViewById(R.id.textView3);
        final TextView textView4 = findViewById(R.id.textView4);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDateTime = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault());
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                SimpleDateFormat format3 = new SimpleDateFormat("HH:mm:ss z", Locale.getDefault());
                SimpleDateFormat format4 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                textView1.setText(format1.format(currentDateTime));
                textView2.setText(format2.format(currentDateTime));
                textView3.setText(format3.format(currentDateTime));
                textView4.setText(format4.format(currentDateTime));
            }
        });
    }
}
