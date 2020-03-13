package com.example.stsisko.helloworldgradle;


import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import gr.aueb.android.barista.core.annotations.Wifi;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


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
