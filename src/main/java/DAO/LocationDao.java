package DAO;

import models.Animals;
import models.Location;

import java.util.List;

public interface LocationDao {
    //LIST
    List<Location> getAll();

    //CREATE
    void add (Location location);
}
