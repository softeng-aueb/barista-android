package com.example.baristademoapp;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.example.baristademoapp.activities.BatteryTestActivity;
import com.example.baristademoapp.activities.MainActivity;
import com.example.baristademoapp.sights.SightNames;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.BatteryOptions;
import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.Permission;
import gr.aueb.android.barista.core.annotations.ScreenSize;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {



    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void testMenuVisibility(){
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.wifiButton)).check(matches(isDisplayed()));
        onView(withId(R.id.dataButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));
    }

    @Test
    @GeoFix(lat = 37.975391, longt =23.735524) // near syntagma
    @Permission(type = Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearSyntagma(){

        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).perform(ViewActions.click());

        onView(withText(SightNames.SYNTAGMA)).check(matches(isDisplayed()));


    }

    @Test
    @GeoFix(lat = 37.971428, longt = 23.723716) // near acropolis
    @Permission(type = Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearAcropolis(){

        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).perform(ViewActions.click());
        onView(withText(SightNames.ACROPOLIS)).check(matches(isDisplayed()));
        onView(withText(SightNames.AGORA)).check(matches(isDisplayed()));
    }


    @Test
    @GeoFix(lat = 37.9670, longt = 23.728612) //near museum
    @Permission(type = Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearMuseum(){
        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).perform(ViewActions.click());
        onView(withText(SightNames.MUSEUM)).check(matches(isDisplayed()));

    }


    @Test
    @BatteryOptions(level = 40, plugged = false)
    public void testBatteryReaction(){
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.batteryButton)).perform(ViewActions.click());
        ColorDrawable backgroundColor = (ColorDrawable) BatteryTestActivity.getInstance().findViewById(R.id.battery_layout).getBackground();
        assertThat(backgroundColor.getColor(),is(equalTo(Color.GRAY)));

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.baristademoapp", appContext.getPackageName());
    }

    @Test
    @ScreenSize(width=1500, height=2600)
    public void screenSizeTest(){

    }


}
