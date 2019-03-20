package gr.aueb.android.barista.core.annotations.constructors;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.WmSizeDTO;
import timber.log.Timber;

public class WmSizeCommandConstructor implements CommandConstructor{

    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {
        int height = ((ScreenSize) a).height();
        int width = ((ScreenSize) a).width();
        Timber.d("Resizing screen to: "+width+"x"+height);
        WmSizeDTO resizeCommand = new WmSizeDTO(null,width,height,false,"DPI");

        return Arrays.asList(resizeCommand);

    }
}
