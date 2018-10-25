package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface SaySomething{


    public String param1() default "Default Param1";
    public String param2() default "Default Param2";
    public String param3() default "Default Param3";
    public String param4() default "Default Param4";
}
