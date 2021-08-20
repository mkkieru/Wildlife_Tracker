package models;

public class Location {
    private Integer id;
    private String location;

    public Location(String location ){
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() { return id; }
}
