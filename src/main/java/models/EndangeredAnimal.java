package models;

import models.Animals;

public class EndangeredAnimal extends Animals{

    private final String status;
    private final String health;
    private final String age;

    public EndangeredAnimal(String name, String Species,String status, String health, String age,String locationId) {
        super(name,Species,locationId);
        this.status = status;
        this.age = age;
        this.health = health;
    }

    public String getAge() {
        return age;
    }

    public String getHealth() {
        return health;
    }

    public String getStatus() {
        return status;
    }
}
