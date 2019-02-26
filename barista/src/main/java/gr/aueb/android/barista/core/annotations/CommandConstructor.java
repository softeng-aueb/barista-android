package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.core.model.CommandDTO;

/**
 * Every Barista Command must be associated with a commandConstructor.The Command Constructor is responsible for transforming
 * data passed with annotations to commandDTO objects. The CommandConstructor interface describes the method that parses an Annotation instance
 * and returns a CommandDTO instance.
 * The maping between Command and CommandConstructor is handled by the BaristaAnnotationParser
 * @see BaristaAnotationParser
 * @see CommandDTO
 */
public interface CommandConstructor {

    /**
     * Function that transforms an annotation to a Command.
     * @param a Annotation that belongs to the barista command collection.
     * @return A CommandDTO object that represents the command indeded to be called. The result is the object that will be JSONised
     * and sent to the Barista Server for execution.
     */
    public CommandDTO constructCommand(Annotation a);
}
