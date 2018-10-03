package gr.aueb.android.barista.framework;

import android.os.Bundle;
import android.test.InstrumentationTestRunner;
import android.util.Log;

import junit.framework.TestSuite;

import gr.aueb.android.barista.command.Command;
import gr.aueb.android.barista.command.CommandChooser;

/**
 * Created by Miltiadis Konsolakis on 17/11/15.
 */
public class BaristaInstrumentationTestRunner extends android.test.InstrumentationTestRunner {
    public static boolean wmEnabled;

    @Override
    public void onCreate(Bundle arguments) {
        Log.d("AUEB_DEBUG", "BaristaInstrumentationTestRunner<onCreate> called!");

        this.wmEnabled = isWmCommandEnabled();
        if(this.wmEnabled) {
            Log.d("AUEB_DEBUG", "Wm Enabled!");

            resetDeviceMetrics();
        } else {
            Log.d("AUEB_DEBUG", "Wm error :(");
        }

        if(isOriCommandEnabled()) { Log.d("AUEB_DEBUG", "Ori Enabled!"); }
        else { Log.d("AUEB_DEBUG", "Ori error :("); }

        super.onCreate(arguments);
    }

    @Override
    public void onDestroy() {
        Log.d("AUEB_DEBUG", "BaristaInstrumentationTestRunner<onDestroy> called!");

        if(this.wmEnabled) {
            Log.d("AUEB_DEBUG", "Wm Enabled!");

            resetDeviceMetrics();
        } else {
            Log.d("AUEB_DEBUG", "Wm error :(");
        }

        super.onDestroy();
    }

    @Override
    public TestSuite getAllTests() {
        Log.d("AUEB_DEBUG", "BaristaInstrumentationTestRunner<getAllTests> called!");

        return null;
    }

    @Override
    protected android.test.AndroidTestRunner getAndroidTestRunner() {
        Log.d("AUEB_DEBUG", "BaristaInstrumentationTestRunner<getAndroidTestRunner> called!");
        return new AndroidTestRunner();
    }

    /**
     * reset device density, size, orientation
     */
    private void resetDeviceMetrics() {
        Command command = CommandChooser.GetCommandInstance();

        if(command != null) {
            command.setContext(getContext());

            boolean res = command.setDensity(0);
            Log.d("AUEB_DEBUG", "Device density reset" + (res ? "" : " NOT") + " applied");

            res = command.setSize(0, 0);
            Log.d("AUEB_DEBUG", "Device size reset" + (res ? "" : " NOT") + " applied");

            res = command.setOrientation(Command.Orientation.RESET);
            Log.d("AUEB_DEBUG", "Device Orientation RESET" + (res ? "" : " NOT") + " applied");

            res = command.setOrientation(Command.Orientation.PORTRAIT);
            Log.d("AUEB_DEBUG", "Device Orientation PORTRAIT" + (res ? "" : " NOT") + " applied");
        }
    }

    /**
     *
     * @return true if permission ok
     */
    private boolean isWmCommandEnabled() {
        //return 0 if has permission -1 if not
        int wmEnabled = getContext().checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS");

        return wmEnabled == 0;
    }

    private boolean isOriCommandEnabled() {
        int wmEnabled = getContext().checkCallingOrSelfPermission("android.permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY");

        return wmEnabled == 0;
    }
}
