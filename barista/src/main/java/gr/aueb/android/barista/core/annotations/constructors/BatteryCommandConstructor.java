package gr.aueb.android.barista.core.annotations.constructors;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.BatteryOptions;
import gr.aueb.android.barista.core.model.BatteryChargeDTO;
import gr.aueb.android.barista.core.model.BatteryLevelDTO;
import gr.aueb.android.barista.core.model.CommandDTO;

public class BatteryCommandConstructor implements CommandConstructor {
    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {

        int level = ((BatteryOptions)a).level();
        boolean pluggedIn = ((BatteryOptions)a).plugged();

        // create one command for seeting the level
        CommandDTO batteryLevelCommand = new BatteryLevelDTO(null,level);

        // create a second command for setting the charging status
        CommandDTO batteryChargeCommand = new BatteryChargeDTO(null, pluggedIn);

        return Arrays.asList(batteryLevelCommand,batteryChargeCommand);
    }
}
