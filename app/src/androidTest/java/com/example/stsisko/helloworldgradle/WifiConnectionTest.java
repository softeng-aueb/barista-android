package com.example.stsisko.helloworldgradle;

import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Surface;
import android.view.View;

import com.example.stsisko.helloworldgradle.activities.DataTestAcivity;
import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;
import com.example.stsisko.helloworldgradle.idles.IdleUtilities;
import com.linkedin.android.testbutler.ButlerApi;
import com.linkedin.android.testbutler.TestButler;
//import com.linkedin.android.testbutler.TestButler;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import gr.aueb.android.barista.core.annotations.Data;
import gr.aueb.android.barista.core.annotations.Wifi;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.inline.Barista;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WifiConnectionTest {


    @Rule
    public ActivityTestRule<WifiTestAcivity> activityActivityTestRule = new ActivityTestRule<WifiTestAcivity>(WifiTestAcivity.class);


    @Ignore
    @Test
    @Wifi(NetworkAdapterStateType.DISABLED)
    public void testIfWifiDisabled(){

        onView(withId(R.id.wifiLabel)).check(matches(isDisplayed()));
        onView(withText("WIFI IS DISABLED")).check(matches(isDisplayed()));

    }



    @Test
    @Wifi(NetworkAdapterStateType.ENABLED)
    public void testIfWifiEnabled2(){

        onView(withId(R.id.wifiLabel)).check(matches(isDisplayed()));
        onView(withText("WIFI IS ENABLED AND CONNECTED")).check(matches(isDisplayed()));

    }


    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();

    }

}
