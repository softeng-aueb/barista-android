package gr.aueb.android.barista.core.annotations;

import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptionUtilities;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;
import gr.aueb.android.barista.core.utilities.AndroidPermissions;

public class ConstantValues {

    public static final double lat = 60.56;
    public static final double longt = 160.56;
    public static final int width = 500;
    public static final int height = 600;
    public static final String permission = AndroidPermissions.BODY_SENSORS;
    public static final int density = 100;
    public static final int baterryLevel = 40;
    public static final boolean plugged = false;

    public static final boolean wifiEnabled = false;
    public static final NetworkAdapterStateType wifiEnabledEnum = NetworkAdapterStateType.DISABLED;

    public static final boolean dataEnabled = false;
    public static final NetworkAdapterStateType dataEnabledEnum = NetworkAdapterStateType.DISABLED;

    public static int orientationLandscape = OrientationOptionUtilities.ORIENTATION_OPTIONS.get(OrientationOptions.ORIENTATION_90);
}
