package gr.aueb.android.barista.command;

import android.os.Build;
import android.util.Log;

/**
 * Created by Miltiadis Konsolakis on 11/1/2016.
 */
public class CommandChooser {
    public static Command GetCommandInstance() {
        Command command = null;

        //check if exists command class
        String sdk = String.valueOf(Build.VERSION.SDK_INT);
        Log.d("AUEB_DEBUG", "Current SDK: " + sdk + "\n");

        try {
            Class cmd = Class.forName("gr.aueb.konsolakis.library.command.Command" + sdk);
            Log.d("AUEB_DEBUG", "Command for sdk: " + sdk + "\n");

            try {
                command = (Command) cmd.newInstance();
            } catch (InstantiationException e) {
                //e.printStackTrace();
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
            }

            return command;
        } catch(ClassNotFoundException e) {
            //e.printStackTrace();
        }

        Log.d("AUEB_DEBUG", "CommandDefault\n");
        command = new CommandDefaultSDK();

        return command;
    }
}
