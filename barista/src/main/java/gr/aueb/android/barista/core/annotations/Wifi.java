package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Implementation of the @Wifi annotation.
 *
 *  This annotation can be applied only to methods. The attributes that are supported are:
 *
 *  value: NetworkAdapterStateType value that specifies if the wifi adapter is switched on or off
 *
 *  e.x @Wifi(NetworkAdapterStateType.DISABLED)
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Wifi {
    NetworkAdapterStateType value() default NetworkAdapterStateType.ENABLED;
}
