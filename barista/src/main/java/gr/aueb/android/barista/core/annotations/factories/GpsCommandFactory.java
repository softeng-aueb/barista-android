package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Gps;
import gr.aueb.android.barista.core.annotations.Wifi;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterUtilities;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.GpsStatusDTO;
import gr.aueb.android.barista.core.model.SvcWifiDTO;

public class GpsCommandFactory implements CommandFactory {

    private final NetworkAdapterStateType DEFAULT_STATE = NetworkAdapterStateType.ENABLED;

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        CommandDTO gpsCommand = null;

        NetworkAdapterStateType selectedState = ((Gps)a).value();
        Boolean state = NetworkAdapterUtilities.NETWORK_STATES.get(selectedState) ;
        gpsCommand = new GpsStatusDTO(null,state);

        // construct reverse command
        if(selectedState != DEFAULT_STATE ){
            CommandDTO reverse = new SvcWifiDTO(null, NetworkAdapterUtilities.NETWORK_STATES.get(DEFAULT_STATE));

            gpsCommand.setResetCommand(reverse);
        }

        return Arrays.asList(gpsCommand);
    }
}
