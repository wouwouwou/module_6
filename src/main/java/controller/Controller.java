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
        menu = JSONTools.parseMenu("menu.json");
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
