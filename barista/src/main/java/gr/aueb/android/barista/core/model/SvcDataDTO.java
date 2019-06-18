package gr.aueb.android.barista.core.model;


import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("SvcData")
public class SvcDataDTO extends CommandDTO {
    private boolean enabled;


    public SvcDataDTO() {

    }

    public SvcDataDTO(String sessionToken, boolean enabled) {
        super(sessionToken);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString(){
        return "Data value: "+this.enabled;
    }

}
