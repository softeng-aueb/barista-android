package gr.aueb.android.barista.core.utilities;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

import androidx.test.InstrumentationRegistry;
import timber.log.Timber;

/**
 * DefaultBaristaConfigurationReader is responsible for fetching configuration option provided by the Barista Plugin
 *
 */
public class DefaultBaristaConfigurationReader {

    private static final int DEFAULT_PORT = 8040;
    private static String sessionToken = null;

    public DefaultBaristaConfigurationReader(){

    }

    /**
     * Method that searches and returns the value for the BARISTA_PORT field in the BuildConfig file of the instrumented app.
     * IMPORTANT: This method must be called after the Instrumentation Registry has been initialized or else it will fail.
     *
     * @return The integer value of the port. If none provided default port 8040 will be used
     */
    public static int getBaristaServerPort() {
        Integer portValue;

        String packageName = InstrumentationRegistry.getTargetContext().getPackageName();

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

    /**
     * Reads the barista-token.txt file created by the barista plugin and returns the sessionToken provided.
     * IMPORTANT: The barista library must have the READ_EXTRERNAL_STORAGE permission granted
     * @return The sessionToken generated by the server
     */
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
                Timber.e("Error while trying to read the sessionToken. "+e.getMessage());
                e.printStackTrace();
            }

            return sessionToken;
        }
        else{
            Timber.d("Returning cached value.");
            return sessionToken;
        }
    }

    /**
     * Reads the barista-token.txt file created by the barista plugin and returns the sessionToken provided.
     * IMPORTANT: The barista library must have the READ_EXTRERNAL_STORAGE permission granted
     * @return The sessionToken generated by the server
     */
    //todo This is ONLY FOR POC. File name must be dynamically provided by the plugin
    public static File getPathFile() {

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "AthensTour.kml");
        return file;

    }
}

