package gr.aueb.android.barista.core.annotations;


import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.annotations.enumarations.OrientationOptions;


public class DummyAnnotatedClass {


    @GeoFix(lat = ConstantValues.lat, longt = ConstantValues.longt)
    public void m1(){}

    @ScreenSize(width = ConstantValues.width, height = ConstantValues.height)
    public void m2(){}

    @Permission(value = ConstantValues.permission)
    public void m3(){

    }

    @Density(value = ConstantValues.density)
    public void m4(){

    }

    @BatteryOptions(plugged = ConstantValues.plugged, level = ConstantValues.baterryLevel)
    public void m5(){}

    @Wifi(value = NetworkAdapterStateType.DISABLED)
    public void m6(){

    }

    @Data(value = NetworkAdapterStateType.DISABLED)
    public void m7(){

    }

    @Orientation(value = OrientationOptions.ORIENTATION_90)
    public void m8() {

    }





}
