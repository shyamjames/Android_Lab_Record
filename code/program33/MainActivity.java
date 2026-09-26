package com.example.shakecolor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class MainActivity extends AppCompatActivity implements
SensorEventListener {
SensorManager sensorManager;

Sensor accelerometer;
Random random;
float lastX, lastY, lastZ;
boolean firstReading = true;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
accelerometer =
sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
}

random = new Random();

@Override
protected void onResume() {
super.onResume();

}

sensorManager.registerListener(
this,
accelerometer,
SensorManager.SENSOR_DELAY_NORMAL
);

@Override
protected void onPause() {
super.onPause();
}

sensorManager.unregisterListener(this);

@Override
public void onSensorChanged(SensorEvent event) {
float x = event.values[0];

float y = event.values[1];
float z = event.values[2];
if (firstReading) {
lastX = x;
lastY = y;
lastZ = z;
firstReading = false;
return;
}
float change = Math.abs(x - lastX)
+ Math.abs(y - lastY)
+ Math.abs(z - lastZ);
if (change > 10) {
changeBackgroundColor();
}

}

lastX = x;
lastY = y;
lastZ = z;

private void changeBackgroundColor() {
int color = Color.rgb(
random.nextInt(256),
random.nextInt(256),
random.nextInt(256)
);
findViewById(R.id.mainLayout).setBackgroundColor(color);

}

}
@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {
}
