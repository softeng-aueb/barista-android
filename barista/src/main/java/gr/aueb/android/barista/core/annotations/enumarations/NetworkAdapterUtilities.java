package gr.aueb.android.barista.core.annotations.enumarations;

import java.util.EnumMap;

public class NetworkAdapterUtilities {

    public static EnumMap<NetworkAdapterStateType, Boolean> NETWORK_STATES = new EnumMap<>(NetworkAdapterStateType.class);

    static{
        NETWORK_STATES.put(NetworkAdapterStateType.ENABLED, true);
        NETWORK_STATES.put(NetworkAdapterStateType.DISABLED, false);
    }
}
