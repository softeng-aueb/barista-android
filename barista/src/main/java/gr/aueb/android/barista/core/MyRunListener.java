package gr.aueb.android.barista.core;


import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import java.lang.annotation.Annotation;
import gr.aueb.android.barista.core.annotations.SaySomething;
import gr.aueb.android.barista.core.http_client.BaristaHttpClient;


public class MyRunListener extends RunListener {

    private BaristaHttpClient client;

    // empty constructor
    public MyRunListener(){

        //https://developer.android.com/training/testing/junit-runner
        client = new BaristaHttpClient();
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

        logDescription(description);
    }

    private void logDescription(Description description){

        System.out.println("[BARISTA-LIB] Message from custom RunListener: "+description.getDisplayName());
        System.out.println("[BARISTA-LIB] Method Running: "+description.getMethodName());
        System.out.println("[BARISTA-LIB] Class Name: "+ description.getClassName());

        System.out.println("[BARISTA-LIB] ANNOTATIONS FOUND");
    }
}
