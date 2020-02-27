package gr.aueb.android.barista.core.model;

public class GpsStatusDTO extends CommandDTO {

    private boolean enabled;

    public GpsStatusDTO() {

    }

    public GpsStatusDTO(String sessionToken, boolean enabled) {
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
        return "Gps Command ["+enabled+"]";
    }

}
