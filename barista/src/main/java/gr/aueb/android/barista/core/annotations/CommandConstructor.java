package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.core.model.CommandDTO;

public interface CommandConstructor {

    public CommandDTO constructCommand(Annotation a) throws BaristaParseException;
}
