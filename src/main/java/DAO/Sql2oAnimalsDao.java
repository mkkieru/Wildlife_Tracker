package DAO;

import models.Animals;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Sql2oAnimalsDao implements AnimalsDao {

    private final Sql2o sql2o;

    public Sql2oAnimalsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Animals animal) {
        String sql = "INSERT INTO animals (name, species,status) VALUES (:name, :species,'Not endangered')";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(animal) //use name and location from match object for sql
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
            showMessageDialog(null, "Animal successfully added");
        } catch (Sql2oException ex) {
            showMessageDialog(null, "Animal NOT added");
        }
    }

    @Override
    public List<Animals> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT name, species FROM animals WHERE status = 'Not endangered'")//raw sql
                    .executeAndFetch(Animals.class); //fetch a list
        }
    }

    @Override
    public Animals findById(int id) {
        return null;
    }

    @Override
    public List<Animals> getAllTasksByCategory(int categoryId) {
        return null;
    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllAnimals() {
        String sql = "DELETE from animals";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
