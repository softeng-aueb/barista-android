package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.WmSizeDTO;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import timber.log.Timber;

public class WmSizeCommandConstructor implements CommandConstructor {

    @Override
    public CommandDTO constructCommand(Annotation a) throws BaristaParseException {
        int height = ((ScreenSize) a).height();
        int width = ((ScreenSize) a).width();
        Timber.d("Resizing screen to: "+width+"x"+height);

        String sessionToken = DefaultBaristaConfigurationReader.getEmulatorSessionToken();
        if(sessionToken != null){
            WmSizeDTO resizeCommand = new WmSizeDTO(sessionToken,height,width,false,"DPI");
            return resizeCommand;
        }
        else{
            throw new BaristaParseException();
        }
    }
}
