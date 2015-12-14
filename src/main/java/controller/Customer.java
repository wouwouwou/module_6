package controller;

import model.Dish;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Wouter on 14-12-2015.
 *
 * This class controls the model as a Customer.
 */
public class Customer extends Controller {

    public Customer() {
        super();
    }

    public void gradeDish(int dishid, int grade) {
        ArrayList<Dish> dishes = getMenu().getDishes();
        ArrayList<Dish> res = new ArrayList<>();
        Iterator<Dish> i = dishes.iterator();
        while (i.hasNext()) {
            Dish dish = i.next();
            if(dish.getId() == dishid) {
                dish.getGrades().add(grade);
            }
            res.add(dish);
        }
        getMenu().setDishes(res);
    }

    public void gradeDish(Dish dish, int grade) {
        ArrayList<Dish> dishes = getMenu().getDishes();
        ArrayList<Dish> res = new ArrayList<>();
        Iterator<Dish> i = dishes.iterator();
        while (i.hasNext()) {
            Dish d = i.next();
            if(d.getId() == dish.getId()) {
                d.getGrades().add(grade);
            }
            res.add(d);
        }
        getMenu().setDishes(res);
    }

}
