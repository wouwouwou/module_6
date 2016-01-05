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
 * Created by Wouter on 5-1-2016.
 *
 * Tools for JSON
 */
public class JSONTools {

    /**
     * Makes a JSONArray of all the dishes on the menu.
     * @return JSONArray of all the dishes on the menu.
     */
    public static JSONArray dishesToJSONArray(Menu menu) {
        JSONArray dishes = new JSONArray();
        ArrayList<Dish> dishlist = menu.getDishes();
        for (Dish d : dishlist) {
            JSONObject dish = dishToJSONObject(d);
            dishes.add(dish);
        }
        return dishes;
    }

    /**
     * Makes a JSONObject of a dish on the menu.
     * @param d The dish on the menu.
     * @return The dish on the menu as JSONObject.
     */
    public static JSONObject dishToJSONObject(Dish d) {
        JSONObject dish = new JSONObject();
        dish.put("id", d.getId());
        dish.put("title", d.getTitle());
        dish.put("description", d.getDescription());
        dish.put("imgpath", d.getImgpath());
        dish.put("comments", commentsToJSONArray(d));
        dish.put("grades", gradesToJSONArray(d));
        return dish;
    }

    /**
     * Makes a JSONArray of the grades of a dish.
     * @param d The dish.
     * @return The grades as JSONArray.
     */
    public static JSONArray gradesToJSONArray(Dish d) {
        JSONArray grades = new JSONArray();
        for (int g : d.getGrades()) {
            grades.add(g);
        }
        return grades;
    }

    /**
     * Makes a JSONArray of the comments on a dish.
     * @param d The dish.
     * @return The comments as JSONArray.
     */
    public static JSONArray commentsToJSONArray(Dish d) {
        JSONArray comments = new JSONArray();
        for (String c : d.getComments()) {
            comments.add(c);
        }
        return comments;
    }

    /**
     * Saves a JSONObject to a given filename.
     * @param filename The filename.
     * @param json The JSONObject to be saved.
     * @throws IOException
     */
    public static void saveJSON(String filename, JSONObject json) throws IOException {
        FileWriter file = new FileWriter(filename);
        file.write(json.toJSONString());
        file.flush();
        file.close();
    }

    /**
     * Parses a menu of a given json file.
     * @param filename The json file.
     * @return The Menu.
     * @throws IOException
     * @throws ParseException
     */
    public static Menu parseMenu(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject jsonMenu = (JSONObject) obj;
        return new Menu(parseDishes(jsonMenu));
    }

    /**
     * Parses an ArrayList of the dishes from the menu in JSON.
     * @param jsonMenu The menu in JSON.
     * @return ArrayList of dishes on the menu.
     */
    public static ArrayList<Dish> parseDishes(JSONObject jsonMenu) {
        ArrayList<Dish> dishes = new ArrayList<>();
        JSONArray dishArray = (JSONArray) jsonMenu.get("dishes");
        for (Object dish : dishArray) {
            Dish d = parseDish((JSONObject)dish);
            dishes.add(d);
        }
        return dishes;
    }

    /**
     * Parses a dish from a JSONObject Dish.
     * @param dish The JSONObject to parse.
     * @return The actual Dish.
     */
    private static Dish parseDish(JSONObject dish) {
        return new Dish(
                (Long) dish.get("id"),
                (String) dish.get("title"),
                (String) dish.get("description"),
                parseGrades((JSONArray) dish.get("grades")),
                parseComments((JSONArray) dish.get("comments")),
                (String) dish.get("imgpath")
        );
    }

    /**
     * Parses the grades of a dish.
     * @param jsonGrades The JSONArray of grades.
     * @return The ArrayList of grades.
     */
    private static ArrayList<Integer> parseGrades(JSONArray jsonGrades) {
        ArrayList<Integer> grades = new ArrayList<>();
        for (Object jsonGrade : jsonGrades) {
            Integer grade = ((Long)jsonGrade).intValue();
            grades.add(grade);
        }
        return grades;
    }

    /**
     * Parses the comments of a dish.
     * @param jsonComments The JSONArray of comments.
     * @return The ArrayList of comments.
     */
    private static ArrayList<String> parseComments(JSONArray jsonComments) {
        ArrayList<String> comments = new ArrayList<>();
        for (Object jsonComment : jsonComments) {
            String comment = (String) jsonComment;
            comments.add(comment);
        }
        return comments;
    }
}