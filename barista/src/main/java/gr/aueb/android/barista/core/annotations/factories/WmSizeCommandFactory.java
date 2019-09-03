package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.WmSizeDTO;
import gr.aueb.android.barista.core.model.WmSizeResetDTO;
import timber.log.Timber;

/**
 *   Command Factory that parses ScreenSize type annotations and creates
 *   equivalent ScreenSizeDTO commands.
 *
 */
public class WmSizeCommandFactory implements CommandFactory {

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        int height = ((ScreenSize) a).height();
        int width = ((ScreenSize) a).width();
        Timber.d("Resizing screen to: "+width+"x"+height);
        WmSizeDTO resizeCommand = new WmSizeDTO(null,width,height,false,"DPI");

        // create the reverse commands
        CommandDTO resetCommand = new WmSizeResetDTO(null);
        resizeCommand.setResetCommand(resetCommand);

        return Arrays.asList(resizeCommand);

    }
}
