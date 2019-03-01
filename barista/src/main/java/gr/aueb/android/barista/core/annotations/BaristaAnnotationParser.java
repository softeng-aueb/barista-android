package gr.aueb.android.barista.core.annotations;

import org.junit.runner.Description;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import gr.aueb.android.barista.core.annotations.constructors.BatteryCommandConstructor;
import gr.aueb.android.barista.core.annotations.constructors.CommandConstructor;
import gr.aueb.android.barista.core.annotations.constructors.DataCommandConstructor;
import gr.aueb.android.barista.core.annotations.constructors.GeoFixCommandConstructor;
import gr.aueb.android.barista.core.annotations.constructors.PmGrantCommandConstructor;
import gr.aueb.android.barista.core.annotations.constructors.WifiCommandConstructor;
import gr.aueb.android.barista.core.annotations.constructors.WmDensityCommandConstructor;
import gr.aueb.android.barista.core.annotations.constructors.WmSizeCommandConstructor;
import gr.aueb.android.barista.core.model.CommandDTO;

public class BaristaAnnotationParser {

    private static ArrayList<Class> supportedBaristaCommandAnotations;

    private static Hashtable<Class, CommandConstructor> commandConstructorMap = new Hashtable<>();

    /**
     * statically initialize the supported commands with the implemented annotation classes
     */
    static{
        supportedBaristaCommandAnotations = new  ArrayList<>();
        supportedBaristaCommandAnotations.add(GeoFix.class);
        supportedBaristaCommandAnotations.add(ScreenSize.class);
        supportedBaristaCommandAnotations.add(Permission.class);
        supportedBaristaCommandAnotations.add(Density.class);
        supportedBaristaCommandAnotations.add(BatteryOptions.class);
        supportedBaristaCommandAnotations.add(Data.class);
        supportedBaristaCommandAnotations.add(Wifi.class);
    }

    /**
     * statically map a comand constructor for each implemented annotation
     */
    static{
        commandConstructorMap.put(GeoFix.class, new GeoFixCommandConstructor());
        commandConstructorMap.put(ScreenSize.class, new WmSizeCommandConstructor());
        commandConstructorMap.put(Permission.class, new PmGrantCommandConstructor());
        commandConstructorMap.put(Density.class, new WmDensityCommandConstructor());
        commandConstructorMap.put(BatteryOptions.class,new BatteryCommandConstructor());
        commandConstructorMap.put(Data.class,new DataCommandConstructor());
        commandConstructorMap.put(Wifi.class,new WifiCommandConstructor());
    }

    /**
     * This command will parse the Desscription of a JUnit test runner and will look for
     * barista annotations. For each annotation found it will construct a command and add it to a list.
     * Finally a list will be returned containing all the commands found.
     *
     *
     * @param description
     * @return
     */
    public static List<CommandDTO> getParsedCommands(Description description){

        List<CommandDTO> commandList = new ArrayList<>();

        for (Class c: supportedBaristaCommandAnotations) {
            Annotation providedAnnotation = description.getAnnotation(c);
            if(providedAnnotation !=null) {

                Collection<CommandDTO> cmd = commandConstructorMap.get(c).constructCommand(providedAnnotation);
                cmd.forEach(command->commandList.add(command));

            }
        }

        return commandList;
    }


}
