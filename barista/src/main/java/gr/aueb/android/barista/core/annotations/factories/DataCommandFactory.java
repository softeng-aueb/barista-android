package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Data;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterUtilities;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.SvcDataDTO;

/**
 *  Command Factory that parses Data type annotations and creates
 *  equivalent DataDTO commands.
 *
 */
public class DataCommandFactory implements CommandFactory {

    private final NetworkAdapterStateType DEFAULT_STATE = NetworkAdapterStateType.ENABLED;

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        CommandDTO dataCommand = null;
        NetworkAdapterStateType selectedState = ((Data)a).value();
        dataCommand = new SvcDataDTO(null, NetworkAdapterUtilities.NETWORK_STATES.get(selectedState));

        //construct reverse command
        if(selectedState != DEFAULT_STATE) {
            CommandDTO resetCommand = new SvcDataDTO(null, NetworkAdapterUtilities.NETWORK_STATES.get(DEFAULT_STATE));
            dataCommand.setResetCommand(resetCommand);
        }
        return Arrays.asList(dataCommand);
    }
}
