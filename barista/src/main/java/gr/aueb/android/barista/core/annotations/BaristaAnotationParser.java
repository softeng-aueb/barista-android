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

    private static ArrayList<Class> annotationClasses;

    static{
        annotationClasses = new  ArrayList<>();
        annotationClasses.add(GeoFix.class);
        annotationClasses.add(ScreenSize.class);
    }

    private static Hashtable<Class,CommandConstructor> commandConstructorMap = new Hashtable<>();

    static{
        commandConstructorMap.put(GeoFix.class, new GeoFixCommandConstructor());
        commandConstructorMap.put(ScreenSize.class, new WmSizeCommandConstructor());
    }

    public static List<CommandDTO> getParsedCommands(Description description){
        List<CommandDTO> commandList = new ArrayList<>();

        for (Class c: annotationClasses) {
            Annotation providedAnnotation = description.getAnnotation(c);
            if(providedAnnotation !=null) {
                CommandDTO cmd = null;
                try {
                    cmd = commandConstructorMap.get(c).constructCommand(providedAnnotation);
                } catch (BaristaParseException e) {
                    Timber.e("Error trying to construct the command");
                    e.printStackTrace();
                }
                commandList.add(cmd);
            }
        }

        return commandList;
    }


}
