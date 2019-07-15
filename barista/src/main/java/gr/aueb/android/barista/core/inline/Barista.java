package gr.aueb.android.barista.core.inline;

import android.view.Surface;

import java.util.Arrays;

import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.Orientation;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterUtilities;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptionUtilities;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;
import gr.aueb.android.barista.core.http_client.HTTPClientManager;
import gr.aueb.android.barista.core.model.BatteryChargeDTO;
import gr.aueb.android.barista.core.model.BatteryLevelDTO;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.GeoFixDTO;
import gr.aueb.android.barista.core.model.SetOrientationDTO;
import gr.aueb.android.barista.core.model.SvcDataDTO;
import gr.aueb.android.barista.core.model.SvcWifiDTO;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import timber.log.Timber;

public class Barista{

    private static final NetworkAdapterStateType DEFAULT_WIFI_STATE = NetworkAdapterStateType.ENABLED;
    private static final NetworkAdapterStateType DEFAULT_DATA_STATE = NetworkAdapterStateType.ENABLED;
    private static final OrientationOptions DEFAULT_ORIENTATION = OrientationOptions.ORIENTATION_0;
    private static final int DEFAULT_BATTERY_LEVEL = 100;
    private static final boolean DEFAULT_CHARGING_STATUS = true;


    private static final String sessionToken = DefaultBaristaConfigurationReader.getEmulatorSessionToken();

    public static void setWifiState(NetworkAdapterStateType selectedState){
        CommandDTO dataCommand = null;
        dataCommand = new SvcWifiDTO(sessionToken, NetworkAdapterUtilities.NETWORK_STATES.get(selectedState));

        //construct reverse command
        if(selectedState != DEFAULT_WIFI_STATE) {
            CommandDTO resetCommand = new SvcWifiDTO(sessionToken, NetworkAdapterUtilities.NETWORK_STATES.get(DEFAULT_WIFI_STATE));
            dataCommand.setResetCommand(resetCommand);
        }

        HTTPClientManager.getInstance().executeAllCommands( Arrays.asList(dataCommand));
    }

    public static void setDataState(NetworkAdapterStateType selectedState){
        CommandDTO dataCommand = null;
        dataCommand = new SvcDataDTO(sessionToken, NetworkAdapterUtilities.NETWORK_STATES.get(selectedState));

        //construct reverse command
        if(selectedState != DEFAULT_DATA_STATE) {
            CommandDTO resetCommand = new SvcDataDTO(sessionToken, NetworkAdapterUtilities.NETWORK_STATES.get(DEFAULT_DATA_STATE));
            dataCommand.setResetCommand(resetCommand);
        }
        Timber.d("Executing inline DATA command");
        HTTPClientManager.getInstance().executeAllCommands( Arrays.asList(dataCommand));
    }

    public static void setBatteryState(int level, boolean plugged){
        // create one command for seeting the level
        CommandDTO batteryLevelCommand = new BatteryLevelDTO(sessionToken,level);

        // create the reverse command
        if(level != DEFAULT_BATTERY_LEVEL){
            CommandDTO resetBatteryLevelCommand = new BatteryLevelDTO(sessionToken,DEFAULT_BATTERY_LEVEL);
            batteryLevelCommand.setResetCommand(resetBatteryLevelCommand);
        }

        // create a second command for setting the charging status
        CommandDTO batteryChargeCommand = new BatteryChargeDTO(sessionToken, plugged);

        // create the reverse command
        if(plugged != DEFAULT_CHARGING_STATUS){
            CommandDTO resetChargingStatusCommand = new BatteryChargeDTO(sessionToken,DEFAULT_CHARGING_STATUS);
            batteryChargeCommand.setResetCommand(resetChargingStatusCommand);
        }

        HTTPClientManager.getInstance().executeAllCommands(  Arrays.asList(batteryLevelCommand,batteryChargeCommand));

    }

    public static void setOrientation(OrientationOptions selectedOrientation){
        CommandDTO orientationCommand = null;


        Integer state = OrientationOptionUtilities.ORIENTATION_OPTIONS.get(selectedOrientation) ;
        orientationCommand = new SetOrientationDTO(sessionToken,state);

        // construct reverse command
        if(selectedOrientation != DEFAULT_ORIENTATION ){
            CommandDTO reverse = new SetOrientationDTO(sessionToken, OrientationOptionUtilities.ORIENTATION_OPTIONS.get(DEFAULT_ORIENTATION));
            orientationCommand.setResetCommand(reverse);
        }

        HTTPClientManager.getInstance().executeAllCommands(Arrays.asList(orientationCommand));
    }


    public static void setGeolocation(double longitude, double latitude){

        Timber.d("Set GPS coordinates to: lat:" + latitude + ", long:" + longitude);
        CommandDTO geofixCommand = new GeoFixDTO(sessionToken, latitude, longitude);
        //geofixCommand.setDelay(500);
        HTTPClientManager.getInstance().executeAllCommands(Arrays.asList(geofixCommand));
    }

}
