package controller;

import model.Dish;
import model.Menu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
     * @param d the dish on the menu
     * @return the dish on the menu as JSONObject
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
     * Makes a JSONArray of the grades of a dish
     * @param d the dish
     * @return the grades as JSONArray
     */
    public static JSONArray gradesToJSONArray(Dish d) {
        JSONArray grades = new JSONArray();
        for (int g : d.getGrades()) {
            grades.add(g);
        }
        return grades;
    }

    /**
     * Makes a JSONArray of the comments on a dish
     * @param d the dish
     * @return the comments as JSONArray
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
     * @param filename the filename
     * @param json the JSONObject to be saved
     * @throws IOException
     */
    public static void saveJSON(String filename, JSONObject json) throws IOException {
        FileWriter file = new FileWriter(filename);
        file.write(json.toJSONString());
        file.flush();
        file.close();
    }

}
