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
    public void getName(){
        Location newLocation = new Location("Near the river");
        Assert.assertEquals("Near the river", newLocation.getName());
    }

}