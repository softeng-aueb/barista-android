package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Implementation of the @Orientation annotation.
 *
 *  This annotation can be applied only to methods. The attributes that are supported are:
 *
 *  value: OrientationOptions value that specifies the orientation of the screen. By default orientation is set to 0 degrees
 *
 *  e.x @Orientation(OrientationOptions.ORIENTATION_90)
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Orientation {
    OrientationOptions value() default OrientationOptions.ORIENTATION_0;
}
