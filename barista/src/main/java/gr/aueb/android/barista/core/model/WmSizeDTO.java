package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("WmSize")
public class WmSizeDTO extends CommandDTO{

    int width;
    int height;
    boolean reset;
    String unit;

    public WmSizeDTO(String sessionToken, int width, int height, boolean reset, String unit) {
        super(sessionToken);
        this.width = width;
        this.height = height;
        this.reset = reset;
        this.unit = unit;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString(){
        return "Set Size Comand [ w:"+width+", h:"+height+"]";
    }
}
