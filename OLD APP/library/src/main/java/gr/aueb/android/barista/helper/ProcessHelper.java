package gr.aueb.android.barista.helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Miltiadis Konsolakis on 12/12/2015.
 */
public class ProcessHelper {

    public static String StreamToString(InputStream in) {
        String output = "";

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String string = "";
            while ((string = bf.readLine()) != null) { output += string + "\n"; }
        }catch (Exception ex) {
            Log.d("AUEB_DEBUG", ex.toString());
            output = "";
        }

        return output;
    }

}
