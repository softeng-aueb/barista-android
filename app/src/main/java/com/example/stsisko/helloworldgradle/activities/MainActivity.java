package com.example.stsisko.helloworldgradle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.stsisko.helloworldgradle.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBatteryTest(View view){
        Intent batteryTestIntent = new Intent(this, BatteryTestActivity.class);
        startActivity(batteryTestIntent);
    }

    public void openGeolocationTest(View view){
        Intent geolocationestIntent = new Intent(this, GeolocationTestActivity.class);
        startActivity(geolocationestIntent);
    }
}
