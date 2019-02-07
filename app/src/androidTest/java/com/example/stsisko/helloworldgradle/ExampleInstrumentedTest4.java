package com.example.stsisko.helloworldgradle;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.ScreenSize;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest4 {


    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    @ScreenSize(width = 1800,height = 2600)
    @GeoFix(lat = 62,longt = 51)
    public void testResize1() {

    }



}
