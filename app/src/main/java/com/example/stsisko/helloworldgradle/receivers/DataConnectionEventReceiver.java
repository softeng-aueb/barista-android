package com.example.stsisko.helloworldgradle.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;

public class DataConnectionEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("PHONE STATUS CHANGED" );
        String action = intent.getAction();
        String extras = intent.getExtras().toString();
        System.out.println("ACTION: "+action+" ,EXTRAS: "+extras);
    }
}
