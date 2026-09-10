package com.example.pg20_alarmapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(v -> {
            AlarmManager alarmManager = (AlarmManager) 
                    getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(MainActivity.this, 
                    AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, 
                    intent, PendingIntent.FLAG_IMMUTABLE);
            long time = System.currentTimeMillis() + 10000; // 10 seconds
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        });
    }
}
