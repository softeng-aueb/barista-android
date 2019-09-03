package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Density;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.WmDensityDTO;
import gr.aueb.android.barista.core.model.WmDensityResetDTO;

/**
 *   Command Factory that parses Density type annotations and creates
 *   equivalent DensityDTO commands.
 *
 */
public class WmDensityCommandFactory implements CommandFactory {

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        int density = ((Density)a).value();
        WmDensityDTO densityCommand = new WmDensityDTO(null,density);
        WmDensityResetDTO densityResetCommand = new WmDensityResetDTO(null);
        densityCommand.setResetCommand(densityResetCommand);
        return Arrays.asList(densityCommand);
    }
}

