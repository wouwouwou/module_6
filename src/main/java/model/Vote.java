package model;

/**
 * Created by Wouter on 8-12-2015.
 *
 * This class represents a vote.
 */
public class Vote {

    /**
     * The dish which gets the vote.
     */
    private Dish dish;

    /**
     * The grade given to the dish.
     */
    private double grade;

    /**
     * Constructor of a vote.
     * @param dish The dish which gets the vote.
     * @param grade The grade given to the dish.
     */
    public Vote(Dish dish, double grade) {
        this.dish = dish;
        this.grade = grade;
    }

    /**
     * Dish getter.
     * @return The dish which is voted on.
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Dish setter.
     * @param dish The dish which is voted on.
     */
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    /**
     * Grade getter.
     * @return The grade given to the dish.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Grade setter.
     * @param grade The grade given to the dish.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }
}
