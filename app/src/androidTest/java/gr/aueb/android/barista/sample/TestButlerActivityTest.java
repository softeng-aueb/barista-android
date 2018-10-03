package gr.aueb.android.barista.sample;

import android.content.Context;
import android.location.LocationManager;
import android.support.test.espresso.Espresso;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.Surface;

import com.linkedin.android.testbutler.TestButler;

import org.apache.commons.lang3.reflect.MethodUtils;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by bzafiris on 11/12/2017.
 */

public class TestButlerActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {


    private MainActivity mainActivity;

    public TestButlerActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
    }


    public void notestLandscapeLayout(){

        TestButler.setRotation(Surface.ROTATION_90);
        Espresso.onView(withId(R.id.button)).perform(click());
        TestButler.setRotation(Surface.ROTATION_180);
        Espresso.onView(withId(R.id.button2)).check(matches(isDisplayed()));

    }

    public void testLocationManagerApi(){

        LocationManager locationManager = (LocationManager) getInstrumentation().getTargetContext().getSystemService(Context.LOCATION_SERVICE);
        assertNotNull(locationManager);


        Log.d("Reflect", locationManager.getClass().getCanonicalName());


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
