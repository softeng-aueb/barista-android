package com.example.stsisko.helloworldgradle;

import android.Manifest;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.BatteryOptions;
import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.ScreenSize;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest3 {

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    @ScreenSize(width = 1800,height = 2600)
    public void testResize1() {

    }

    @Test
    @ScreenSize(width = 1500,height = 1600)
    public void testResize2(){

    }

    @Test
    @GeoFix(lat = 62,longt = 51)
    public void testGeoFix(){


    }

    @Test
    @BatteryOptions(level =40, plugged = false)
    public void testBattery(){

    }

//    @Test
//    @BatteryOptions(level = 100, plugged = true)
//    public void testBattery2(){
//
//    }



}
