import java.util.*;

import DAO.Sql2oAnimalsDao;
import DAO.Sql2oEndangeredAnimalsDao;
import DAO.Sql2oLocationDao;
import models.Animals;
import models.EndangeredAnimal;
import models.Location;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";
        Sql2o sql2o = new Sql2o(connectionString, "damark", "password");

        Sql2oAnimalsDao animalDao = new Sql2oAnimalsDao(sql2o);
        Sql2oEndangeredAnimalsDao endangeredAnimalDao = new Sql2oEndangeredAnimalsDao(sql2o);
        Sql2oLocationDao locationDao = new Sql2oLocationDao(sql2o);


        //display all animals on homepage
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Animals> animal = animalDao.getAll();
            model.put("animal", animal);

            return new ModelAndView(model, "Index.hbs");
        }, new HandlebarsTemplateEngine());

        //get form to add an animal (Also endangered ones)
        get("/animalForm", (request, response) -> {
            Map<String,Object> model = new HashMap<>();

            //INSERT INTO sightings (location) VALUES (:location)
            Location location = new Location("Near The River");
            Location location2 = new Location("Near The Village");
            Location location3 = new Location("Near The Cliff");
            Location location4 = new Location("Near The    ");
            Location location5 = new Location("Near The Village");

           //locationDao.add(location);
           //locationDao.add(location2);

            List<Location> locations = locationDao.getAll();
            model.put("locations", locations);

            return new ModelAndView(model, "Animal-Form.hbs");
        }, new HandlebarsTemplateEngine());

        //Success after adding a new animal to table animals
        get("/success", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/EndangeredAnimal", (request, response) -> {
            Map<String,Object> model = new HashMap<>();

            List<EndangeredAnimal> endangeredAnimal = endangeredAnimalDao.getAll();
            model.put("endangeredAnimal", endangeredAnimal);

            return new ModelAndView(model, "Endangered-Animals.hbs");
        }, new HandlebarsTemplateEngine());

        //Post form to add new animal
       post("/newAnimal", (request, response) -> {
           Map<String,Object> model = new HashMap<>();

           String name = request.queryParams("name");
           String species = request.queryParams("species");
           String stringLocationid = request.queryParams("locationId");

           Animals newAnimal = new Animals(name,species,stringLocationid);
           animalDao.add(newAnimal);

           response.redirect("/success");
           return null;
       }, new HandlebarsTemplateEngine());

       //post form to add new endangered animal
        post("/newEndangeredAnimal", (request, response) -> {
            Map<String,Object> model = new HashMap<>();

            String name = request.queryParams("name");
            String species = request.queryParams("species");
            String stringLocationid = request.queryParams("locationId");
            String status = request.queryParams("status");
            String health = request.queryParams("health");
            String age = request.queryParams("age");

            EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(name,species,status,health,age,stringLocationid);
            endangeredAnimalDao.add(newEndangeredAnimal);

            response.redirect("/EndangeredAnimal");
            return null;
        }, new HandlebarsTemplateEngine());

        //Delete all animal entries
        get("/clearTable",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            animalDao.clearAllAnimals();

            response.redirect("/success");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
