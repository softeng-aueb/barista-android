package com.example.stsisko.helloworldgradle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestMainctivityLayout() {
        Espresso.onView(withText("Button")).check(matches(isClickable()));
        Espresso.onView(withText("OK")).check(matches(isClickable()));
        Espresso.onView(withHint("Enter your name")).check(matches(isDisplayed()));
        onView(withId(R.id.textView2)).check(matches(withText("")));
    }

    @Test
    public void TestUpperCaseTransformation(){
        String userInput = "Stelios";
        onView(withId(R.id.editText3)).perform(typeText(userInput));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textView2)).check(matches(withText("STELIOS")));
    }
}
