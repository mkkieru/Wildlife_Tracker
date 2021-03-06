package dao;

import models.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

//import org.junit.After;
//import org.junit.Test;

public class Sql2oLocationDaoTest{
    private static Sql2oLocationDao locationDao; //these variables are now static.
    private static Connection conn; //these variables are now static.

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, "damark", "password");

        locationDao = new Sql2oLocationDao(sql2o);
        conn = sql2o.open();
        System.out.println("connection established");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing databases");
        locationDao.clearAllLocations();
    }

    @AfterClass // changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception { //changed to static and shutDown
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }
    @Test
    public void addingLocationSetsId() throws Exception {
        Location location5 = new Location("Near The Village");
        Integer originalCategoryId = location5.getId();
        locationDao.add(location5);
        assertNotEquals(originalCategoryId, location5.getId());
    }

    @Test
    public void addedLocationsAreReturnedFromGetAll() {
        Location location5 = new Location("Near The Village");
        locationDao.add(location5);
        assertEquals(1, locationDao.getAll().size());
    }
    @Test
    public void noLocationReturnsEmptyList() throws Exception {
        assertEquals(0, locationDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectAnimal() throws Exception {
        Location location5 = new Location("Near The Village");
        locationDao.add(location5);
        locationDao.deleteById(location5.getId());
        assertEquals(0, locationDao.getAll().size());
    }
    @Test
    public void clearAllLocationsDeletesAllAnimals() throws Exception {
        Location location5 = new Location("Near The Village");
        locationDao.add(location5);
        Location location = new Location("Near The Village");
        locationDao.add(location);

        locationDao.clearAllLocations();

        assertEquals(0, locationDao.getAll().size());
    }
    @Test
    public void updateChangesLocationContent() throws Exception {
        String initialDescription = "village";
        Location location = new Location(initialDescription);
        locationDao.add(location);
        locationDao.update(location.getId(),"Cleaning");
        Location updatedLocation = locationDao.findById(location.getId());

        assertNotEquals(initialDescription, updatedLocation.getLocation());
    }

}