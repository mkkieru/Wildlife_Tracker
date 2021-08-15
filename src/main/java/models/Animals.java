package models;

import java.util.List;

public class Animals {
    private Integer id ;
    private String name ;
    private String species;

    public Animals(String name, String Species){
        this.name = name;
        this.species = Species;

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
}
