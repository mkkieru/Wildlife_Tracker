package DAO;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Sql2oLocationDao implements LocationDao {

    private final Sql2o sql2o;

    public Sql2oLocationDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public List<Location> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings")//raw sql
                    .executeAndFetch(Location.class); //fetch a list
        }
    }

    @Override
    public void add(Location location) {

        String sql = "INSERT INTO sightings (location) VALUES (:location)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(location) //use name and location from match object for sql
                    .executeUpdate()
                    .getKey();
            location.setId(id);
            showMessageDialog(null, "Location successfully added");
        } catch (Sql2oException ex) {
            showMessageDialog(null, "Location NOT added");
        }

    }
    @Override
    public void clearAllAnimals() {
        String sql = "DELETE from sightings";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
