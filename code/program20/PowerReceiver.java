package com.example.batterylow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class PowerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int percent = level * 100 / scale;
        if (percent <= 15) {
            Toast.makeText(context, "Battery Low: " + percent + "%", 
                    Toast.LENGTH_SHORT).show();
        }
    }
}
