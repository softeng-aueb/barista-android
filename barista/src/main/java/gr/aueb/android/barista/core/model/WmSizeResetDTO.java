package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("WmSizeReset")
public class WmSizeResetDTO extends CommandDTO {

    public WmSizeResetDTO(String sessionToken){
        super(sessionToken);
    }

}
