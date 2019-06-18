package gr.aueb.android.barista.core.model;


import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("WmDensityReset")
public class WmDensityResetDTO extends CommandDTO {
    public WmDensityResetDTO(String sessionToken){
        super(sessionToken);
    }
}
