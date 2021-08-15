package models;

public class Location {
    private Integer id;
    private String name;

    public Location(String name ){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
