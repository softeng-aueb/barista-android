package com.example.stsisko.helloworldgradle.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;

import com.example.stsisko.helloworldgradle.R;
import com.example.stsisko.helloworldgradle.activities.BatteryTestActivity;
import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;
import com.example.stsisko.helloworldgradle.helpers.ThreadSleepingTime;

public class WifiEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("WIFI STATUS CHANGED" );
        final String action = intent.getAction();

        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        boolean connected = info.isConnected();

        WifiTestAcivity.wifiStatusChanged(connected);

    }
}
