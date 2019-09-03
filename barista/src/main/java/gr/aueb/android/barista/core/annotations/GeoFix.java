package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Implementation of the @GeoFix annotation.
 *
 *  This annotation can be applied only to methods. The attributes that are supported are:
 *
 *  lat: double value that represents the latitude. Must be between -90 and 90
 *  longt: double value that represents the longitude. Must be between -180 and 180
 *
 *  e.x @GeoFix(lat=25.361, longt = 85.124)
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface GeoFix {

    public double lat();
    public double longt();

}

