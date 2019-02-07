package gr.aueb.android.barista.core;

import android.support.test.InstrumentationRegistry;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.http_client.BaristaClient;
import gr.aueb.android.barista.core.http_client.DefaultBaristaRetrofitClient;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.GeoFixDTO;
import gr.aueb.android.barista.core.model.WmSizeDTO;
import gr.aueb.android.barista.core.utilities.BaristaConfigurationReader;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;

public class BaristaRunListener extends RunListener {

    //todo not use anymore
    public static final int UID = new Random().nextInt(1000);

    private static final String BaseURL = "http://10.0.2.2";
    private BaristaClient httpClient;
    private String emulatorToken = DefaultBaristaConfigurationReader.getEmulatorSessionToken();


    public BaristaRunListener(){
        Timber.plant(new Timber.DebugTree());
        //fixme find port number with DefaultBaristaConfigurationReader method.
        // If use DefaultBaristaConfigurationReader No Instrumentation registry found error will occur.
        httpClient = new DefaultBaristaRetrofitClient(BaseURL,
                8070,
                JacksonConverterFactory.create());
    }



    /**
     *   Called when an atomic test is about to be started.
     *   todo think about smart reseting device properties after execution
     *         for example if only size attributes are affected , reset only screen size after execution
     *         Maybe cache the tests
     * @param description
     */
    public void testStarted(Description description){
        TestRunnerMonitor.testStarted();
        Timber.d("Starting test: "+description.getClassName()+":"+description.getMethodName());

        // TODO use BaristaAnnotationParser
        Annotation screenAnnotaion = description.getAnnotation(ScreenSize.class);
        if(screenAnnotaion != null ) {

            int height = ((ScreenSize) screenAnnotaion).height();
            int width = ((ScreenSize) screenAnnotaion).width();
            Timber.d("Resizing screen to: "+width+"x"+height);

            CommandDTO resizeCommand = new WmSizeDTO(emulatorToken,height,width,false,"DPI");
            ((WmSizeDTO) resizeCommand).setHeight(height);
            ((WmSizeDTO) resizeCommand).setWidth(width);
            httpClient.executeCommand(resizeCommand);
            //httpClient.resizeScreen(width,height);

        }
        else{
            Timber.d("No ScreenSize annotations provided");
        }

        Annotation geofixAnnotaion = description.getAnnotation(GeoFix.class);
        if(geofixAnnotaion != null ) {

            double latitude = ((GeoFix) geofixAnnotaion).lat();
            double longitude = ((GeoFix) geofixAnnotaion).longt();
            Timber.d("Set GPS coordinates to: lat:"+latitude+", long:"+longitude);

            CommandDTO geofixCommand = new GeoFixDTO(emulatorToken,latitude,longitude);

            httpClient.executeCommand(geofixCommand);


        }
        else{
            Timber.d("No ScreenSize annotations provided");
        }
    }

    /**
     * Called before any tests have been run.
     * @param description
     */
    public void testRunStarted(Description description){
        TestRunnerMonitor.testRunStarted();

    }

    private void logDescription(Description description){
        Timber.d("Message from custom RunListener: %s",description.getDisplayName());
        Timber.d("Method Running: %s", description.getMethodName());
        Timber.d("Class Name: %s",description.getClassName());
        Timber.d("ANNOTATIONS FOUND");
    }

    public void testRunFinished(Result result){
        TestRunnerMonitor.testRunFinished();
        //DefaultBaristaRetrofitClient httpClient = DefaultBaristaRetrofitClient.getHttpClient(BaseURL);
        httpClient.killServer();

    }

    public void testFinished(Description description) {
        TestRunnerMonitor.testFinished();
        Timber.d("Test "+description.getClassName()+":"+description.getMethodName()+" finished. Reseting Device");
        //DefaultBaristaRetrofitClient httpClient = DefaultBaristaRetrofitClient.getHttpClient(BaseURL);
        //todo reset device
        //httpClient.resetScreen();
    }


}

