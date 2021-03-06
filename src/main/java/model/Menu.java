package model;

import java.util.ArrayList;

/**
 * This class represents the menu of the restaurant.
 */
public class Menu {

    /**
     * The dishes on the menu.
     */
    private ArrayList<Dish> dishes;

    /**
     * Creates a new menu.
     */
    public Menu() {
        dishes = new ArrayList<>();
    }

    /**
     * Creates a new menu.
     *
     * @param dishes The dishes of the new Menu.
     */
    public Menu(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    /**
     * Gets the dishes of the menu.
     *
     * @return Dishes of the menu.
     */
    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    /**
     * Sets the dishes of the menu
     *
     * @param dishes The dishes to be set
     */
    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }
}
