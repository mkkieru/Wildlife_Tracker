package DAO;

import models.Animals;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class Sql2oAnimalsDaoTest {

    private static Sql2oAnimalsDao animalsDao; //these variables are now static.
    private static Connection conn; //these variables are now static.

    @BeforeClass //changed to @BeforeClass (run once before running any tests in this file)
    public static void setUp() throws Exception { //changed to static
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "damark", "password"); // changed user and pass to null

        animalsDao = new Sql2oAnimalsDao(sql2o);
        conn = sql2o.open(); // open connection once before this test file is run
    }

    @After // run after every test
    public void tearDown() throws Exception { //I have changed
        System.out.println("clearing database");
        animalsDao.clearAllAnimals(); // clear all categories after every test
    }

    @AfterClass // changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception { //changed to static and shutDown
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addingAnimal(){
        Animals animal = setupNewAnimal();
        animalsDao.add(animal);
        List<Animals> newAnimal = animalsDao.getAll();
        assertTrue(newAnimal.contains(animal));
    }
    @Test
    public void addingAnimalSetsId() throws Exception {
        Animals animal = setupNewAnimal();
        int originalCategoryId = animal.getId();
        animalsDao.add(animal);
        assertNotEquals(java.util.Optional.of(originalCategoryId), animal.getId());
    }

    // helper method
    public Animals setupNewAnimal(){
        return new Animals("Mark","Human Being","2");
    }


}