package com.example.stsisko.helloworldgradle.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.stsisko.helloworldgradle.R;
import com.example.stsisko.helloworldgradle.helpers.IntegerCounter;
import com.example.stsisko.helloworldgradle.helpers.ThreadSleepingTime;
import com.example.stsisko.helloworldgradle.receivers.BatteryEventReceiver;

public class BatteryTestActivity extends Activity {

    final Handler handler = new Handler();
    private Runnable r;
    private static BatteryTestActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        INSTANCE = this;
        setContentView(R.layout.battery_test);
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        BroadcastReceiver batteryReceiver = new BatteryEventReceiver();
        getApplicationContext().registerReceiver(batteryReceiver, iFilter);

    }

    public void goButtonClickAction(View v){
         ToggleButton button =  v.findViewById(R.id.go_battery_toggle);
         if(button.isChecked()){
             r = new Runnable() {

                 public void run() {
                     System.out.println("HELLO ");
                     handler.postDelayed(this, ThreadSleepingTime.proccessSpeed);

                     TextView label = findViewById(R.id.number_label);
                     label.setText(new Integer(IntegerCounter.getNextTick()).toString());
                 }
             };

             handler.postDelayed(r,0000);

         }
         else{
             handler.removeCallbacks(r);
             IntegerCounter.resetCounter();
             TextView label = findViewById(R.id.number_label);
             label.setText(new Integer(IntegerCounter.getNextTick()).toString());
         }

    }

    public static BatteryTestActivity getInstance(){
        return INSTANCE;
    }

}
