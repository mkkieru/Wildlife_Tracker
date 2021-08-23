import java.util.*;

import dao.Sql2oAnimalsDao;
import dao.Sql2oEndangeredAnimalsDao;
import dao.Sql2oLocationDao;
import models.Animals;
import models.EndangeredAnimal;
import models.Location;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
}
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        //postgres://zhbxtovcqwapss:750a5c4e892198abadb172a759d02b25e852ba5f50621721a90b449ee494b41d@ec2-54-156-60-12.compute-1.amazonaws.com:5432/dfq8hbhqfn0d1r

        //String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";
        //Sql2o sql2o = new Sql2o(connectionString, "damark", "password");

        String connectionString = "jdbc:postgresql://ec2-54-156-60-12.compute-1.amazonaws.com:5432/dfq8hbhqfn0d1r";
        Sql2o sql2o = new Sql2o(connectionString, "zhbxtovcqwapss", "750a5c4e892198abadb172a759d02b25e852ba5f50621721a90b449ee494b41d");

        Sql2oAnimalsDao animalDao = new Sql2oAnimalsDao(sql2o);
        Sql2oEndangeredAnimalsDao endangeredAnimalDao = new Sql2oEndangeredAnimalsDao(sql2o);
        Sql2oLocationDao locationDao = new Sql2oLocationDao(sql2o);


        //display all animals on homepage
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Animals> animal = animalDao.getAll();
            model.put("animal", animal);

            List<Location> location = locationDao.getAll();
            model.put("location", location);

            return new ModelAndView(model, "Index.hbs");
        }, new HandlebarsTemplateEngine());

        //get form to add an animal (Also endangered ones)
        get("/animalForm", (request, response) -> {
            Map<String,Object> model = new HashMap<>();

            //INSERT INTO sightings (location) VALUES (:location)
            List<Location> locations = locationDao.getAll();
            if (locations.size()==0){
                Location location = new Location("Near The River");
                Location location2 = new Location("Near The Village");
                Location location3 = new Location("Near The Cliff");
                Location location4 = new Location("Near The Camping Area");
                Location location5 = new Location("Near The Village");

               locationDao.add(location);
               locationDao.add(location2);
               locationDao.add(location3);
               locationDao.add(location4);
               locationDao.add(location5);
            }
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

        get("/animals/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimalToDelete = Integer.parseInt(req.params("id"));
            animalDao.deleteById(idOfAnimalToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
