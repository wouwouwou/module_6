package model;

/**
 * Created by Wouter on 8-12-2015.
 *
 * This class represents a Dish.
 */
public class Dish {

    /**
     * The title of the Dish.
     */
    private String title;

    /**
     * The description of the Dish.
     */
    private String description;

    /**
     * The grade of the Dish.
     */
    private double grade;

    /**
     * The path to the image of the Dish.
     */
    private String imgpath;

    /**
     * Constructor of a Dish.
     * @param title The dish needs a title.
     * @param description The dish needs a description.
     */
    public Dish(String title, String description) {
        this.title = title;
        this.description = description;
        this.grade = 0.0;
    }

    /**
     * Title getter.
     * @return The title of this dish.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Title setter.
     * @param title The new title of this dish.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Description getter.
     * @return The description of this dish.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description setter.
     * @param description The new description of this dish.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Grade getter.
     * @return The grade of this dish.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Grade setter.
     * @param grade The new grade of this dish.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Image path getter.
     * @return The path to the image of this dish.
     */
    public String getImgpath() {
        return imgpath;
    }

    /**
     * Imgae path setter.
     * @param imgpath The new path to the image of this dish.
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
