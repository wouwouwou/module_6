import controller.Controller;
import controller.Program;
import model.Dish;

import java.util.ArrayList;

/**
 * Created by Wouter on 14-12-2015.
 *
 * Program starter class.
 */
public class Main {

    /**
     * Main method, program starter. Creates a controller.
     * @param args arguments given to the program
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        Dish d = new Dish(1, "Title", "Description", new ArrayList<Integer>(), new ArrayList<String>(), "steak.jpg");
        c.getMenu().getDishes().clear();
        c.getMenu().getDishes().add(d);
        c.exportMenu();
    }

}
