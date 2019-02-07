package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.GeoFixDTO;
import gr.aueb.android.barista.core.utilities.BaristaConfigurationReader;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import timber.log.Timber;

public class GeoFixCommandConstructor implements CommandConstructor {

    @Override
    public CommandDTO constructCommand(Annotation a) throws BaristaParseException {

        double latitude = ((GeoFix) a).lat();
        double longitude = ((GeoFix) a).longt();
        Timber.d("Set GPS coordinates to: lat:"+latitude+", long:"+longitude);
        String sessionToken = DefaultBaristaConfigurationReader.getEmulatorSessionToken();

        if( sessionToken != null) {
            CommandDTO geofixCommand = new GeoFixDTO(sessionToken, latitude, longitude);
            return geofixCommand;
        }
        else{
            throw new BaristaParseException();
         }

    }
}
