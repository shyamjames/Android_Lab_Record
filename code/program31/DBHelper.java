package com.example.regloginapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
private static final String DATABASE_NAME = "UserDB";
private static final int DATABASE_VERSION = 1;
public static final String TABLE_NAME = "users";
public static final String COL_ID = "id";
public static final String COL_USERNAME = "username";
public static final String COL_EMAIL = "email";
public static final String COL_PASSWORD = "password";
public DBHelper(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
@Override
public void onCreate(SQLiteDatabase db) {
String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
COL_USERNAME + " TEXT UNIQUE, " +


COL_EMAIL + " TEXT, " +
COL_PASSWORD + " TEXT)";
db.execSQL(createTable);
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
onCreate(db);
}
public long registerUser(String username, String email, String password) {
SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put(COL_USERNAME, username);
values.put(COL_EMAIL, email);
values.put(COL_PASSWORD, password);
long result = db.insert(TABLE_NAME, null, values);
db.close();
return result;
}
public boolean isUsernameExists(String username) {
SQLiteDatabase db = this.getReadableDatabase();
Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID},
COL_USERNAME + "=?", new String[]{username}, null, null, null);
boolean exists = cursor.getCount() > 0;
cursor.close();

db.close();
return exists;
}
public boolean checkLogin(String username, String password) {
SQLiteDatabase db = this.getReadableDatabase();
Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID},
COL_USERNAME + "=? AND " + COL_PASSWORD + "=?",
new String[]{username, password}, null, null, null);
boolean isValid = cursor.getCount() > 0;
cursor.close();
db.close();
return isValid;
}
}
RegisterActivity.java
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
LoginActivity.java
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
HomeActivity.java
package com.example.regloginapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class HomeActivity extends AppCompatActivity {
TextView tvWelcome;
Button btnLogout;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_home);
tvWelcome = findViewById(R.id.tvWelcome);
btnLogout = findViewById(R.id.btnLogout);


String username = getIntent().getStringExtra("USERNAME");
tvWelcome.setText("Welcome, " + username + "!");
btnLogout.setOnClickListener(v -> {
Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);
finish();
});
}
}
activity_register.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
android:padding="24dp"
android:gravity="center">
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Register"
android:textSize="26sp"


android:textStyle="bold"
android:paddingBottom="30dp"/>
<EditText
android:id="@+id/etUsername"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Username"/>
<EditText
android:id="@+id/etEmail"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Email"
android:inputType="textEmailAddress"
android:layout_marginTop="10dp"/>
<EditText
android:id="@+id/etPassword"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Password"
android:inputType="textPassword"
android:layout_marginTop="10dp"/>


<EditText
android:id="@+id/etConfirmPassword"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Confirm Password"
android:inputType="textPassword"
android:layout_marginTop="10dp"/>
<Button
android:id="@+id/btnRegister"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="20dp"
android:text="Register"/>
<TextView
android:id="@+id/tvGoToLogin"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Already have an account? Login"
android:textColor="#2196F3"
android:paddingTop="16dp"/>
</LinearLayout>
activity_login.xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
android:padding="24dp"
android:gravity="center">
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Login"
android:textSize="26sp"
android:textStyle="bold"
android:paddingBottom="30dp"/>
<EditText
android:id="@+id/etUsername"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Username"/>
<EditText
android:id="@+id/etPassword"
android:layout_width="match_parent"
android:layout_height="wrap_content"

android:hint="Password"
android:inputType="textPassword"
android:layout_marginTop="10dp"/>
<Button
android:id="@+id/btnLogin"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="20dp"
android:text="Login"/>
<TextView
android:id="@+id/tvGoToRegister"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Don't have an account? Register"
android:textColor="#2196F3"
android:paddingTop="16dp"/>
</LinearLayout>
activity_home.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"

android:gravity="center"
android:padding="24dp">
<TextView
android:id="@+id/tvWelcome"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Welcome!"
android:textSize="24sp"
android:textStyle="bold"
android:paddingBottom="30dp"/>
<Button
android:id="@+id/btnLogout"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Logout"/>
</LinearLayout>
