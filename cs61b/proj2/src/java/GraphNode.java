/**
 * Created by cs61bl-cc on 8/3/16.
 */
public class GraphNode {

    private long id;
    private double lat;
    private double lon;
    private String name;

    public GraphNode(long id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name =null;
    }

    public GraphNode(long id, double lat, double lon, String name) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public long getid() {
        return this.id;
    }

    public double getlat() {
        return this.lat;
    }

    public double getlon() {
        return this.lon;
    }

    public String getname() {
        return this.name;
    }

    public void setId(long id) {


    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }
}

