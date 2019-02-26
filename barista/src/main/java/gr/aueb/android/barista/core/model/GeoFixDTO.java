package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("GeoFix")
public class GeoFixDTO extends CommandDTO {

    private double latitude;
    private double longitude;

    public GeoFixDTO(String sessionToken, double latitude, double longitude) {
        super(sessionToken);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString(){
        return "Geofix Comand ["+latitude+","+longitude+"]";
    }

}
