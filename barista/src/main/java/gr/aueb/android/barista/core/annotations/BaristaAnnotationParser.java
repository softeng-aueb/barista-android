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
import gr.aueb.android.barista.core.annotations.factories.FollowPathFactory;
import gr.aueb.android.barista.core.annotations.factories.GeoFixCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.OrientationFactory;
import gr.aueb.android.barista.core.annotations.factories.PmGrantCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.WifiCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.WmDensityCommandFactory;
import gr.aueb.android.barista.core.annotations.factories.WmSizeCommandFactory;
import gr.aueb.android.barista.core.model.CommandDTO;
import timber.log.Timber;

/**
 *  BaristaAnnotationParser is used to process the annotations of a test method and build the appropriate
 *  Commands that must to be executed in order the inscribed preferences to be applied to the emulator.
 *
 */
public class BaristaAnnotationParser {

    /**
     *  A hash table that maps Annotation Names to Command Factories. For each Barista annotation a unique command factory
     *  must be present in order
     */
    private static Hashtable<String, CommandFactory> commandFactoryMap = new Hashtable<>();

    /**
     * statically map a command factory for each implemented annotation
     */
    static{
        commandFactoryMap.put("GeoFix", new GeoFixCommandFactory());
        commandFactoryMap.put("ScreenSize", new WmSizeCommandFactory());
        commandFactoryMap.put("Permission", new PmGrantCommandFactory());
        commandFactoryMap.put("Density", new WmDensityCommandFactory());
        commandFactoryMap.put("BatteryOptions", new BatteryCommandFactory());
        commandFactoryMap.put("Data", new DataCommandFactory());
        commandFactoryMap.put("Wifi", new WifiCommandFactory());
        commandFactoryMap.put("Orientation", new OrientationFactory());
        commandFactoryMap.put("FollowPath", new FollowPathFactory());


    }

    /**
     * This command will parse the Desscription of a JUnit test runner and will look for
     * barista annotations. For each annotation found it will construct a command and add it to a list.
     * Finally a list will be returned containing all the commands found.
     *
     * @param description
     * @return
     */
    public static List<CommandDTO> getParsedCommands(Description description){

        List<CommandDTO> commandList = new ArrayList<>();

        for(Annotation providedAnnotation : description.getAnnotations()) {

            String annotationName = providedAnnotation.annotationType().getSimpleName();
            CommandFactory factory = commandFactoryMap.get(annotationName);
            if(factory !=null) {
                Timber.d("Found annotation: "+ annotationName);
                Collection<CommandDTO> cmd = factory.constructCommand(providedAnnotation);
                cmd.forEach(command->commandList.add(command));

            }


        }

        return commandList;
    }


}
