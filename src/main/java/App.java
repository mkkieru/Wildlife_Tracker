import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.Sql2oAnimalsDao;
import DAO.Sql2oEndangeredAnimalsDao;
import models.Animals;
import models.EndangeredAnimal;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";      //connect to todolist, not todolist_test!
        Sql2o sql2o = new Sql2o(connectionString, "damark", "password");

        Sql2oAnimalsDao animalDao = new Sql2oAnimalsDao(sql2o);
        Sql2oEndangeredAnimalsDao endangeredAnimalDao = new Sql2oEndangeredAnimalsDao(sql2o);

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
            return new ModelAndView("model", "Animal-Form.hbs");
        }, new HandlebarsTemplateEngine());

        //display after adding a new animal to table animals
        get("/success", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView("model", "success.hbs");
        }, new HandlebarsTemplateEngine());

        //Post form to add new animal
       post("/newAnimal", (request, response) -> {
           Map<String,Object> model = new HashMap<>();

           String name = request.queryParams("name");
           String species = request.queryParams("species");

           Animals animal = new Animals(name, species);
           animalDao.add(animal);

           response.redirect("/success");
           return null;
       }, new HandlebarsTemplateEngine());

       //post form to add new endangered animal
        post("/newEndangeredAnimal", (request, response) -> {
            Map<String,Object> model = new HashMap<>();

            String name = request.queryParams("name");
            String species = request.queryParams("species");
            String status = request.queryParams("status");
            String health = request.queryParams("health");
            String age = request.queryParams("age");

            EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name,species,status,health,age);
            endangeredAnimalDao.add(endangeredAnimal);

            response.redirect("/success");
            return null;
        }, new HandlebarsTemplateEngine());

       //clear animals database table
        post("/ClearAllAnimals", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            animalDao.clearAllAnimals();
            response.redirect("/success");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
