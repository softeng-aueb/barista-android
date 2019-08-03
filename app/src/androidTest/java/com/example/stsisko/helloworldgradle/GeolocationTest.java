package com.example.stsisko.helloworldgradle;

import android.Manifest;
import android.provider.Settings;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.stsisko.helloworldgradle.activities.GeolocationTestActivity;
import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;
import com.example.stsisko.helloworldgradle.idles.IdleUtilities;
import com.example.stsisko.helloworldgradle.sights.SightNames;


import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;

import gr.aueb.android.barista.core.annotations.Density;
import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.Permission;
import gr.aueb.android.barista.core.inline.Barista;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import gr.aueb.android.barista.core.utilities.KMLParser;
import gr.aueb.android.barista.core.utilities.helper_classes.Coordinate;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class GeolocationTest {

    @Rule
    public ActivityTestRule<GeolocationTestActivity> activityActivityTestRule = new ActivityTestRule<GeolocationTestActivity>(GeolocationTestActivity.class);

    @Ignore
    @Test
    @GeoFix(lat = 37.975391, longt =23.735524) // near syntagma
    @Permission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearSyntagma(){

        onView(withId(R.id.map)).check(matches(isDisplayed()));
        onView(isRoot()).perform(IdleUtilities.waitFor(2000));
        onView(withText(SightNames.SYNTAGMA)).check(matches(isDisplayed()));

    }

    @Ignore
    @Test
    @GeoFix(lat = 37.971428, longt = 23.723716) // near acropolis
    @Permission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearAcropolis() {

        onView(withId(R.id.map)).check(matches(isDisplayed()));
        onView(isRoot()).perform(IdleUtilities.waitFor(2000)); // wait 2 seconds for animations to finish
        onView(withText(SightNames.ACROPOLIS)).check(matches(isDisplayed()));
        onView(withText(SightNames.AGORA)).check(matches(isDisplayed()));

    }


    @Test
    @Permission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void followPath(){
        File f = DefaultBaristaConfigurationReader.getPathFile();
        KMLParser kmlParser = new KMLParser(f.getPath());
        ArrayList<Coordinate> pointList = kmlParser.parseFile();
        for(Coordinate coord : pointList){
            Barista.setGeolocation(coord.getLongtitude(), coord.getLattitutde());
            onView(isRoot()).perform(IdleUtilities.waitFor(200));
            // testing code logic
        }
    }

    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }




}
