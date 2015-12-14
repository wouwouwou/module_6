package controller;

import model.Dish;
import java.util.ArrayList;

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
        for (Dish dish : dishes) {
            if (dish.getId() == dishid) {
                dish.getGrades().add(grade);
            }
            res.add(dish);
        }
        getMenu().setDishes(res);
    }

    public void gradeDish(Dish dish, int grade) {
        ArrayList<Dish> dishes = getMenu().getDishes();
        ArrayList<Dish> res = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getId() == dish.getId()) {
                d.getGrades().add(grade);
            }
            res.add(d);
        }
        getMenu().setDishes(res);
    }

}
