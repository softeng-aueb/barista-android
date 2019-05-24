package gr.aueb.android.barista.core.annotations.enumarations;

import android.view.Surface;

import java.util.EnumMap;

public class OrientationOptionUtilities {

    public static EnumMap<OrientationOptions, Integer> ORIENTATION_OPTIONS = new EnumMap<>(OrientationOptions.class);

    static{
        ORIENTATION_OPTIONS.put(OrientationOptions.ORIENTATION_0, Surface.ROTATION_0);
        ORIENTATION_OPTIONS.put(OrientationOptions.ORIENTATION_90, Surface.ROTATION_90);
        ORIENTATION_OPTIONS.put(OrientationOptions.ORIENTATION_180,Surface.ROTATION_180);
        ORIENTATION_OPTIONS.put(OrientationOptions.ORIENTATION_270, Surface.ROTATION_270);
    }
}
