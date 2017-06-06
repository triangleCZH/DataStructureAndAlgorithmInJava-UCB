/**
 * Created by cs61bl-cc on 8/3/16.
 */
public class GraphNode {

    private long id;
    private long lat;
    private long lon;
    private String name;

    public GraphNode(long id, long lat, long lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name =null;
    }

    public GraphNode(long id, long lat, long lon, String name) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public long getid() {
        return this.id;
    }

    public long getlat() {
        return this.lat;
    }

    public long getlon() {
        return this.lon;
    }

    public String getname() {
        return this.name;
    }

    public void setId(long id) {


    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }
}

