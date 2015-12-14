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

    private Menu menu;

    public Controller() {
        importMenu();
    }

    public void importMenu() {

        ArrayList<Dish> dishes = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("c:\\test.json"));
            JSONObject jsonMenu = (JSONObject) obj;
            JSONArray dishArray = (JSONArray) jsonMenu.get("dishes");

            for (Object dish : dishArray) {

                JSONObject jsonDish = (JSONObject) dish;

                JSONArray jsonGrades = (JSONArray) jsonDish.get("grades");
                ArrayList<Integer> grades = new ArrayList<>();

                for (Object jsonGrade : jsonGrades) {
                    Integer grade = (Integer) jsonGrade;
                    grades.add(grade);
                }

                Dish d = new Dish(
                        (Integer) jsonDish.get("id"),
                        (String) jsonDish.get("title"),
                        (String) jsonDish.get("description"),
                        grades,
                        (String) jsonDish.get("imgpath")
                );

                dishes.add(d);
            }
        } catch (IOException|ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        menu = new Menu(dishes);
    }

    public void exportMenu() {
        JSONObject menu = new JSONObject();

        JSONArray dishes = new JSONArray();

        ArrayList<Dish> dishlist = getMenu().getDishes();
        for (Dish d : dishlist) {

            JSONObject dish = new JSONObject();

            dish.put("id", d.getId());
            dish.put("title", d.getTitle());
            dish.put("description", d.getDescription());
            dish.put("imgpath", d.getImgpath());

            JSONArray grades = new JSONArray();

            for (int g : d.getGrades()) {
                grades.add(g);
            }

            dish.put("grades", grades);

            dishes.add(dish);
        }

        menu.put("dishes", dishes);

        try {

            FileWriter file = new FileWriter("c:\\test.json");
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
