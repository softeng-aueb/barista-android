package gr.aueb.android.barista.core;

import android.app.Instrumentation;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.core.annotations.SaySomething;
import gr.aueb.android.barista.core.http_client.BaristaHttpClient;
//import android.support.test.InstrumentationRegistry;

public class MyRunListener extends RunListener {
    private BaristaHttpClient client;
    // empty constructor
    public MyRunListener(){
        //https://developer.android.com/training/testing/junit-runner
        //InstrumentationRegistry.
        client = new BaristaHttpClient();

    }

    /**
     *   Called when an atomic test is about to be started.
     * @param description
     */
    public void testStarted(Description description){
        logDescription(description);
        System.out.println("[BARISTA-LIB]: REST RESP 1 -> "+client.getStatus());
        System.out.println("[BARISTA-LIB]: REST RESP 2 -> "+client.getStatus());
    }

    /**
     * Called before any tests have been run.
     * @param description
     */
    public void testRunStarted(Description description){

        logDescription(description);
    }

    private void logDescription(Description description){

        System.out.println("[BARISTA-LIB]: Message from custom RunListener. "+description.getDisplayName());
        System.out.println("[BARISTA-LIB]: METHOD RUNNING: "+description.getMethodName());
        System.out.println("[BARISTA-LIB]: CLASS NAME: "+ description.getClassName());
        System.out.println("[BARISTA-LIB]: ANNOTATIONS FOUND");

       Annotation a = description.getAnnotation(SaySomething.class);
        if(a != null ) {
            System.out.println("[BARISTA-LIB]:"+((SaySomething) a).param1());
            System.out.println("[BARISTA-LIB]:"+((SaySomething) a).param2());
            System.out.println("[BARISTA-LIB]:"+((SaySomething) a).param3());
            System.out.println("[BARISTA-LIB]:"+((SaySomething) a).param4());
        }
        else{
            System.out.println("[BARISTA-LIB]: NULL ANOTATION");
        }
    }
}
