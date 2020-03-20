package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gr.aueb.android.barista.BuildConfig;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Monkey {

    public int seed();
    public int count();
    public int throttle();
    public String apk_name();

}
