package gr.aueb.android.barista.core.annotations;

import org.junit.runner.Description;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import gr.aueb.android.barista.core.annotations.GeoFix;
import gr.aueb.android.barista.core.annotations.ScreenSize;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.CommandDTO;
import timber.log.Timber;

public class BaristaAnotationParser {

    private static ArrayList<Class> supportedBaristaCommandAnotations;

    private static Hashtable<Class,CommandConstructor> commandConstructorMap = new Hashtable<>();

    /**
     * statically initialize the supported commands with the implemented annotation classes
     */
    static{
        supportedBaristaCommandAnotations = new  ArrayList<>();
        supportedBaristaCommandAnotations.add(GeoFix.class);
        supportedBaristaCommandAnotations.add(ScreenSize.class);
        supportedBaristaCommandAnotations.add(Permission.class);
    }

    /**
     * statically map a comand constructor for each implemented annotation
     */
    static{
        commandConstructorMap.put(GeoFix.class, new GeoFixCommandConstructor());
        commandConstructorMap.put(ScreenSize.class, new WmSizeCommandConstructor());
        commandConstructorMap.put(Permission.class, new PmGrantCommandConstructor());
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

                CommandDTO cmd = commandConstructorMap.get(c).constructCommand(providedAnnotation);
                commandList.add(cmd);

            }
        }

        return commandList;
    }


}
