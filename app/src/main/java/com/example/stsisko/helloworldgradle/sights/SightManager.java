package com.example.stsisko.helloworldgradle.sights;

import com.example.stsisko.helloworldgradle.helpers.DistanceCalculator;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;


public class SightManager {
    public static Hashtable<String, LatLng> sights = new Hashtable<>();
    static{

        sights.put(SightNames.ACROPOLIS, new LatLng(37.971646, 23.725745));
        sights.put(SightNames.SYNTAGMA, new LatLng(37.975356, 23.736843));
        sights.put(SightNames.AGORA, new LatLng(37.974795, 23.721510));
        sights.put(SightNames.MUSEUM, new LatLng(37.968529, 23.728612));
    }

    /**
     *  Lazy way to find the nearest sights. Each time a location update occurs, a linear search is made in order to deside
     *  which landmarks are worth displaying.
     *
     *  ONLY FOR DEMOSTRATION PURPOSES
     * @param currentLocation
     * @param radiusInMeters
     * @return
     */
    public static Collection<String> findNearbySights(LatLng currentLocation, double radiusInMeters){
        double radius = radiusInMeters/(double)100000;
        ArrayList<String> nearBy = new ArrayList<>();
        for(Map.Entry<String, LatLng> k: sights.entrySet()){
            LatLng v = k.getValue();
            double  distance = DistanceCalculator.calculateDistance(currentLocation.latitude,currentLocation.longitude, v.latitude, v.longitude);
            if(radius >= distance)
                nearBy.add(k.getKey());
        }
        return nearBy;
    }




}
