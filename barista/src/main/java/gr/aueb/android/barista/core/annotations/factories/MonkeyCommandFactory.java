package gr.aueb.android.barista.core.annotations.factories;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import gr.aueb.android.barista.core.annotations.Monkey;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.MonkeyDTO;
import timber.log.Timber;

public class MonkeyCommandFactory implements CommandFactory {
    @Override
    public Collection<CommandDTO> constructCommand(Annotation a) {

        int seed = ((Monkey) a).seed();
        int count = ((Monkey) a).count();
        int throttle = ((Monkey) a).throttle();
        String apk = ((Monkey) a).apk_name();
        Timber.d("Executing Monkey fuzzer");
        CommandDTO monkeyFuzzer = new MonkeyDTO(null, seed, count, throttle, apk);
        return Arrays.asList(monkeyFuzzer);
    }
}
