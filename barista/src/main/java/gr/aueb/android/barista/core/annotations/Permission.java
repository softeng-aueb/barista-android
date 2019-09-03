package gr.aueb.android.barista.core.annotations;

import android.Manifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Implementation of the @Permission annotation.
 *
 *  This annotation can be applied only to methods. The attributes that are supported are:
 *
 *  value: String value that specifies the name of the Android Permission included in Manifest.permission.*
 *
 *  e.x @Permission(Manifest.permission.ACCESS_FINE_LOCATION)
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    String value();
}
