package models;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class AnimalsTest {

    @Test
    public void newAnimal_InstatiatesANewAnimal_true(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        Assert.assertTrue(newAnimal instanceof Animals);
    }

    @Test
    public void setNameSetsCorrectly(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        newAnimal.setSpecies("mark");
        Assert.assertEquals("mark", newAnimal.getSpecies());
    }
    @Test
    public void setSpeciesSetsCorrectly(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        newAnimal.setSpecies("human");
        Assert.assertEquals("human", newAnimal.getSpecies());
    }
    @Test
    public void setNameLocationIdCorrectly(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        newAnimal.setLocationId("2");
        Assert.assertEquals("2", newAnimal.getLocationId());
    }
    /*@Test
    public void setAgeSetsCorrectly(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        newAnimal.setAge("child");
        Assert.assertEquals("child", newAnimal.getAge());
    }
    @Test
    public void sethealthSetsCorrectly(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        newAnimal.setHealth("sick");*/
        Assert.assertEquals("sick", newAnimal.getHealth());
    }
    @Test
    public void setStatusSetsCorrectly(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        newAnimal.setStatus("endangered");
        Assert.assertEquals("endangered", newAnimal.getStatus());
    }
    @Test
    public void getName(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        Assert.assertEquals("Mark", newAnimal.getName());
    }
    @Test
    public void getLocationid(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        Assert.assertTrue("2"==newAnimal.getLocationId());
    }

    @Test
    public void getSpecies(){
        Animals newAnimal = new Animals("Mark","Human Being","2");
        Assert.assertEquals("Human Being", newAnimal.getSpecies());
    }
}