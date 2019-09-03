package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Permission;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.PmGrantDTO;

/**
 *   Command Factory that parses Permission type annotations and creates
 *   equivalent PermissionDTO commands.
 *
 */
public class PmGrantCommandFactory implements CommandFactory {

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        String permission = ((Permission)a).value();

        PmGrantDTO grantCommand = new PmGrantDTO(null, permission);

        /*
            Reverse command cannot be requested since permission revokation results
            in restarting the application and therefor terminating the testing procedure
         */
        //reverse command
        //PmRevokeDTO revokeCommand = new PmRevokeDTO(null, permission);
        //grantCommand.setResetCommand(revokeCommand);

        return Arrays.asList(grantCommand);
    }
}
