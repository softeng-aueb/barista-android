package gr.aueb.android.barista.core.utilities;

import org.junit.runner.Description;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gr.aueb.android.barista.core.model.Command;

public class BaristaAnotationParser {



    public static List<Command> getParsedCommands(Description description){
        List<Command> commandList = new ArrayList<>();

        // for each className = known barista annotation class {
            // Annotation screenAnnotaion = description.getAnnotation(className)

        //}
        return commandList;
    }


}
