package com.example.stsisko.helloworldgradle.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.example.stsisko.helloworldgradle.R;
import com.example.stsisko.helloworldgradle.receivers.DataConnectionEventReceiver;
import com.example.stsisko.helloworldgradle.receivers.WifiEventReceiver;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity that adapts its behavior to the the state of the mobile data connection
 *
 */
public class DataTestAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_test_acivity);

        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(TelephonyManager.ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED);
        BroadcastReceiver dataReceiver = new DataConnectionEventReceiver();
        getApplicationContext().registerReceiver(dataReceiver, iFilter);

        boolean dataIsEnabled = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dataIsEnabled =  tm.isDataEnabled();

        } else {
            dataIsEnabled =  tm.getSimState() == TelephonyManager.SIM_STATE_READY && tm.getDataState() != TelephonyManager.DATA_DISCONNECTED;
        }

        if(dataIsEnabled){
            ((TextView)findViewById(R.id.dataLabel)).setText("DATA IS ENABLED");
            ((TextView)findViewById(R.id.dataLabel)).setBackgroundColor(Color.GREEN);
        }
        else{
            ((TextView)findViewById(R.id.dataLabel)).setText("DATA IS DISABLED");
            ((TextView)findViewById(R.id.dataLabel)).setBackgroundColor(Color.RED);
        }
    }
}
