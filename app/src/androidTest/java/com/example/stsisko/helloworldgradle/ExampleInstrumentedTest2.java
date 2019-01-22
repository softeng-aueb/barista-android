package com.example.stsisko.helloworldgradle;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.SaySomething;
import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.http_client.BaristaHttpClient;
import gr.aueb.android.barista.core.http_client.dto.SizeDto;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
//@RunWith(MyTestRunner.class)
@LargeTest
public class ExampleInstrumentedTest2 {



    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    @ScreenSize(width = "800",height = "600")
    public void TestMainctivityLayout() {
        System.out.println("TEST MAIN ACTIVITY");
        Espresso.onView(withText("Button")).check(matches(isClickable()));
        Espresso.onView(withText("OK")).check(matches(isClickable()));
        Espresso.onView(withHint("Enter your name")).check(matches(isDisplayed()));
        onView(withId(R.id.textView2)).check(matches(withText("")));


    }



    @Test
    @ScreenSize(width = "1500",height = "1600")
    public void TestUpperCaseTransformation(){
        System.out.println("TEST UPPER CASE");
        String userInput = "Stelios";
        onView(withId(R.id.editText3)).perform(typeText(userInput));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textView2)).check(matches(withText("STELIOS")));

    }

    @Test
    public void testRestClient(){

        BaristaHttpClient client = BaristaHttpClient.getInstance();
        System.out.println("@@@@ CALLING SERVICE");
        String message = client.getStatus();
        assertNotNull(message);
        assertEquals("Hello World from Jersey Servlet Container",message);
        System.out.println("REST RESPONSE: "+ message);
    }

    @Test
    @ScreenSize(width = "800",height = "600")
    public void testFindSize(){

        BaristaHttpClient client = BaristaHttpClient.getInstance();

        SizeDto size = client.getActuallSize();
        assertNotNull(size);
        assertEquals(800,size.getWidth());
        assertEquals(600,size.getHeight());

    }


}
