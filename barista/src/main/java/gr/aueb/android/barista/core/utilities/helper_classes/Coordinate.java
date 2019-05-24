package gr.aueb.android.barista.core.utilities.helper_classes;

public class Coordinate {

    double longtitude;
    double lattitutde;

    public Coordinate(double lattitutde, double longtitude) {
        this.longtitude = longtitude;
        this.lattitutde = lattitutde;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLattitutde() {
        return lattitutde;
    }

    public void setLattitutde(double lattitutde) {
        this.lattitutde = lattitutde;
    }

    @Override
    public String toString(){
        return "lat:"+this.lattitutde+" long:"+this.longtitude;
    }
}
