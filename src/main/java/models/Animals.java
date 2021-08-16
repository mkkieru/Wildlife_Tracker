package models;

import java.util.List;

public class Animals {
    private Integer id ;
    private String name ;
    private String species;
    private String locationId;
    private String status = "Not Endangered";
    private String health = "Not indicated";
    private String age = "Not indicated";

    public Animals(String name, String Species, String locationId){
        this.name = name;
        this.species = Species;
        this.locationId = locationId;

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

    public String getLocationId() { return locationId; }

    public String getStatus() { return status; }

    public String getHealth() { return health; }

    public String getAge() {  return age; }
}
