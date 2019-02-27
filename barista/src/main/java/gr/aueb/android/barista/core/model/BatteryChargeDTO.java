package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("BatteryCharge")
public class BatteryChargeDTO extends CommandDTO {

    private boolean isPlugged;

    public BatteryChargeDTO(String sessionToken, boolean isPlugged) {
        super(sessionToken);
        this.isPlugged = isPlugged;
    }

    public boolean isPlugged() {
        return isPlugged;
    }

    public void setPlugged(boolean plugged) {
        isPlugged = plugged;
    }

    @Override
    public String toString(){
        return "Charging: "+isPlugged;
    }
}
