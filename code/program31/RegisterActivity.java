package com.example.regloginapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class RegisterActivity extends AppCompatActivity {

EditText etUsername, etEmail, etPassword, etConfirmPassword;
Button btnRegister;
TextView tvGoToLogin;
DBHelper dbHelper;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_register);
etUsername = findViewById(R.id.etUsername);
etEmail = findViewById(R.id.etEmail);
etPassword = findViewById(R.id.etPassword);
etConfirmPassword = findViewById(R.id.etConfirmPassword);
btnRegister = findViewById(R.id.btnRegister);
tvGoToLogin = findViewById(R.id.tvGoToLogin);
dbHelper = new DBHelper(this);
btnRegister.setOnClickListener(v -> registerUser());
tvGoToLogin.setOnClickListener(v -> {
startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
finish();
});
}
private void registerUser() {
String username = etUsername.getText().toString().trim();
String email = etEmail.getText().toString().trim();
String password = etPassword.getText().toString().trim();

String confirmPassword = etConfirmPassword.getText().toString().trim();
if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) ||
TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
Toast.makeText(this, "Please fill all fields",
Toast.LENGTH_SHORT).show();
return;
}
if (!password.equals(confirmPassword)) {
Toast.makeText(this, "Passwords do not match",
Toast.LENGTH_SHORT).show();
return;
}
if (dbHelper.isUsernameExists(username)) {
Toast.makeText(this, "Username already taken",
Toast.LENGTH_SHORT).show();
return;
}
long result = dbHelper.registerUser(username, email, password);
if (result != -1) {
Toast.makeText(this, "Registration successful! Please login.",
Toast.LENGTH_SHORT).show();
startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
finish();
} else {
Toast.makeText(this, "Registration failed",
Toast.LENGTH_SHORT).show();


}
}
}
