package gr.aueb.android.barista.core;

import timber.log.Timber;

/**
 *
 */
public class TestRunnerMonitor {

    private static int running_tests = 0 ;

    public static void testRunStarted(){
        running_tests++;
        Timber.d("Called testRunStarted()");

    }

    public static void testRunFinished(){
        running_tests--;
        Timber.d("Called testRunFinished()");
    }

    public static void testStarted(){
        Timber.d("Called testStarted()");
    }

    public static void testFinished(){
        Timber.d("Called testFinished()");
    }


}
