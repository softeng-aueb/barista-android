package gr.aueb.android.barista.core.annotations;

import gr.aueb.android.barista.core.model.BatteryChargeDTO;
import gr.aueb.android.barista.core.model.BatteryLevelDTO;
import gr.aueb.android.barista.core.model.GeoFixDTO;
import gr.aueb.android.barista.core.model.PmGrantDTO;
import gr.aueb.android.barista.core.model.PmRevokeDTO;
import gr.aueb.android.barista.core.model.SetOrientationDTO;
import gr.aueb.android.barista.core.model.SvcDataDTO;
import gr.aueb.android.barista.core.model.SvcWifiDTO;
import gr.aueb.android.barista.core.model.WmDensityDTO;
import gr.aueb.android.barista.core.model.WmSizeDTO;

/**
 * Some static objects to help asserting with constructed commands
 */
public class CommandDTODataHelper {

    public static final GeoFixDTO geoFixCommand = new GeoFixDTO(null,ConstantValues.lat,ConstantValues.longt);
    public static final WmSizeDTO sizeCommand  = new WmSizeDTO(null,ConstantValues.width,ConstantValues.height,false,null);
    public static final PmGrantDTO permissionCommand = new PmGrantDTO(null, ConstantValues.permission);
    public static final PmRevokeDTO revokeCommand = new PmRevokeDTO(null, ConstantValues.permission);

    public static final WmDensityDTO densityCommand = new WmDensityDTO(null,ConstantValues.density);
    public static final BatteryChargeDTO batteryChargeCommand = new BatteryChargeDTO(null, ConstantValues.plugged);
    public static final BatteryLevelDTO batteryLevelCommand = new BatteryLevelDTO(null,ConstantValues.baterryLevel);
    public static final SvcDataDTO dataCommand = new SvcDataDTO(null, ConstantValues.dataEnabled);
    public static final SvcWifiDTO wifiCommand = new SvcWifiDTO(null, ConstantValues.wifiEnabled);
    public static final SetOrientationDTO orientationComnnand = new SetOrientationDTO(null, ConstantValues.orientationLandscape);



}
