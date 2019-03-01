package gr.aueb.android.barista.core.annotations;


import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.model.CommandDTO;

import static gr.aueb.android.barista.core.annotations.ConstantValues.wifiEnabledEnum;


public class DummyAnnotatedClass {


    @GeoFix(lat = ConstantValues.lat, longt = ConstantValues.longt)
    public void m1(){}

    @ScreenSize(width = ConstantValues.width, height = ConstantValues.height)
    public void m2(){}

    @Permission(type = ConstantValues.permission)
    public void m3(){

    }

    @Density(density = ConstantValues.density)
    public void m4(){

    }

    @BatteryOptions(plugged = ConstantValues.plugged, level = ConstantValues.baterryLevel)
    public void m5(){}

    @Wifi(enabled = NetworkAdapterStateType.DISABLED)
    public void m6(){

    }

    @Data(enabled = NetworkAdapterStateType.DISABLED)
    public void m7(){

    }



}
