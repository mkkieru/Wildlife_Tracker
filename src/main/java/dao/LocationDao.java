package dao;

import models.Animals;
import models.Location;

import java.util.List;

public interface LocationDao {
    //LIST
    List<Location> getAll();

    //CREATE
    void add (Location location);

    //DELETE
    void deleteById(int id);
    void clearAllLocations();
    public void update(int id, String newName);
    public Location findById(int id);
}
