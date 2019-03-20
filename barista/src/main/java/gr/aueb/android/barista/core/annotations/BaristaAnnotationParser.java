package gr.aueb.android.barista.core.annotations;

import org.junit.runner.Description;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import gr.aueb.android.barista.core.annotations.factories.BatteryCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.CommandFactory;
import gr.aueb.android.barista.core.annotations.factories.DataCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.GeoFixCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.PmGrantCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.WifiCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.WmDensityCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.WmSizeCommandFactory;
import gr.aueb.android.barista.core.model.CommandDTO;

public class BaristaAnnotationParser {

    private static ArrayList<Class> supportedBaristaCommandAnotations;

    private static Hashtable<Class, CommandFactory> commandConstructorMap = new Hashtable<>();

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
        commandConstructorMap.put(GeoFix.class, new GeoFixCommandFactory());
        commandConstructorMap.put(ScreenSize.class, new WmSizeCommandFactory());
        commandConstructorMap.put(Permission.class, new PmGrantCommandFactory());
        commandConstructorMap.put(Density.class, new WmDensityCommandFactory());
        commandConstructorMap.put(BatteryOptions.class,new BatteryCommandFactory());
        commandConstructorMap.put(Data.class,new DataCommandFactory());
        commandConstructorMap.put(Wifi.class,new WifiCommandFactory());
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
