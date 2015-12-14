package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wouter on 14-12-2015.
 *
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
     * Gets the dishes of the menu.
     * @return Dishes of the menu.
     */
    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    /**
     * Sets the dishes of the menu
     * @param dishes The dishes to be set
     */
    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }
}
