package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Wifi;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterUtilities;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.SvcWifiDTO;

/**
 *   Command Factory that parses Wifi type annotations and creates
 *   equivalent WifiDTO commands.
 *
 */
public class WifiCommandFactory implements CommandFactory {

    private NetworkAdapterStateType DEFAULT_STATE = NetworkAdapterStateType.ENABLED;

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
       CommandDTO wifiCommand = null;

       NetworkAdapterStateType selectedState = ((Wifi)a).value();
       Boolean state = NetworkAdapterUtilities.NETWORK_STATES.get(selectedState) ;
       wifiCommand = new SvcWifiDTO(null,state);

       // construct reverse command
       if(selectedState != DEFAULT_STATE ){
           CommandDTO reverse = new SvcWifiDTO(null, NetworkAdapterUtilities.NETWORK_STATES.get(DEFAULT_STATE));

           wifiCommand.setResetCommand(reverse);
       }

       return Arrays.asList(wifiCommand);
    }

}
