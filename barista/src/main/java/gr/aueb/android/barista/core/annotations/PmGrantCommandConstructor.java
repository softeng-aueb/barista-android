package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.PmGrantDTO;

public class PmGrantCommandConstructor implements CommandConstructor {


    @Override
    public CommandDTO constructCommand(Annotation a) {
        String permission = ((Permission)a).type();

        PmGrantDTO grantCommand = new PmGrantDTO(null, permission);
        return grantCommand;
    }
}
