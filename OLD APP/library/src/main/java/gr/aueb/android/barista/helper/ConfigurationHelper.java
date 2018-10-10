package gr.aueb.android.barista.helper;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import java.lang.annotation.Annotation;

import gr.aueb.android.barista.annotation.Configuration;
import gr.aueb.android.barista.annotation.Device;
import gr.aueb.android.barista.command.Command;
import gr.aueb.android.barista.command.CommandChooser;

/**
 * Created by Miltiadis Konsolakis on 8/2/16.
 */
public class ConfigurationHelper {

    public static boolean isCurrentDeviceModel(Activity activity, Device.Model model) {
        Command command = CommandChooser.GetCommandInstance();

        if(command != null) {
            command.setContext(activity);
            if(command.isCurrentDensity(model.getDpi()) && command.isCurrentSize(model.getWidth(), model.getHeight())) { return true; }
        }

        return false;
    }

    public static boolean isCurrentConfiguration(Activity activity, Configuration configuration) {
        Command command = CommandChooser.GetCommandInstance();

        if(command != null) {
            command.setContext(activity);
            if(command.isCurrentDensity(configuration.dpi()) && command.isCurrentSize(configuration.width(), configuration.height()) && isCurrentOrientation(activity, configuration.orientation())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCurrentOrientation(Activity activity, Configuration.Orientation orientation) {
        if((Compatibility.currentOrientation(activity) == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) && (orientation == Configuration.Orientation.PORTRAIT)) { return true; }
        else if((Compatibility.currentOrientation(activity) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) && (orientation == Configuration.Orientation.LANDSCAPE)) { return true; }

        return false;
    }

    public static String configToString(Configuration configuration) {
        if(configuration != null) {
            // Check device association
            String modelName = getDevicesNameFromConfiguration(configuration);
            if (modelName.length() > 0) { modelName = "\nDevices: " + modelName; }

            return "Configuration dpi: " + configuration.dpi() + " width: " + configuration.width() + " Weight: " + configuration.height() + " Orientation: " + configuration.orientation() + modelName;
        }

        return "";
    }

    public static String configToShortString(Configuration configuration) {
        if(configuration != null) {
            String shortName = getDevicesNameFromConfiguration(configuration);
            if (shortName.length() > 0) { shortName = shortName.replace(", ", "_"); }

            shortName += "_W" + configuration.width() + "-H" + configuration.height() + "-DPI" + configuration.dpi();
            shortName += "_" + configuration.orientation();

            return shortName;
        }

        return "";
    }

    public static String getDevicesNameFromConfiguration(Configuration configuration) {
        if(configuration != null) {
            // Check device association
            String modelsName = "";
            if (configuration != null) {
                Device.Model[] models = Device.Model.values();
                for (int i = 0; i < models.length; i++) {
                    if (models[i].getDpi() == configuration.dpi() && models[i].getWidth() == configuration.width() && models[i].getHeight() == configuration.height()) {
                        modelsName += ((modelsName.length() == 0) ? "" : ", ") + models[i].name();
                    }
                }
            }

            return modelsName;
        }

        return "";
    }

    public static Configuration getResetConfiguration() {
        return new Configuration() {
            @Override
            public Class<? extends Annotation> annotationType() { return Configuration.class; }
            @Override
            public int dpi() { return 0; }
            @Override
            public int width() { return 0; }
            @Override
            public int height() { return 0; }
            @Override
            public Orientation orientation() { return Orientation.PORTRAIT; }
        };
    }
}
