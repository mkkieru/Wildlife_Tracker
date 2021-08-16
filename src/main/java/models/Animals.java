package models;

import java.util.List;

public class Animals {
    private Integer id ;
    private String name ;
    private String species;
    private String locationid;

    public Animals(String name, String Species, String locationid){
        this.name = name;
        this.species = Species;
        this.locationid = locationid;

    }

    public <T> Animals(List<T> executeAndFetch) {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getSpecies() { return species; }

    public String getLocationid() {
        return locationid;
    }
}
