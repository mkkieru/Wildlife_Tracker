package models;

import org.junit.Assert;
import org.junit.Test;

public class locationTest {

    @Test
    public void newLocationInstantiatesCorrectly(){
        Location newLocation = new Location("Near the river");
        Assert.assertTrue(newLocation instanceof Location);
    }
    @Test
    public void TestsetLocation(){
        Location newLocation = new Location("");
        newLocation.setLocation("Near the river");
        Assert.assertEquals("Near the river", newLocation.getLocation());
    }
    @Test
    public void getLocation(){
        Location newLocation = new Location("Near the river");
        Assert.assertEquals("Near the river", newLocation.getLocation());
    }
    @Test
    public void TestsetIdAndGetId(){
        Location newLocation = new Location("Near the river");
        newLocation.setId(2);
        Assert.assertTrue(2 == newLocation.getId());
    }
}