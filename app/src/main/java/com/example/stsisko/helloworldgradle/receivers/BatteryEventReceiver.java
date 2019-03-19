package com.example.baristademoapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.BatteryManager;

import com.example.baristademoapp.R;
import com.example.baristademoapp.activities.BatteryTestActivity;
import com.example.baristademoapp.helpers.ThreadSleepingTime;

public class BatteryEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("BATTERY STATUS CHANGED" );
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);

        // Calculate the battery charged percentage
        float percentage = level/ (float) scale;
        System.out.println("BATTERY LEVEL %: "+percentage);
        if(percentage < 0.5) {
            System.out.println("BATTERY RECEIVER DETECTED LOW BATTERY" );
            ThreadSleepingTime.setSlowTimer();
            BatteryTestActivity.getInstance().findViewById(R.id.battery_layout).setBackgroundColor(Color.GRAY);
        }
        else{
            ThreadSleepingTime.setFastTimer();
            BatteryTestActivity.getInstance().findViewById(R.id.battery_layout).setBackgroundColor(Color.WHITE);
        }
    }
}
