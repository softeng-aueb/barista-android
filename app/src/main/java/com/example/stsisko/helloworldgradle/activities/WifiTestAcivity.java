package com.example.stsisko.helloworldgradle.activities;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.stsisko.helloworldgradle.R;
import com.example.stsisko.helloworldgradle.receivers.BatteryEventReceiver;
import com.example.stsisko.helloworldgradle.receivers.WifiEventReceiver;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity that adapts its behavior to the the state of the wifi connection
 */
public class WifiTestAcivity extends AppCompatActivity {
    private static TextView wifiLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_test_acivity);
        wifiLabel =  ((TextView)findViewById(R.id.wifiLabel));

        IntentFilter iFilter = new IntentFilter();

        iFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        BroadcastReceiver wifiReceiver = new WifiEventReceiver();
        getApplicationContext().registerReceiver(wifiReceiver, iFilter);

        ConnectivityManager cm =  (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        if(isConnected){
            ((TextView)findViewById(R.id.wifiLabel)).setText("WIFI IS ENABLED AND CONNECTED");
        }

    }

    public static void wifiStatusChanged(boolean status){
        if(status){
            wifiLabel.setText("WIFI IS ENABLED AND CONNECTED");
            wifiLabel.setBackgroundColor(Color.GREEN);
        }
        else{
            wifiLabel.setText("WIFI IS DISABLED");
            wifiLabel.setBackgroundColor(Color.RED);
        }
    }


}
