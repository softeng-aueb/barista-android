package gr.aueb.android.barista.core.utilities;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import gr.aueb.android.barista.core.utilities.helper_classes.Coordinate;

public class KMLParser {
    private String filePath;
    private File kmlFile;

    public KMLParser(String filePath){
        this.kmlFile = new File(filePath);

    }

    public ArrayList<Coordinate> parseFile(){
        ArrayList<Coordinate> parsedPoints = new ArrayList<>();
        try {
           DocumentBuilderFactory kmlFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder kmlBuilder = kmlFactory.newDocumentBuilder();
           Document doc = kmlBuilder.parse(kmlFile);

           doc.getDocumentElement().normalize();

           String coordinates = doc.getElementsByTagName("coordinates").item(0).getTextContent().trim();
           String[] points = coordinates.split(" ");
           for(String s : points ){

               String[] singlePoint = s.split(",");
               String longtitude = singlePoint[0];
               String lattitude = singlePoint[1];
                // String height = singlePoint[3]; NEVER USED
               Coordinate singleCoordinate = new Coordinate(Double.parseDouble(lattitude), Double.parseDouble(longtitude));
               parsedPoints.add(singleCoordinate);

           }
           
           return parsedPoints;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
