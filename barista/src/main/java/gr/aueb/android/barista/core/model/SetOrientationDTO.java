package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "SetOrientation")
public class SetOrientationDTO extends CommandDTO{
    private int orientation;

    public SetOrientationDTO(){

    }

    public SetOrientationDTO(String sessionToken , int orientation){
        super(sessionToken);
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString(){
          return "Orientation set to : "+this.orientation;
    }
}
