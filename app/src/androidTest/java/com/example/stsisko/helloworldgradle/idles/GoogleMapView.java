package com.example.stsisko.helloworldgradle.idles;


import androidx.test.espresso.IdlingResource;

public class GoogleMapView implements IdlingResource {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isIdleNow() {
        return false;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {

    }
}
