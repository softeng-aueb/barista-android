package com.example.stsisko.helloworldgradle;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.SaySomething;
import gr.aueb.android.barista.core.http_client.BaristaHttpClient;

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
public class ExampleInstrumentedTest {

    private static BaristaHttpClient client;

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void initilizeHttpClient(){

        client = new BaristaHttpClient();

    }


    @Test
    //@SaySomething(param1="Annotation parsing Test!")
    public void TestMainctivityLayout() {
        System.out.println("TEST MAIN ACTIVITY");
        Espresso.onView(withText("Button")).check(matches(isClickable()));
        Espresso.onView(withText("OK")).check(matches(isClickable()));
        Espresso.onView(withHint("Enter your name")).check(matches(isDisplayed()));
        onView(withId(R.id.textView2)).check(matches(withText("")));
//        AnottationParser p = new AnottationParser();
//        p.printValue(this);
    }

    @Test
    @SaySomething(param1="Annotation parsing Test!")
    public void TestUpperCaseTransformation(){
        System.out.println("TEST UPPER CASE");
        String userInput = "Stelios";
        onView(withId(R.id.editText3)).perform(typeText(userInput));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textView2)).check(matches(withText("STELIOS")));
    }

    @Test
    public void testRestClient(){


        System.out.println("@@@@ CALLING SERVICE");
        String message = client.getStatus();
      // assertNotNull(message);
        assertEquals("Hello World from Jersey Servlet Container",message);
        System.out.println("REST RESPONSE: "+ message);

    }

   // @Test
    public void testRestClient2(){

        System.out.println("@@@@ CALLING SERVICE");
        String message = client.getStatus2();
        //assertNotNull(message);
        assertEquals("Hello World from Jersey Servlet Container 12",message);
        System.out.println("REST RESPONSE: "+ message);

    }

   // @AfterClass
    public static void killServer(){
       client.killServer();
    }
}
