package gr.aueb.android.barista.core.http_client;

import java.util.List;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.CommandDTO;

public interface BaristaClient {

    void killServer();
    void executeCommand(CommandDTO cmd);
    void executeAllCommands(List<CommandDTO> cmd);

}
