package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(ElementType.METHOD)
/**
 * GeoFix
 *  lat must be between -90 and 90
 *  longth must be bewteen -180 and 180
 */
public @interface GeoFix {

    public double lat();
    public double longt();

}

