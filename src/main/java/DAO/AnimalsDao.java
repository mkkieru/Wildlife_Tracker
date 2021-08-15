package DAO;

import models.Animals;

import java.util.List;

public interface AnimalsDao{
    //LIST
    List<Animals> getAll();

    //CREATE
    void add (Animals animals);

    //READ
    Animals findById(int id);
    List<Animals> getAllTasksByCategory(int categoryId);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void clearAllAnimals();
}
