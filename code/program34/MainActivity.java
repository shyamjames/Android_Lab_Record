package com.example.communicationapp;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
Button smsButton, emailButton, callButton;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
smsButton = findViewById(R.id.smsButton);
emailButton = findViewById(R.id.emailButton);
callButton = findViewById(R.id.callButton);
// Send SMS
smsButton.setOnClickListener(v -> {
Intent intent = new Intent(Intent.ACTION_SENDTO);
intent.setData(Uri.parse("smsto:9876543210"));
intent.putExtra("sms_body", "Hello! This is a test message.");
startActivity(intent);
});
// Send Email
emailButton.setOnClickListener(v -> {
Intent intent = new Intent(Intent.ACTION_SENDTO);
intent.setData(Uri.parse("mailto:example@gmail.com"));
intent.putExtra(Intent.EXTRA_SUBJECT, "Test Email");
intent.putExtra(Intent.EXTRA_TEXT, "Hello! This is a test email.");
startActivity(intent);
});
// Make Call
callButton.setOnClickListener(v -> {
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:9876543210"));
startActivity(intent);
});

}

}
