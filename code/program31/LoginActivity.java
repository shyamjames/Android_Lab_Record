package com.example.regloginapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity {
EditText etUsername, etPassword;
Button btnLogin;
TextView tvGoToRegister;
DBHelper dbHelper;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_login);


etUsername = findViewById(R.id.etUsername);
etPassword = findViewById(R.id.etPassword);
btnLogin = findViewById(R.id.btnLogin);
tvGoToRegister = findViewById(R.id.tvGoToRegister);
dbHelper = new DBHelper(this);
btnLogin.setOnClickListener(v -> loginUser());
tvGoToRegister.setOnClickListener(v -> {
startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
});
}
private void loginUser() {
String username = etUsername.getText().toString().trim();
String password = etPassword.getText().toString().trim();
if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
Toast.makeText(this, "Please enter username and password",
Toast.LENGTH_SHORT).show();
return;
}
boolean isValid = dbHelper.checkLogin(username, password);
if (isValid) {
Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
intent.putExtra("USERNAME", username);
intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |


Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);
finish();
} else {
Toast.makeText(this, "Invalid username or password",
Toast.LENGTH_SHORT).show();
}
}
}
