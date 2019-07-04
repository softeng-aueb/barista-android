package com.example.stsisko.helloworldgradle;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.stsisko.helloworldgradle.activities.DataTestAcivity;
import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.Data;
import gr.aueb.android.barista.core.annotations.Wifi;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WifiConnectionTest {


    @Rule
    public ActivityTestRule<WifiTestAcivity> activityActivityTestRule = new ActivityTestRule<WifiTestAcivity>(WifiTestAcivity.class);


    @Test
    @Wifi(NetworkAdapterStateType.DISABLED)
    public void testIfWifiDisabled(){

       onView(withId(R.id.wifiLabel)).check(matches(isDisplayed()));
       onView(withText("WIFI IS DISABLED")).check(matches(isDisplayed()));

    }

    @Test
    @Wifi(NetworkAdapterStateType.ENABLED)
    public void testIfWifiEnabled(){

        onView(withId(R.id.wifiLabel)).check(matches(isDisplayed()));

       onView(withText("WIFI IS ENABLED AND CONNECTED")).check(matches(isDisplayed()));

    }


    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

}
