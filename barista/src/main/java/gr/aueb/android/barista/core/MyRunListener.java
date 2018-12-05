package gr.aueb.android.barista.core;


import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import gr.aueb.android.barista.BuildConfig;
import gr.aueb.android.barista.core.annotations.SaySomething;
import gr.aueb.android.barista.core.http_client.BaristaHttpClient;
import timber.log.Timber;

import static android.util.Log.INFO;


public class MyRunListener extends RunListener {

    private BaristaHttpClient httpClient;

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
            httpClient.echoMessage(((SaySomething) a).param1());
            httpClient.echoMessage(((SaySomething) a).param2());
            httpClient.echoMessage(((SaySomething) a).param3());
            httpClient.echoMessage(((SaySomething) a).param4());
            httpClient.resizeScreen("500","600");

        }
        else{
            Timber.d("NULL ANNOTATION");
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
        httpClient = new BaristaHttpClient();

    }

    private void logDescription(Description description){
        Timber.d("Message from custom RunListener: %s",description.getDisplayName());
        Timber.d("Method Running: %s", description.getMethodName());
        Timber.d("Class Name: %s",description.getClassName());
        Timber.d("ANNOTATIONS FOUND");
    }

    public void testRunFinished(Result result) throws Exception {
       if(!result.wasSuccessful()){
          Timber.e("Tests Failed. Closing Barista server manualy");
          this.httpClient.killServer();
       }

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

