import controller.Controller;
import model.Dish;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Wouter on 6-1-2016.
 */
public class Main {

    public static void main(String[] args) {
        Controller c = new Controller();
        c.getMenu().getDishes().clear();
        Dish d1 = new Dish(1, "Baked Saithe Fillet", "Baked Saithe Fillet with a tartar sauce, carrots and French fries.", new ArrayList<Integer>(), new ArrayList<String>(), "fish.jpg");
        Dish d2 = new Dish(1, "Belgian Beef Stew", "Belgian beef stew with ratatoulle and potato gratin.", new ArrayList<Integer>(), new ArrayList<String>(), "stew.jpg");
        Dish d3 = new Dish(1, "Mozzarella Pie", "Mozzarella pie with Hollandaise sauce, carrots and fries.", new ArrayList<Integer>(), new ArrayList<String>(), "pie.jpg");
        c.getMenu().getDishes().add(d1);
        c.getMenu().getDishes().add(d2);
        c.getMenu().getDishes().add(d3);
        try {
            c.exportMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
