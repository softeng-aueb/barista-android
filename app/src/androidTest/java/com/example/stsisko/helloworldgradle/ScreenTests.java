package com.example.stsisko.helloworldgradle;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.stsisko.helloworldgradle.activities.GeolocationTestActivity;
import com.example.stsisko.helloworldgradle.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.Orientation;
import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ScreenTests {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    @Orientation(OrientationOptions.ORIENTATION_90)
    public void testMenuVisibilityOriented_90(){
        // InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("wm size 500x2500");
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.wifiButton)).check(matches(isDisplayed()));
        onView(withId(R.id.dataButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));

    }

    @Test
    @ScreenSize(width = 1200, height = 800)
    public void testMenuVisibilityRandomScreenSize(){
        // InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("wm size 500x2500");
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.wifiButton)).check(matches(isDisplayed()));
        onView(withId(R.id.dataButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));

    }

    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }
}
