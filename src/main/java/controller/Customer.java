package controller;

import model.Dish;

/**
 * This class controls the model as a Customer.
 */
public class Customer extends Controller {

    public Customer() {
        super();
    }

    public void gradeDish(Dish dish, String comment, int grade) {
        if (!"".equals(comment)) {
            dish.getComments().add(comment);
        }
        if (grade > 0) {
            dish.getGrades().add(grade);
        }
    }

}
