package com.example.stsisko.helloworldgradle;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.stsisko.helloworldgradle.activities.GeolocationTestActivity;
import com.example.stsisko.helloworldgradle.activities.MainActivity;
import com.example.stsisko.helloworldgradle.idles.IdleUtilities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.Density;
import gr.aueb.android.barista.core.annotations.Orientation;
import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;
import gr.aueb.android.barista.core.inline.Barista;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ScreenTests {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    public OrientationOptions rotationStates[] ;
    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
        rotationStates = new OrientationOptions[4];
        rotationStates[0] = OrientationOptions.ORIENTATION_0;
        rotationStates[1] = OrientationOptions.ORIENTATION_90;
        rotationStates[2] = OrientationOptions.ORIENTATION_180;
        rotationStates[3] = OrientationOptions.ORIENTATION_270;

    }

    @Test
    @Orientation(OrientationOptions.ORIENTATION_90)
    public void testMenuVisibilityOriented_90(){

        for(OrientationOptions opt : rotationStates) {
            Barista.setOrientation(opt);
            onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
            onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
            onView(withId(R.id.wifiButton)).check(matches(isDisplayed()));
            onView(withId(R.id.dataButton)).check(matches(isDisplayed()));
            onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));
            onView(isRoot()).perform(IdleUtilities.waitFor(5000));
        }

    }

    @Test
    @ScreenSize(width = 800, height = 1200)
    @Density(400)
    public void testMenuVisibilityRandomScreenSize(){

        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.wifiButton)).check(matches(isDisplayed()));
        onView(withId(R.id.dataButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));

    }



}
