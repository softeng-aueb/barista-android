package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.BatteryOptions;
import gr.aueb.android.barista.core.model.BatteryChargeDTO;
import gr.aueb.android.barista.core.model.BatteryLevelDTO;
import gr.aueb.android.barista.core.model.CommandDTO;

/**
 *  CommandFactory that creates BatteryCommands. From a BatterOptions annotation can derive two diffferent
 *  commands. A command for requesting battery level change and a command that requests charging status state.
 *  Therefore the result of this factory is a lsit of two commands.
 *
 */
public class BatteryCommandFactory implements CommandFactory {

    private final int DEFAULT_BATTERY_LEVEL = 100;
    private final boolean DEFAULT_CHARGING_STATUS = true;

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {

        int level = ((BatteryOptions)a).level();
        boolean pluggedIn = ((BatteryOptions)a).plugged();

        // create one command for seeting the level
        CommandDTO batteryLevelCommand = new BatteryLevelDTO(null,level);

        // create the reverse command
        if(level != DEFAULT_BATTERY_LEVEL){
            CommandDTO resetBatteryLevelCommand = new BatteryLevelDTO(null,DEFAULT_BATTERY_LEVEL);
            batteryLevelCommand.setResetCommand(resetBatteryLevelCommand);
        }

        // create a second command for setting the charging status
        CommandDTO batteryChargeCommand = new BatteryChargeDTO(null, pluggedIn);

        // create the reverse command
        if(pluggedIn != DEFAULT_CHARGING_STATUS){
            CommandDTO resetChargingStatusCommand = new BatteryChargeDTO(null,DEFAULT_CHARGING_STATUS);
            batteryChargeCommand.setResetCommand(resetChargingStatusCommand);
        }

        return Arrays.asList(batteryLevelCommand,batteryChargeCommand);
    }
}
