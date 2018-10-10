package gr.aueb.android.barista.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miltiadis Konsolakis on 14/12/2015.
 *
 * Annotation gia tin dilosi pollon configuration se mia methodo h klassi
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})

public @interface Configurations {
    Configuration[] configurations();
}
