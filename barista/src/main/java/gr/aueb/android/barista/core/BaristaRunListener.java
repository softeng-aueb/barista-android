package gr.aueb.android.barista.core;


import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import gr.aueb.android.barista.core.annotations.BaristaAnnotationParser;
import gr.aueb.android.barista.core.http_client.BaristaClient;
import gr.aueb.android.barista.core.http_client.DefaultBaristaRetrofitClient;
import gr.aueb.android.barista.core.http_client.HTTPClientManager;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.WmSizeResetDTO;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import retrofit2.converter.jackson.JacksonConverterFactory;
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
     *  The Barsita Server IP. By default ip 10.0.2.2. is used as the default host machine IP
     */
    private static final String BASE_URL = "http://10.0.2.2";

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

        HTTPClientManager.initialize();
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
            List<CommandDTO> reverseCommands = this.lastExecutedCommands.stream()
                    .filter(command -> Objects.nonNull(command.getResetCommand()))
                    .map(command -> command.getResetCommand())
                    .collect(Collectors.toList());
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
        commands.forEach(command->{
            command.setSessionToken(sessionToken);
            if(command.getResetCommand() != null ){
                command.getResetCommand().setSessionToken(sessionToken);
            }
        });

        return commands;
    }




}

