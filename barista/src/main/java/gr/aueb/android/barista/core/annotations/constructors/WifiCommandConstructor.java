package gr.aueb.android.barista.core.annotations.constructors;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Wifi;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.SvcWifiDTO;

public class WifiCommandConstructor implements CommandConstructor {
    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
       CommandDTO wifiCommand = null;
        switch (((Wifi)a).enabled()){
            case ENABLED:
                wifiCommand = new SvcWifiDTO(null,true);
                break;
            case DISABLED:
                wifiCommand = new SvcWifiDTO(null,false);
                break;
        }
        return Arrays.asList(wifiCommand);
    }
}
