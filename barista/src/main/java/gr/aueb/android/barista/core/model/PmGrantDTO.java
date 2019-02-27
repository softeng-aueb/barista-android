package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PmGrant")
public class PmGrantDTO extends CommandDTO {

    private String sessionToken;
    private String permission;

    public PmGrantDTO(String sessionToken, String permission){
        super(sessionToken);
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString(){
        return "Set Permission Command "+this.permission;
    }
}
