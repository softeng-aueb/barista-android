package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("WmDensity")
public class WmDensityDTO extends CommandDTO {

    private int density;

    public WmDensityDTO(String sessionToken, int density) {
        super(sessionToken);
        this.density = density;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    @Override
    public String toString(){
        return "Density: "+density;
    }
}
