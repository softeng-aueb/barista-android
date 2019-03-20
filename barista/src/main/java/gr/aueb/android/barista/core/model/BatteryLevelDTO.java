package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("BatteryLevel")
public class BatteryLevelDTO extends CommandDTO {

    private int level;


    public BatteryLevelDTO(String sessionToken, int level) {
        super(sessionToken);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString(){
        return "Batery-Level:"+level;
    }


}
