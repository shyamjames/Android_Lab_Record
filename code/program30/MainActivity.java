package com.example.asynctaskdemo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MainActivity extends AppCompatActivity {
ProgressBar progressBar;
TextView tvProgress, tvStatus;
Button btnStart;
ExecutorService executorService;
Handler mainHandler;
boolean isTaskRunning = false;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

setContentView(R.layout.activity_main);
progressBar = findViewById(R.id.progressBar);
tvProgress = findViewById(R.id.tvProgress);
tvStatus = findViewById(R.id.tvStatus);
btnStart = findViewById(R.id.btnStart);
executorService = Executors.newSingleThreadExecutor();
// Handler tied to main/UI thread
mainHandler = new Handler(Looper.getMainLooper());
btnStart.setOnClickListener(v -> startAsyncTask());
}
private void startAsyncTask() {
if (isTaskRunning) {
Toast.makeText(this, "Task already running...",
Toast.LENGTH_SHORT).show();
return;
}
isTaskRunning = true;
btnStart.setEnabled(false);
tvStatus.setText("Task running in background...");
progressBar.setProgress(0);
tvProgress.setText("0%");
executorService.execute(() -> {
for (int i = 0; i <= 100; i += 5) {
final int progress = i;
try {


Thread.sleep(200); // simulate work (e.g. downloading, processing)
} catch (InterruptedException e) {
e.printStackTrace();
}
// Post progress updates back to the UI thread
mainHandler.post(() -> {
progressBar.setProgress(progress);
tvProgress.setText(progress + "%");
});
}
// Task finished — update UI on main thread
mainHandler.post(() -> {
tvStatus.setText("Task completed!");
btnStart.setEnabled(true);
isTaskRunning = false;
Toast.makeText(MainActivity.this, "Background task finished!",
Toast.LENGTH_SHORT).show();
});
});
}
@Override
protected void onDestroy() {
super.onDestroy();
// Shut down executor to avoid leaks
if (executorService != null) {


executorService.shutdownNow();
}
}}
