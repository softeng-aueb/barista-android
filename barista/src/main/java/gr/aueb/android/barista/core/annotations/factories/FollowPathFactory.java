package gr.aueb.android.barista.core.annotations.factories;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.FollowPath;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.GeoFixDTO;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import gr.aueb.android.barista.core.utilities.KMLParser;
import gr.aueb.android.barista.core.utilities.helper_classes.Coordinate;

public class FollowPathFactory implements CommandFactory {
    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {

        ArrayList<CommandDTO> commands = new ArrayList<>();
        String filePath = ((FollowPath)a).file();
        File f = DefaultBaristaConfigurationReader.getPathFile();
        KMLParser kmlParser = new KMLParser(f.getPath());
        ArrayList<Coordinate> pointList = kmlParser.parseFile();
        for(Coordinate c : pointList){
           //GeoFixDTO grantCommand = new GeoFixDTO(null, c.getLattitutde(), c.getLongtitude());
            GeoFixDTO grantCommand = new GeoFixDTO(null, c.getLattitutde(), c.getLongtitude());
            commands.add(grantCommand);
            grantCommand.setDelay(500);
        }

        return commands;
    }
}
