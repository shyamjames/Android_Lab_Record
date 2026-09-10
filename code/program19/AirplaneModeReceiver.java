package com.example.pg19_airplanemode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.ACTION_AIRPLANE_MODE_CHANGED.equals(
                intent.getAction())) {
            boolean isOn = intent.getBooleanExtra("state", false);
            String msg = isOn ? "Airplane Mode turned ON" 
                              : "Airplane Mode turned OFF";
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
