package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PmRevoke")
public class PmRevokeDTO extends CommandDTO {

    private String sessionToken;
    private String permission;

    public PmRevokeDTO(String sessionToken, String permission){
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
        return "Revoke Permission "+this.permission;
    }
}
