package com.example.stsisko.helloworldgradle.sights;

import com.example.stsisko.helloworldgradle.helpers.DistanceCalculator;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;


public class SightManager {
    public static Hashtable<String, LatLng> sights = new Hashtable<>();
    static{

        sights.put(SightNames.ACROPOLIS, new LatLng(37.971646, 23.725745));
        sights.put(SightNames.SYNTAGMA, new LatLng(37.975356, 23.736843));
        sights.put(SightNames.AGORA, new LatLng(37.974795, 23.721510));
        sights.put(SightNames.MUSEUM, new LatLng(37.968529, 23.728612));
    }


    public static Collection<String> findNearbySights(LatLng currentLocation, double radiusInMeters){
        double radius = radiusInMeters/(double)100000;
        ArrayList<String> nearBy = new ArrayList<>();
        sights.forEach( (k,v) -> {
            double  distance = DistanceCalculator.calculateDistance(currentLocation.latitude,currentLocation.longitude, v.latitude, v.longitude);
            if(radius >= distance)
                nearBy.add(k);
        });
        return nearBy;
    }




}
