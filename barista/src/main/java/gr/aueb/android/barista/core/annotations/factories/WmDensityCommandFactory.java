package gr.aueb.android.barista.core.annotations.constructors;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Density;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.WmDensityDTO;

public class WmDensityCommandConstructor implements CommandConstructor {

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        int density = ((Density)a).density();
        WmDensityDTO densityCommand = new WmDensityDTO(null,density);
        return Arrays.asList(densityCommand);
    }
}

