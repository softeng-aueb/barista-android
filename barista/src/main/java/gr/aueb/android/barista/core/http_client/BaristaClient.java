package gr.aueb.android.barista.core.http_client;

import java.util.List;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.CommandDTO;

/**
 *  Interface of the Barista Client.
 *
 *  A class that implements this interface should be able to utilize all the REST API provided by
 *  the Barista HTTP Server
 *
 */
public interface BaristaClient {
    /**
     *  Request to the /kill endpoint
     *
     *  Called when all the tests are finished
     */
    void killServer();

    /**
     *  Request to the /activate endpoint
     *
     *  Called when the tests are about to begin
     */
    void activate();

    /**
     *  Request to the /execute endpoint
     *
     *  Post a single command in order to be executed on the emulator.
     * @param cmd
     */
    void executeCommand(CommandDTO cmd);

    /**
     *  Request to the /executeAll endpoint
     *
     *  Posts a list of commands in orer to be executed on the emulator.
     *
     * @param cmd
     */
    void executeAllCommands(List<CommandDTO> cmd);

}
