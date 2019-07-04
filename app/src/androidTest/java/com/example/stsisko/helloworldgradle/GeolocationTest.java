package com.example.stsisko.helloworldgradle;

import android.Manifest;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.stsisko.helloworldgradle.activities.GeolocationTestActivity;
import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;
import com.example.stsisko.helloworldgradle.sights.SightNames;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.Density;
import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.Permission;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

//@RunWith(AndroidJUnit4.class)
public class GeolocationTest {

    @Rule
    public ActivityTestRule<GeolocationTestActivity> activityActivityTestRule = new ActivityTestRule<GeolocationTestActivity>(GeolocationTestActivity.class);

   // @Test
    @GeoFix(lat = 37.975391, longt =23.735524) // near syntagma
    @Permission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearSyntagma(){

        onView(withId(R.id.map)).check(matches(isDisplayed()));

        onView(withText(SightNames.SYNTAGMA)).check(matches(isDisplayed()));

    }

    //@Test
    @GeoFix(lat = 37.971428, longt = 23.723716) // near acropolis
    @Permission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearAcropolis(){

        onView(withId(R.id.map)).check(matches(isDisplayed()));
        onView(withText(SightNames.ACROPOLIS)).check(matches(isDisplayed()));
        onView(withText(SightNames.AGORA)).check(matches(isDisplayed()));

    }

    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }
}
