package gr.aueb.android.barista.core.annotations;

import gr.aueb.android.barista.core.model.GeoFixDTO;
import gr.aueb.android.barista.core.model.WmDensityDTO;
import gr.aueb.android.barista.core.model.WmSizeDTO;

public class CommandDTODataHelper {

    public static final GeoFixDTO geoFixCommand = new GeoFixDTO(null,ConstantValues.lat,ConstantValues.longt);
    public static final WmSizeDTO sizeCommand  = new WmSizeDTO(null,ConstantValues.width,ConstantValues.height,false,null);



}
