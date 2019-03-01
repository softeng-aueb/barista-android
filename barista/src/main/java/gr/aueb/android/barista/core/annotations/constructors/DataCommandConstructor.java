package gr.aueb.android.barista.core.annotations.constructors;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Data;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.SvcDataDTO;

public class DataCommandConstructor implements CommandConstructor {
    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        CommandDTO dataCommand = null;
        switch (((Data)a).enabled()){
            case ENABLED:
                dataCommand = new SvcDataDTO(null,true);
                break;
            case DISABLED:
                dataCommand = new SvcDataDTO(null,false);
                break;
        }

        return Arrays.asList(dataCommand);
    }
}
