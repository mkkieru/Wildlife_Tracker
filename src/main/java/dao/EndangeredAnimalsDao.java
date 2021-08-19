package DAO;

import models.EndangeredAnimal;

import java.util.List;

public interface EndangeredAnimalsDao {

    //LIST
    List<EndangeredAnimal> getAll();

    //CREATE
    void add (EndangeredAnimal animal);

    //READ
    EndangeredAnimal findById(int id);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void clearAllCategories();
}
