package gr.aueb.android.barista.framework;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import gr.aueb.android.barista.annotation.Configuration;
import gr.aueb.android.barista.helper.Compatibility;
import gr.aueb.android.barista.helper.ConfigurationHelper;
import gr.aueb.android.barista.helper.Functions;


/**
 * Created by Miltiadis Konsolakis on 9/2/16.
 */
public class ActivityInstrumentationTestCase<T extends Activity> extends ActivityInstrumentationTestCase2 {
    private Activity activity;
    //private boolean layoutReady;

    public ActivityInstrumentationTestCase(Class activityClass) {
        super(activityClass);
        //this.layoutReady = false;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.activity = super.getActivity();

        if(AndroidTestRunner.currentConfiguration != null) {
            int goToOrientation = AndroidTestRunner.currentConfiguration.orientation() == Configuration.Orientation.PORTRAIT ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            rotateTo(goToOrientation);
        } else { Log.d("AUEB_DEBUG", "AndroidTestRunner, currentConfiguration NULL."); }

        try { this.activity.getWindow().getDecorView().findViewById(android.R.id.content).invalidate(); } catch (Exception e) { }

        // Save screen shot before run tests
        Functions.saveScreenShot(activity, ConfigurationHelper.configToShortString(AndroidTestRunner.currentConfiguration));
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();
    }

    protected final void rotateTo(int nextOrientation) {
        if (Compatibility.currentOrientation(activity) != nextOrientation) {
            Log.d("AUEB_DEBUG", "ChangeToOrientation: " + (nextOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? "Portrait" : "Landscape"));

            Instrumentation.ActivityMonitor monitor = new Instrumentation.ActivityMonitor(activity.getClass().getName(), null, false);
            getInstrumentation().addMonitor(monitor);

            this.activity.setRequestedOrientation(nextOrientation);
            getInstrumentation().waitForIdleSync();

            this.activity = getInstrumentation().waitForMonitor(monitor);

            try { Thread.sleep(50); } catch (InterruptedException e) { }
        }
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    protected void tearDown() throws Exception {
        // Restore Default Orientation First then call super to clear activity

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getInstrumentation().waitForIdleSync();

        super.tearDown();
    }
}
