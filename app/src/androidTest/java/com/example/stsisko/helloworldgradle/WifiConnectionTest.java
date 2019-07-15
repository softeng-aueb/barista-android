package com.example.stsisko.helloworldgradle;

import android.provider.Settings;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Surface;
import android.view.View;

import com.example.stsisko.helloworldgradle.activities.DataTestAcivity;
import com.example.stsisko.helloworldgradle.activities.WifiTestAcivity;
//import com.linkedin.android.testbutler.TestButler;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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



   // @Test
   // public void testIfWifiDisabled(){

     //  onView(withId(R.id.wifiLabel)).check(matches(isDisplayed()));
      // Barista.setWifiState(NetworkAdapterStateType.DISABLED);
      // onView(isRoot()).perform(waitFor(1000));
      // onView(withText("WIFI IS DISABLED")).check(matches(isDisplayed()));
      // Barista.setWifiState(NetworkAdapterStateType.ENABLED);
       //waitFor(5000);
       //onView(withText("WIFI IS ENABLED AND CONNECTED")).check(matches(isDisplayed()));
//        Barista.setWifiState(NetworkAdapterStateType.ENABLED);
//        onView(isRoot()).perform(waitFor(5000));
//        onView(withId(R.id.wifiLabel)).check(matches(withText("WIFI IS ENABLED AND CONNECTED")));


   // }

    @Test
    @Wifi(NetworkAdapterStateType.ENABLED)
    public void testIfWifiEnabled(){

        onView(withId(R.id.wifiLabel)).check(matches(isDisplayed()));
       // TestButler.setWifiState(true);
       onView(withText("WIFI IS ENABLED AND CONNECTED")).check(matches(isDisplayed()));

    }

    @Test
    @Wifi(NetworkAdapterStateType.DISABLED)
    public void testIfWifiDisabled(){

        onView(withId(R.id.wifiLabel)).check(matches(isDisplayed()));
        onView(withText("WIFI IS DISABLED")).check(matches(isDisplayed()));

    }


    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();

    }

    /**
     * Perform action of waiting for a specific time.
     */
    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }

        };
    }
}
