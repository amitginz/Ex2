import api.GeoLocation;
public class geoLocation implements GeoLocation {
    private double x;
    private double y;
    private double z;
    public geoLocation(double x, double y , double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set_x(double x){
        this.x = x;
    }

    public void set_y(double x){
        this.y = y;
    }

    public void set_z(double x){
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow((this.x-g.x()),2) + Math.pow((this.y-g.y()),2) + Math.pow((this.z-g.z()),2));
    }
}
