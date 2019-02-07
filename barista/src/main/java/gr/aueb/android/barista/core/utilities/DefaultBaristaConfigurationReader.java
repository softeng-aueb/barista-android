package gr.aueb.android.barista.core.utilities;

import android.os.Environment;
import android.support.test.InstrumentationRegistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

import timber.log.Timber;

public class DefaultBaristaConfigurationReader {

    private static final int DEFAULT_PORT = 8040;
    private static String sessionToken = null;
    public DefaultBaristaConfigurationReader(){

    }

    public static int getBaristaServerPort() {
        Integer portValue;

        String packageName = InstrumentationRegistry.getTargetContext().getPackageName(); // InstrumentationRegistry.getTargetContext().getPackageName();

        String buildConfigClass = packageName + ".BuildConfig";

        try {
            Class clazz = Class.forName(buildConfigClass);
            Field portField = clazz.getField("BARISTA_PORT");

            if(portField == null){
                portValue = DEFAULT_PORT;
                Timber.d("Using default port: %s",portValue);
            }
            else{
                portField.setAccessible(true);
                portValue = (Integer) portField.get(clazz);
                Timber.d("Using given configuration port: %s", portValue);
            }

            return portValue;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }
        return -1;

    }


    public static String getEmulatorSessionToken() {
        if(sessionToken == null) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "barista-token.txt");
            Timber.d("Searching for token at: " + file.getAbsolutePath());
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sessionToken = line;
                }

            } catch (IOException e) {
                Timber.e("Error while trying to read the seessionToken. "+e.getMessage());
                e.printStackTrace();
            }

            return sessionToken;
        }
        else{
            Timber.d("Returning cached value.");
            return sessionToken;
        }
    }
}

