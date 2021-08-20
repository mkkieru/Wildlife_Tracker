package dao;

import models.Animals;
import models.EndangeredAnimal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Sql2oEndangeredAnimalsDao implements EndangeredAnimalsDao {

    private final Sql2o sql2o;

    public Sql2oEndangeredAnimalsDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public List<EndangeredAnimal> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals WHERE status = 'Endangered'")//raw sql
                    .executeAndFetch(EndangeredAnimal.class); //fetch a list
        }
    }
    @Override
    public void add(EndangeredAnimal EndangeredAnimalToBeAdded) {
        String sql = "INSERT INTO animals (name,species,status,health,age,locationid) VALUES (:name,:species,:status,:health,:age,:locationId)";
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(EndangeredAnimalToBeAdded) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            EndangeredAnimalToBeAdded.setId(id); //update object to set id now from database
            showMessageDialog(null, "Animal successfully added");
        } catch (Sql2oException ex) {
            showMessageDialog(null, "Animal NOT added");
        }
    }

    @Override
    public EndangeredAnimal findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
    }

    @Override
    public void update(int id, String newName){
        String sql = "UPDATE animals SET name = :name WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from animals WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

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
