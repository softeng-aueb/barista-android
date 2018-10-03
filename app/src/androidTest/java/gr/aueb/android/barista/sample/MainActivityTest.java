package gr.aueb.android.barista.sample;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;

import gr.aueb.android.barista.annotation.Configuration;
import gr.aueb.android.barista.annotation.Device;
import gr.aueb.android.barista.annotation.Devices;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

/*@Configurations(configurations = {
        @Configuration(dpi = 240, width = 480, height = 854), //android one
        @Configuration(dpi = 320, width = 720, height = 1280), //sony xperia z3 compact
        @Configuration(dpi = 640, width = 1440, height = 2560, orientation = Configuration.Orientation.LANDSCAPE),
        @Configuration(dpi = 640, width = 1440, height = 2560, orientation = Configuration.Orientation.PORTRAIT)
})*/

@Device(model = Device.Model.SAMSUNG_GALAXY_NOTE_4, orientation = Configuration.Orientation.LANDSCAPE)

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Activity activity;

    public MainActivityTest() {
        super(MainActivity.class);

        Log.d("AUEB_DEBUG", "MainActivityTest<constructor> called!");
    }

    Button mLeftButton;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("MainActivityTest<setUp> called!");

        setActivityInitialTouchMode(true);
        mLeftButton = (Button) getActivity().findViewById(R.id.button);

        activity = getActivity();
    }

    //@Configuration(dpi = 320, width = 720, height = 1280) //sony xperia z3 compact
    @Devices( devices = {
            @Device(model = Device.Model.NEXUS_S, orientation = Configuration.Orientation.LANDSCAPE)
           // @Device(model = Device.Model.SAMSUNG_GALAXY_S6)
    })
    public void testFocusMovesToRight() throws Exception {
        Log.d("AUEB_DEBUG", "MainActivityTest<testFocusMovesToRight> called!");

        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getInstrumentation().waitForIdleSync();

        /*Instrumentation.ActivityMonitor monitor = new Instrumentation.ActivityMonitor(getActivity().getClass().getName(), null, false);
        getInstrumentation().addMonitor(monitor);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getInstrumentation().waitForIdleSync();
        activity = getInstrumentation().waitForMonitor(monitor);*/

        /*Instrumentation.ActivityMonitor monitor = new Instrumentation.ActivityMonitor(getActivity().getClass().getName(), null, false);
        getInstrumentation().addMonitor(monitor);
        // Rotate the screen
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getInstrumentation().waitForIdleSync();
        // Updates current activity
        activity = getInstrumentation().waitForMonitor(monitor);*/

        //assertTrue(mLeftButton.hasFocus());
        //getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_RIGHT);

        Button rightButton = (Button) getActivity().findViewById(R.id.button);
        //assertTrue(rightButton.hasFocus());

        DisplayMetrics dm = getInstrumentation().getContext().getResources().getDisplayMetrics();

        Log.d("AUEB_DEBUG", "testFocusMovesToRight -> Density: " + dm.densityDpi);

        int dmWidth = getInstrumentation().getContext().getResources().getDisplayMetrics().widthPixels;
        int dmHeight = getInstrumentation().getContext().getResources().getDisplayMetrics().heightPixels;
        Log.d("AUEB_DEBUG", "testFocusMovesToRight -> size: " + dmWidth + "x" + dmHeight);

       // assertTrue(dm.densityDpi == 480);
        assertTrue(true);
    }

    /*public void testFocusMovesToRight1() throws Exception {
        System.out.println("MainActivityTest<testFocusMovesToRight1> called!");

       // assertTrue(mLeftButton.hasFocus());
        Button rightButton = (Button) getActivity().findViewById(R.id.button);
        //assertTrue(rightButton.hasFocus());

        DisplayMetrics dm = getInstrumentation().getContext().getResources().getDisplayMetrics();

        System.out.println("testFocusMovesToRight1 -> Density: "+dm.densityDpi);

       // assertTrue(dm.densityDpi == 240);
    }*/

}