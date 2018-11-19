package gr.aueb.android.barista.core;


import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import gr.aueb.android.barista.BuildConfig;
import gr.aueb.android.barista.core.annotations.SaySomething;
import gr.aueb.android.barista.core.http_client.BaristaHttpClient;
import timber.log.Timber;

import static android.util.Log.INFO;


public class MyRunListener extends RunListener {

    private BaristaHttpClient client;

    // empty constructor
    public MyRunListener(){

        //https://developer.android.com/training/testing/junit-runner



    }

    /**
     *   Called when an atomic test is about to be started.
     * @param description
     */
    public void testStarted(Description description){
        logDescription(description);

        Annotation a = description.getAnnotation(SaySomething.class);
        if(a != null ) {
            client.echoMessage(((SaySomething) a).param1());
            client.echoMessage(((SaySomething) a).param2());
            client.echoMessage(((SaySomething) a).param3());
            client.echoMessage(((SaySomething) a).param4());

        }
        else{
            System.out.println("[BARISTA-LIB] NULL ANNOTATION");
        }
    }

    /**
     * Called before any tests have been run.
     * @param description
     */
    public void testRunStarted(Description description){

        //if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        /*} else {
            Timber.plant(new CrashReportingTree());
        }*/

        String packageName = InstrumentationRegistry.getTargetContext().getPackageName();
        String buildConfigClass = packageName + ".BuildConfig";
        try {
            // TODO: get emulator port and ip address through reflection on BuildConfig
            Class clazz = Class.forName(buildConfigClass);
            Field portField = clazz.getField("BARISTA_PORT");
            Timber.d("Found field %s", portField);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Timber.d("Found package %s", packageName);
        logDescription(description);

        // TODO: configure client with port, ipaddress
        client = new BaristaHttpClient();

    }

    private void logDescription(Description description){

        System.out.println("[BARISTA-LIB] Message from custom RunListener: "+description.getDisplayName());
        System.out.println("[BARISTA-LIB] Method Running: "+description.getMethodName());
        System.out.println("[BARISTA-LIB] Class Name: "+ description.getClassName());

        System.out.println("[BARISTA-LIB] ANNOTATIONS FOUND");
    }


    /** A tree which logs important information for crash reporting. */
/*    private static final class CrashReportingTree extends Timber.Tree {
        @Override
        public boolean isLoggable(int priority, @Nullable String tag) {
            return priority >= INFO;
        }

        @Override
        protected void log(int priority, String tag, Throwable t, String message) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }*/

}

