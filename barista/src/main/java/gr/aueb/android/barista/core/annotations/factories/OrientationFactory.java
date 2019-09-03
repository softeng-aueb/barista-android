package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Orientation;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptionUtilities;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.SetOrientationDTO;

/**
 *   Command Factory that parses Orientation type annotations and creates
 *   equivalent OrientationDTO commands.
 *
 */
public class OrientationFactory implements CommandFactory {
    private final OrientationOptions DEFAULT_ORIENTATION = OrientationOptions.ORIENTATION_0;

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        CommandDTO orientationCommand = null;

        OrientationOptions selectedOrientation = ((Orientation)a).value();
        Integer state = OrientationOptionUtilities.ORIENTATION_OPTIONS.get(selectedOrientation) ;
        orientationCommand = new SetOrientationDTO(null,state);

        // construct reverse command
        if(selectedOrientation != DEFAULT_ORIENTATION ){
            CommandDTO reverse = new SetOrientationDTO(null, OrientationOptionUtilities.ORIENTATION_OPTIONS.get(DEFAULT_ORIENTATION));
            orientationCommand.setResetCommand(reverse);
        }

        return Arrays.asList(orientationCommand);
    }
}
