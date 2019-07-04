package com.example.stsisko.helloworldgradle;

import android.Manifest;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.stsisko.helloworldgradle.activities.DataTestAcivity;
import com.example.stsisko.helloworldgradle.activities.MainActivity;
import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.Data;
import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.Permission;
import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DataConnectionTest {


    @Rule
    public ActivityTestRule<DataTestAcivity> activityActivityTestRule = new ActivityTestRule<DataTestAcivity>(DataTestAcivity.class);


    @Test
    @Data(NetworkAdapterStateType.DISABLED)
    public void testIfDataDisabled(){

        onView(withId(R.id.dataLabel)).check(matches(isDisplayed()));
        onView(withText("DATA IS DISABLED")).check(matches(isDisplayed()));

    }

    @Test
    @Data(NetworkAdapterStateType.ENABLED)
    public void testIfDataEnabled(){

        onView(withId(R.id.dataLabel)).check(matches(isDisplayed()));
        onView(withText("DATA IS ENABLED")).check(matches(isDisplayed()));

    }


    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

}
