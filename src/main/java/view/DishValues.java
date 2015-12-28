package view;

import model.Dish;

public class DishValues {
    private final Dish dish;
    private String comment;
    private int rating;

    public DishValues(Dish dish) {
        this.dish = dish;
        this.comment = "";
        this.rating = 0;
    }

    public Dish getDish() {
        return dish;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
