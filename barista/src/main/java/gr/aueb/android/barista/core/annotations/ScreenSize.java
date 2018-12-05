package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface ScreenSize {


    public String width() default "Default Param1";
    public String height() default "Default Param2";

}
