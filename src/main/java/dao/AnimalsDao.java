package dao;

import models.Animals;

import java.util.List;

public interface AnimalsDao{
    //LIST
    List<Animals> getAll();

    //CREATE
    void add (Animals animals);

    //READ
    Animals findById(int id);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void clearAllAnimals();
}
