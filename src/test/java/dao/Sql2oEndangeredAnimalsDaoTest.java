package dao;

import junit.framework.TestCase;
import models.Animals;
import models.EndangeredAnimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oEndangeredAnimalsDaoTest {

    private static Sql2oEndangeredAnimalsDao endangeredAnimalsDao; //these variables are now static.
    private static Connection conn; //these variables are now static.

    @BeforeClass
    public static void setUp() throws Exception {
        //String connectionString = " jdbc:postgresql://ec2-54-205-232-84.compute-1.amazonaws.com:5432/d9hu4q43c1k12s"; // connect to postgres test database
        //Sql2o sql2o = new Sql2o(connectionString, "sfwbxkfwtrikhm", "5bbc9ba274f227c6268d56b2359f86e26fbef68006fa1ca864304acddadf4e74");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, "damark", "password");

        endangeredAnimalsDao = new Sql2oEndangeredAnimalsDao(sql2o);
        conn=sql2o.open();
        System.out.println("connection established");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing databases");
        endangeredAnimalsDao.clearAllAnimals();
    }

    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }
    @Test
    public void addingAnimalSetsId() throws Exception {
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("name","species","status","health","age","stringLocationid");
        Integer originalCategoryId = newEndangeredAnimal.getId();
        endangeredAnimalsDao.add(newEndangeredAnimal);
        assertNotEquals(originalCategoryId, newEndangeredAnimal.getId());
    }
    @Test
    public void addedAnimalsAreReturnedFromGetAll() throws Exception {
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("name","species","Endangered","health","age","stringLocationid");
        endangeredAnimalsDao.add(newEndangeredAnimal);
        assertEquals(1, endangeredAnimalsDao.getAll().size());
    }
    @Test
    public void noAnimalsReturnsEmptyList() throws Exception {
        assertEquals(0, endangeredAnimalsDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectAnimal() throws Exception {
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("name","species","Endangered","health","age","stringLocationid");
        endangeredAnimalsDao.add(newEndangeredAnimal);
        endangeredAnimalsDao.deleteById(newEndangeredAnimal.getId());
        assertEquals(0, endangeredAnimalsDao.getAll().size());
    }
    @Test
    public void clearAllAnimalsDeletesAllAnimals() throws Exception {
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("name","species","Endangered","health","age","stringLocationid");
        endangeredAnimalsDao.add(newEndangeredAnimal);
        EndangeredAnimal newEndangeredAnimal2 = new EndangeredAnimal("name","species","Endangered","health","age","stringLocationid");
        endangeredAnimalsDao.add(newEndangeredAnimal2);

        endangeredAnimalsDao.clearAllAnimals();

        assertEquals(0, endangeredAnimalsDao.getAll().size());
    }
    @Test
    public void updateChangesAnimalsContent() throws Exception {
        String initialDescription = "name";
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(initialDescription,"species","Endangered","health","age","stringLocationid");
        endangeredAnimalsDao.add(newEndangeredAnimal);
        endangeredAnimalsDao.update(newEndangeredAnimal.getId(),"Cleaning");

        EndangeredAnimal updatedAnimal = endangeredAnimalsDao.findById(newEndangeredAnimal.getId());
        assertNotEquals(initialDescription, updatedAnimal.getName());
    }



}