package com.example.stsisko.helloworldgradle.helpers;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class CustomLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        System.out.println("NEW LAT: "+location.getLatitude());
        System.out.println("NEW LONGT: "+location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
