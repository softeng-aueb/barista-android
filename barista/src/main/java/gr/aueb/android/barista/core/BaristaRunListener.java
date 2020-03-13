package gr.aueb.android.barista.core;


import android.content.Context;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;
import gr.aueb.android.barista.core.annotations.BaristaAnnotationParser;
import gr.aueb.android.barista.core.http_client.BaristaClient;
import gr.aueb.android.barista.core.http_client.HTTPClientManager;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import timber.log.Timber;

/**
 *  Barista Run Listener is hooked to the testing execution cycle by the Android Test Runner.
 *
 *  AndroidTestRunner calls BaristaRunListener each time the tests are starting, a test case is starting,
 *  a testcase is finished and finally when all tests are finished.
 *
 */
public class BaristaRunListener extends RunListener {

    /**
     * A barista http client implementation
     */
    private BaristaClient httpClient;

    /**
     * The sessionToken to be encapsulated in each request
     */
    private String sessionToken = null;

    /**
     *  A List containing the last executed commands in oder to know at second time, which emulator states to reset.
     */
    private List<CommandDTO> lastExecutedCommands;

    /**
     * Empty constructor
     */
    public BaristaRunListener(){
        Timber.plant(new Timber.DebugTree());
        lastExecutedCommands = null;
    }

    /**
     * Called before any tests have been run.
     *
     * @param description A description of the test class
     */
    public void testRunStarted(Description description){

        //debug only
        TestRunnerMonitor.testRunStarted();

        String host = (String) getBuildConfigValue(InstrumentationRegistry.getInstrumentation().getContext(), "baristaEndpoint");

        if (host == null){
            throw new IllegalArgumentException("BuildConfig does not include baristaEndpoint property");
        }

        HTTPClientManager.initialize(host);
        httpClient = HTTPClientManager.getInstance();
        // request to gain read permissions
        httpClient.activate();

        //w8 some ms for the activation to be applied
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // load the session token provided by the barista plugin
        sessionToken = DefaultBaristaConfigurationReader.getEmulatorSessionToken();
    }

    /**
     *  Called when an atomic test is about to be started.
     *
     *  Android Test Runner calls this function whenever a test method is about to start. The Android runner provides
     *  the function with a Description object that includes various metadata about the test method to be executed, including its
     *  annotations. Those annotations are parsed by the Annotatation parer and tranformed into commands. Those commands are send to the
     *  Barista HTTP Server for execution.
     *
     * @param description The metadata of a test method
     */
    public void testStarted(Description description){
        TestRunnerMonitor.testStarted();
        Timber.d("Starting test: "+description.getClassName()+":"+description.getMethodName());

        // pass the metadata of the test case to the the Barista annotation parser
        List<CommandDTO> currentCommands = BaristaAnnotationParser.getParsedCommands(description);

        if(currentCommands.size() != 0){
            // assign to the commands the sessionToken
            setSessionTokenToCommands(currentCommands);
            Timber.d("Total commands to execute: "+currentCommands.size());

            // Send request to Barista Server
            httpClient.executeAllCommands(currentCommands);

            // store the commands temporary to a list in order to know how to reset
            // the emulator later
            this.lastExecutedCommands = currentCommands;
        }


    }


    /**
     *  Executed every time a Test case finishes.
     *
     *  Method that runs at the end of every test method and resets the emulator to its state before the begin of the test method.
     *  It uses a temporary list which contains the last executed methods in order to derive from them the reverse commands.
     *
     *
     * @param description
     */
    public void testFinished(Description description) {
        TestRunnerMonitor.testFinished();
        Timber.d("Test "+description.getClassName()+":"+description.getMethodName()+" finished. Reseting Device");
        if(lastExecutedCommands != null ) {
            List<CommandDTO> reverseCommands = new ArrayList<>();
            for(CommandDTO command: lastExecutedCommands){
                if (command.getResetCommand() != null){
                    reverseCommands.add(command.getResetCommand());
                }
            }

            httpClient.executeAllCommands(reverseCommands);
        }
    }

    /**
     * When all tests finish, send a termination signal to the server. This doesn't mean the server will close.
     * @param result
     */
    public void testRunFinished(Result result){
        TestRunnerMonitor.testRunFinished();
        httpClient.killServer();
    }


    /**
     * Helping function that sets the current sessionsToken to a list of commands. It also sets the session token to the reverse
     * commands
     *
     * @param commands  A Collection of CommandDTO objects
     * @return  A reference to the refactored list. The result is not a new List but the same list
     *
     */
    private Collection<CommandDTO> setSessionTokenToCommands(Collection<CommandDTO> commands){
        for(CommandDTO command: commands){
            command.setSessionToken(sessionToken);
            if(command.getResetCommand() != null ) {
                command.getResetCommand().setSessionToken(sessionToken);
            }
        }
        return commands;
    }


    /**
     *
     * https://stackoverflow.com/questions/21365928/gradle-how-to-use-buildconfig-in-an-android-library-with-a-flag-that-gets-set
     *
     * Gets a field from the project's BuildConfig. This is useful when, for example, flavors
     * are used at the project level to set custom fields.
     * @param context       Used to find the correct file
     * @param fieldName     The name of the field-to-access
     * @return              The value of the field, or {@code null} if the field is not found.
     */
    public static Object getBuildConfigValue(Context context, String fieldName) {
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}

