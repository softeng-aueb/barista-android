package gr.aueb.android.barista.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miltiadis Konsolakis on 14/12/2015.
 *
 * Annotation mesa apo to opoio dilonoume polla Modela siskeuon to default orientation einai PORTRAIT
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})

public @interface Devices {
    Device[] devices();
}
