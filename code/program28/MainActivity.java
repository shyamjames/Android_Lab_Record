package com.example.countdemo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
TextView tvCount;
Button btnIncrement, btnDecrement, btnReset;
int count = 0;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
tvCount = findViewById(R.id.tvCount);
btnIncrement = findViewById(R.id.btnIncrement);
btnDecrement = findViewById(R.id.btnDecrement);
btnReset = findViewById(R.id.btnReset);
btnIncrement.setOnClickListener(v -> {
count++;
tvCount.setText(String.valueOf(count));
});
btnDecrement.setOnClickListener(v -> {
count--;
tvCount.setText(String.valueOf(count));
});
btnReset.setOnClickListener(v -> {
count = 0;
tvCount.setText(String.valueOf(count));
});
}

}
