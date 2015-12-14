package controller;

import model.Dish;
import model.Menu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Wouter on 14-12-2015.
 *
 * This class controls the model as an Owner.
 */
public class Owner extends Controller {

    public Owner() {
        super();
    }

    public void addDish(Dish dish) {
        dish.setId(getNewId(getMenu()));
        getMenu().getDishes().add(dish);
    }

    public void editDish(Dish dish) {
        ArrayList<Dish> dishes = getMenu().getDishes();
        ArrayList<Dish> res = new ArrayList<>();
        Iterator<Dish> i = dishes.iterator();
        while (i.hasNext()) {
            Dish d = i.next();
            if (d.getId() == dish.getId()) {
                d.setDescription(dish.getDescription());
                d.setGrades(dish.getGrades());
                d.setImgpath(dish.getImgpath());
                d.setTitle(dish.getTitle());
            }
            res.add(d);
        }
        getMenu().setDishes(res);
    }

    public void deleteDish(Dish dish) {
        ArrayList<Dish> dishes = getMenu().getDishes();
        ArrayList<Dish> res = new ArrayList<>();
        Iterator<Dish> i = dishes.iterator();
        while (i.hasNext()) {
            Dish d = i.next();
            if (d.getId() != dish.getId()) {
                res.add(d);
            }
        }
        getMenu().setDishes(res);
    }

    private int getNewId(Menu menu) {
        int res = 0;
        ArrayList<Dish> dishes = menu.getDishes();
        Iterator<Dish> i = dishes.iterator();
        while(i.hasNext()) {
            int id = i.next().getId();
            if (id >= res) {
                res = id;
            }
        }
        return res + 1;
    }

}
