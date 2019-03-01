package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("SvcWifi")
public class SvcWifiDTO extends CommandDTO {

    private boolean enabled;

    public SvcWifiDTO() {
    }

    public SvcWifiDTO(String sessionToken, boolean enabled) {
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
        return "Wifi Enabled: "+this.enabled;
    }
}
