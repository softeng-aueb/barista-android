package gr.aueb.android.barista.core.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Orientation {
    OrientationOptions value() default OrientationOptions.ORIENTATION_0;
}
