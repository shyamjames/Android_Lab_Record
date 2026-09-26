package com.example.flashlight;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
Button flashlightButton;
CameraManager cameraManager;
String cameraId;
boolean isFlashlightOn = false;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
flashlightButton = findViewById(R.id.flashlightButton);
cameraManager = (CameraManager)
getSystemService(CAMERA_SERVICE);
try {
cameraId = cameraManager.getCameraIdList()[0];
} catch (CameraAccessException e) {
e.printStackTrace();
}
flashlightButton.setOnClickListener(v -> {
try {

isFlashlightOn = !isFlashlightOn;
cameraManager.setTorchMode(cameraId, isFlashlightOn);
if (isFlashlightOn) {
flashlightButton.setText("Turn OFF Flashlight");
} else {
flashlightButton.setText("Turn ON Flashlight");
}
}

}

}

catch (CameraAccessException e) {
e.printStackTrace();
}
});
