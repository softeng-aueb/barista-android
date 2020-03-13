package com.example.stsisko.helloworldgradle;


import com.example.stsisko.helloworldgradle.activities.DataTestAcivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import gr.aueb.android.barista.core.annotations.Data;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
