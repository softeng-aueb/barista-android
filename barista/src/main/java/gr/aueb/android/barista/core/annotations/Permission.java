package gr.aueb.android.barista.core.annotations;

import android.Manifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    String type();
}
