package gr.aueb.android.barista.core.annotations;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;

// FIXME find a beeter way to pass - handle the sessionToken. Must find a way to help with testing
public interface CommandConstructor {

    public CommandDTO constructCommand(Annotation a);
}
