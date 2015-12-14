package controller;

import model.Menu;

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

    }

    public void exportMenu() {

    }

    public Menu getMenu() {
        return menu;
    };

    public void setMenu(Menu menu) {
        this.menu = menu;
    };

}
