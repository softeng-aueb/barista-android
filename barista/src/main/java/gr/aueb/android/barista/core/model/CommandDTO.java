package gr.aueb.android.barista.core.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *  The mapping of model objects to JSON is handled by Jackson framework.
 *  Each JSON that represents a command contains a key named 'type' that contains
 *  the type of the command. This key is used by the server to be converted back to model object.
 *
 *  Bellow is the mapping strategy that is followed for the conversion of each command to JSON
 *
 * @param <T>
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GeoFixDTO.class),
        @JsonSubTypes.Type(value = WmSizeDTO.class),
        @JsonSubTypes.Type(value = WmSizeResetDTO.class),
        @JsonSubTypes.Type(value = WmDensityDTO.class),
        @JsonSubTypes.Type(value = PmGrantDTO.class),
        @JsonSubTypes.Type(value = WmDensityDTO.class),
        @JsonSubTypes.Type(value = WmDensityResetDTO.class),
        @JsonSubTypes.Type(value = BatteryLevelDTO.class),
        @JsonSubTypes.Type(value = BatteryChargeDTO.class),
        @JsonSubTypes.Type(value = SvcWifiDTO.class),
        @JsonSubTypes.Type(value = SvcDataDTO.class)
})
public abstract class CommandDTO <T extends Command> {


    public CommandDTO(){

    }

    private String sessionToken;
    private int delay;
    private CommandDTO resetCommand;

    public CommandDTO(String sessionToken) {
        this.sessionToken = sessionToken;
        this.resetCommand = null;
        this.delay = 0;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @JsonIgnore
    //todo decouble reset command feature from model
    public CommandDTO getResetCommand() {return this.resetCommand;}

    public void setResetCommand( CommandDTO resetCommand ) { this.resetCommand = resetCommand; }

    public int getDelay(){
        return this.delay;
    }

    public void setDelay(int delay){
        this.delay = delay;
    }


}
