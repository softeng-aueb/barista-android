package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Implementation of the @Density annotation.
 *
 *  This annotation can be applied only to methods. The attributes that are supported are:
 *
 *  value: Integer value that specifies if the density of the emulator's screen
 *
 *  e.x @Density(200)
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Density {

    int value();

}
