package gr.aueb.android.barista.core.utilities;

import org.junit.Test;

import java.util.ArrayList;

import gr.aueb.android.barista.core.utilities.helper_classes.Coordinate;

import static org.junit.Assert.*;

public class KMLParserTest {

    @Test
    public void testKMLParser(){

        KMLParser myParser = new KMLParser("C:\\Users\\s.tsisko\\Downloads\\Athens_Tour.kml");
        ArrayList<Coordinate> coordinates = myParser.parseFile();
        for(Coordinate s : coordinates){
            System.out.println(s.toString());
        }
    }

}