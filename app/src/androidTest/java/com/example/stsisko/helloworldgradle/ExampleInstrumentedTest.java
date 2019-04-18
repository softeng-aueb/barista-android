package com.example.stsisko.helloworldgradle;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.stsisko.helloworldgradle.activities.BatteryTestActivity;
import com.example.stsisko.helloworldgradle.activities.MainActivity;
import com.example.stsisko.helloworldgradle.sights.SightNames;
import com.example.stsisko.helloworldgradle.activities.BatteryTestActivity;
import com.example.stsisko.helloworldgradle.activities.MainActivity;
import com.example.stsisko.helloworldgradle.sights.SightNames;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.BatteryOptions;
import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.Permission;
import gr.aueb.android.barista.core.annotations.ScreenSize;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

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
        onView(withId(R.id.map)).check(matches(isDisplayed()));
        onView(withText(SightNames.SYNTAGMA)).check(matches(isDisplayed()));


    }

    @Test
    @GeoFix(lat = 37.971428, longt = 23.723716) // near acropolis
    @Permission(type = Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearAcropolis(){

        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).perform(ViewActions.click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
        onView(withText(SightNames.ACROPOLIS)).check(matches(isDisplayed()));
        onView(withText(SightNames.AGORA)).check(matches(isDisplayed()));
    }


    @Test
    @GeoFix(lat = 37.9670, longt = 23.728612) //near museum
    //@ScreenSize(width = 600, height = 500)
    @Permission(type = Manifest.permission.ACCESS_FINE_LOCATION)
    public void testNearMuseum(){

        onView(withId(R.id.gpsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gpsButton)).perform(ViewActions.click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
        onView(withText(SightNames.MUSEUM)).check(matches(isDisplayed()));
    }


    //@Test
    @BatteryOptions(level = 40, plugged = false)
    public void testBatteryReaction(){
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.batteryButton)).perform(ViewActions.click());
        ColorDrawable backgroundColor = (ColorDrawable) BatteryTestActivity.getInstance().findViewById(R.id.battery_layout).getBackground();
        assertThat(backgroundColor.getColor(),is(equalTo(Color.GRAY)));

    }

    @Test
    @BatteryOptions(level = 80, plugged = true)
    public void testBatteryReaction2(){
        onView(withId(R.id.batteryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.batteryButton)).perform(ViewActions.click());
        onView(withId(R.id.battery_layout)).check(matches(isDisplayed()));
        ColorDrawable backgroundColor = (ColorDrawable) BatteryTestActivity.getInstance().findViewById(R.id.battery_layout).getBackground();
        assertNotEquals(backgroundColor.getColor(),Color.GRAY);

    }


    @Test
    @ScreenSize(width=1500, height=2600)
    public void screenSizeTest(){

    }

//    @After
//    public void resetPermissions(){
//        String packageName = InstrumentationRegistry.getTargetContext().getApplicationContext().getPackageName();
//        String permissionName = Manifest.permission.ACCESS_FINE_LOCATION;
//        InstrumentationRegistry.getInstrumentation().getUiAutomation().revokeRuntimePermission(packageName, permissionName);
//    }

}
