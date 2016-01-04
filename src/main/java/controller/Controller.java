package controller;

import model.Dish;
import model.Menu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Wouter on 14-12-2015.
 *
 * Super class for a controller.
 */
public class Controller {

    /**
     * This controller has a menu
     */
    private Menu menu;

    /**
     * Constructor of the controller.
     * It always imports the menu if the file menu.json exists.
     */
    public Controller() {
        try {
            importMenu();
        } catch (IOException|ParseException e) {
            this.menu = new Menu();
        }
    }

    /**
     * Imports the menu.
     */
    public void importMenu() throws IOException,ParseException {

        //Make a list of dishes
        ArrayList<Dish> dishes = new ArrayList<>();

        //Make a parser
        JSONParser parser = new JSONParser();

        //Parse the save file
        Object obj = parser.parse(new FileReader("menu.json"));

        //In the file is a menu
        JSONObject jsonMenu = (JSONObject) obj;

        //The menu contains an array of dishes
        JSONArray dishArray = (JSONArray) jsonMenu.get("dishes");

        //For every dish in the array of dishes
        for (Object dish : dishArray) {

            //The dish is an object
            JSONObject jsonDish = (JSONObject) dish;

            //The dish has an array of grades
            JSONArray jsonGrades = (JSONArray) jsonDish.get("grades");

            //Make a new list for the grades
            ArrayList<Integer> grades = new ArrayList<>();

            //For every grade in the array
            for (Object jsonGrade : jsonGrades) {

                //The grade is an integer, not a long
                Long longGrade = (Long) jsonGrade;
                Integer grade = longGrade.intValue();

                //Add it to the list of grades
                grades.add(grade);
            }

            //The dish has an array of comments
            JSONArray jsonComments = (JSONArray) jsonDish.get("comments");

            //Make a new list for the comments
            ArrayList<String> comments = new ArrayList<>();

            //For every comment in the array
            for (Object jsonComment : jsonComments) {

                //The comment is a string
                String comment = (String) jsonComment;

                //Add it to the list of comments
                comments.add(comment);
            }

            //We have a new Dish
            Dish d = new Dish(
                    (Long) jsonDish.get("id"),
                    (String) jsonDish.get("title"),
                    (String) jsonDish.get("description"),
                    grades,
                    comments,
                    (String) jsonDish.get("imgpath")
            );

            //Add our new dish
            dishes.add(d);
        }
        menu = new Menu(dishes);
    }

    /**
     * Exports the menu.
     */
    public void exportMenu() {

        //The menu is an object
        JSONObject menu = new JSONObject();

        //The menu contains an array of dishes
        JSONArray dishes = new JSONArray();

        //Get the dishes
        ArrayList<Dish> dishlist = getMenu().getDishes();

        //For every dish
        for (Dish d : dishlist) {

            //The dish is an object
            JSONObject dish = new JSONObject();

            //The dish has these fields
            dish.put("id", d.getId());
            dish.put("title", d.getTitle());
            dish.put("description", d.getDescription());
            dish.put("imgpath", d.getImgpath());

            //The dish has an array of grades
            JSONArray grades = new JSONArray();

            //The dish has an array of comments;
            JSONArray comments = new JSONArray();

            //For every grade
            for (int g : d.getGrades()) {

                //Add the grade to the array
                grades.add(g);
            }

            for (String c : d.getComments()) {

                //Add the comment to the array
                comments.add(c);
            }

            //The dish has these arrays
            dish.put("comments", comments);
            dish.put("grades", grades);

            //Add the dish to the list of dishes
            dishes.add(dish);
        }

        //Add the dishes to the menu
        menu.put("dishes", dishes);

        //Save the menu
        try {

            FileWriter file = new FileWriter("menu.json");
            file.write(menu.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
