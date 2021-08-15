package models;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class AnimalsTest {

    @Test
    public void newAnimal_InstatiatesANewAnimal_true(){
        Animals newAnimal = new Animals("Mark","Human Being");
        Assert.assertTrue(newAnimal instanceof Animals);
    }

    @Test
    public void getName(){
        Animals newAnimal = new Animals("Mark","Human Being");
        Assert.assertEquals("Mark", newAnimal.getName());
    }

    @Test
    public void getSpecies(){
        Animals newAnimal = new Animals("Mark","Human Being");
        Assert.assertEquals("Human Being", newAnimal.getSpecies());
    }
}