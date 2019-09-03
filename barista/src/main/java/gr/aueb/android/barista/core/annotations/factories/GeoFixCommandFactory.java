package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.GeoFixDTO;
import timber.log.Timber;


/**
 *   Command Factory that parses GeoFix type annotations and creates
 *   equivalent GeoFixDTO commands.
 *
 */
public class GeoFixCommandFactory implements CommandFactory {


    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {

        double latitude = ((GeoFix) a).lat();
        double longitude = ((GeoFix) a).longt();
        Timber.d("Set GPS coordinates to: lat:" + latitude + ", long:" + longitude);
        CommandDTO geofixCommand = new GeoFixDTO(null, latitude, longitude);
        return Arrays.asList(geofixCommand);
    }
}
