package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Implementation of the @ScreenSize annotation.
 *
 *  This annotation can be applied only to methods. The attributes that are supported are:
 *
 *  width: Integer value that represents the width of the screen in pixels
 *  height: Integer value that represents the height of the screen in pixels
 *
 *  e.x @ScreenSize(width=500, height=1200)
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface ScreenSize {

    public int width();
    public int height();

}

