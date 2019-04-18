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
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.WmSizeResetDTO;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;

public class BaristaRunListener extends RunListener {


    // The ip of the host machine (where the Barista server Runs)
    private static final String BASE_URL = "http://10.0.2.2";

    private BaristaClient httpClient;
    private String sessionToken = null;
    private List<CommandDTO> lastExecutedCommands;

    public BaristaRunListener(){
        Timber.plant(new Timber.DebugTree());
        lastExecutedCommands = null;
    }

    /**
     * Called before any tests have been run.
     * @param description
     */
    public void testRunStarted(Description description){

        //debug only
        TestRunnerMonitor.testRunStarted();


        //initialize the http client.
        httpClient = new DefaultBaristaRetrofitClient(BASE_URL,
                DefaultBaristaConfigurationReader.getBaristaServerPort(),
                JacksonConverterFactory.create());

        // request to gain read permissions
        httpClient.activate();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // load the session token provided by the barista plugin
        sessionToken = DefaultBaristaConfigurationReader.getEmulatorSessionToken();
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

        List<CommandDTO> currentCommands = BaristaAnnotationParser.getParsedCommands(description);
        setSessionTokenToCommands(currentCommands);
        Timber.d("Total commands to execute: "+currentCommands.size());

        httpClient.executeAllCommands(currentCommands);

        this.lastExecutedCommands = currentCommands;

    }


    /**
     * Executed every time a est case finishes
     * @param description
     */
    public void testFinished(Description description) {
        TestRunnerMonitor.testFinished();
        Timber.d("Test "+description.getClassName()+":"+description.getMethodName()+" finished. Reseting Device");
        List<CommandDTO> reverseCommands = this.lastExecutedCommands.stream()
                 .filter(command -> Objects.nonNull(command.getResetCommand()))
                 .map(command -> command.getResetCommand())
                 .collect(Collectors.toList());
        httpClient.executeAllCommands(reverseCommands);
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

