package gr.aueb.android.barista.core.annotations;


import gr.aueb.android.barista.core.model.CommandDTO;

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

    @BatteryOptions(plugged = ConstantValues.pluged, level = ConstantValues.baterryLevel)
    public void m5(){}

}
