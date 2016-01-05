package controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
     * It always imports the menu if the file menu.json exists and if the
     * file can be parsed. Otherwise makes a new menu.
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
     * Throws an IOException if there is a problem reading the file
     * Throws an ParseException if the file can not be parsed.
     * @throws IOException
     * @throws ParseException
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
     * Exports the menu as JSON to the file "menu.json".
     */
    public void exportMenu() throws IOException {
        JSONObject menu = new JSONObject();
        JSONArray dishes = JSONTools.dishesToJSONArray(this.menu);
        menu.put("dishes", dishes);
        JSONTools.saveJSON("menu.json", menu);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
