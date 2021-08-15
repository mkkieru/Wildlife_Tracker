package models;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class EndangeredAnimalTest{

    @Test
    public void newEndangeredAnimal(){
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Mark","Human Being","endangered","Healthy","young");
        Assert.assertTrue(newEndangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void getName(){
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Mark","Human Being","endangered","Healthy","young");
        Assert.assertEquals("Mark", newEndangeredAnimal.getName());
    }
    @Test
    public void getStatus(){
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Mark","Human Being","endangered","Healthy","young");
        Assert.assertEquals("endangered", newEndangeredAnimal.getStatus());
    }
    @Test
    public void getSpecies(){
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Mark","Human Being","endangered","Healthy","young");
        Assert.assertEquals("Human Being", newEndangeredAnimal.getSpecies());
    }
    @Test
    public void getHealth(){
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Mark","Human Being","endangered","Healthy","young");
        Assert.assertEquals("Healthy", newEndangeredAnimal.getHealth());
    }
    @Test
    public void getAge(){
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Mark","Human Being","endangered","Healthy","young");
        Assert.assertEquals("young", newEndangeredAnimal.getAge());
    }

}