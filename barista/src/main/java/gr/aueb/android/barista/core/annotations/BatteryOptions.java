package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Implementation of the @BatteryOptions annotation.
 *
 *  This annotation can be applied only to methods. The attributes that are supported are:
 *
 *  plugged: Boolean value that specifies the charging state of the emulator
 *  level: Integer value that indicates the battery level of the emulator.
 *
 *  e.x @batteryOptions(plugged =false, level=30)
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface BatteryOptions {

    boolean plugged() default true;
    int level() default 100;

}
