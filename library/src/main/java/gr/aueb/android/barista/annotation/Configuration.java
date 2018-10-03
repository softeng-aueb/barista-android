package gr.aueb.android.barista.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miltiadis Konsolakis on 14/12/2015.
 * 
 * Annotation mesa apo to opio dilono ena configuration siskeuis gia default Orientation exoume to PORTRAIT
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})

public @interface Configuration {

    public enum Orientation { PORTRAIT, LANDSCAPE }

    int dpi();
    int width();
    int height();
    Orientation orientation() default Orientation.PORTRAIT;

}
