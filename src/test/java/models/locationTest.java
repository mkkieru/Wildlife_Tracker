package models;

import org.junit.Assert;
import org.junit.Test;

public class locationTest {

    @Test
    public void newLocation(){
        Location newLocation = new Location("Near the river");
        Assert.assertTrue(newLocation instanceof Location);
    }
    @Test
    public void getLocation(){
        Location newLocation = new Location("Near the river");
        Assert.assertEquals("Near the river", newLocation.getLocation());
    }
    @Test
    public void getID(){
        Location newLocation = new Location("Near the river");
        Assert.assertTrue(0 == newLocation.getId());
    }


}