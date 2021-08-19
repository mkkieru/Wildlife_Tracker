package dao;

import models.Animals;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Sql2oAnimalsDao implements DAO.AnimalsDao {

    private final Sql2o sql2o;

    public Sql2oAnimalsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Animals animal) {
        String sql = "INSERT INTO animals (name,species,status,health,age,locationid) VALUES (:name,:species,:status,:health,:age,:locationId)";
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(animal) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            animal.setId(id); //update object to set id now from database
            showMessageDialog(null, "Animal successfully added");
        } catch (Sql2oException ex) {
            showMessageDialog(null, "Animal NOT added");
        }
    }

    @Override
    public List<Animals> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals")//raw sql
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
