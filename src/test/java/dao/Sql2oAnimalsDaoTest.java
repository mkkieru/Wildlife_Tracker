package dao;

import junit.framework.TestCase;
import models.Animals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.sql.Connection;

import static junit.framework.TestCase.assertEquals;

public class Sql2oAnimalsDaoTest{


    private static Sql2oAnimalsDao animalsDao; //these variables are now static.
    private static Connection conn; //these variables are now static.

    @BeforeClass
    public static void setUp() throws Exception {
        //String connectionString = " jdbc:postgresql://ec2-54-205-232-84.compute-1.amazonaws.com:5432/d9hu4q43c1k12s"; // connect to postgres test database
        //Sql2o sql2o = new Sql2o(connectionString, "sfwbxkfwtrikhm", "5bbc9ba274f227c6268d56b2359f86e26fbef68006fa1ca864304acddadf4e74");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, "damark", "password");

        animalsDao = new Sql2oAnimalsDao(sql2o);
        System.out.println("connection established");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing databases");
        animalsDao.clearAllAnimals();
    }

    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addedAnimalsAreReturnedFromGetAll() throws Exception {
        Animals animal = new Animals("Scooby","Dog","2");
        animalsDao.add(animal);
        assertEquals(1, animalsDao.getAll().size());
    }

}