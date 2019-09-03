package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.BaristaAnnotationParser;
import gr.aueb.android.barista.core.model.CommandDTO;

/**
 * Every Barista Command must be associated with a CommandFactory.The CommandFactory is responsible for transforming
 * data passed with annotations to commandDTO objects. The CommandFactory interface describes the method that parses an Annotation instance
 * and returns a CommandDTO instance.
 * The mapping between Command and  CommandFactory is handled by the BaristaAnnotationParser
 * @see BaristaAnnotationParser
 * @see CommandDTO
 */
public interface CommandFactory {

    /**
     * Function that transforms an annotation to a Command.
     * @param a Annotation that belongs to the barista command collection.
     * @return A CommandDTO collection  that represents the list of commands command indeded to be called. The result is the object that will be JSONised
     * and sent to the Barista Server for execution.
     */
    Collection<CommandDTO> constructCommand(Annotation a);


}
