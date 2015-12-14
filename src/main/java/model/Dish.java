package model;

import java.util.ArrayList;

/**
 * Created by Wouter on 8-12-2015.
 *
 * This class represents a Dish.
 */
public class Dish {

    /**
     * The id of the Dish.
     */
    private int id;

    /**
     * The title of the Dish.
     */
    private String title;

    /**
     * The description of the Dish.
     */
    private String description;

    /**
     * The grades given to the Dish.
     */
    private ArrayList<Integer> grades;

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
        this.grades = new ArrayList<>();
        this.imgpath = null;
    }

    /**
     * Constructor of a Dish.
     * @param title The dish needs a title.
     * @param description The dish needs a description.
     * @param grades The grades of the dish.
     * @param imgpath The path to the image of the dish.
     */
    public Dish(int id, String title, String description, ArrayList<Integer> grades, String imgpath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.grades = grades;
        this.imgpath = imgpath;
    }

    /**
     * id getter.
     * @return The id of the dish.
     */
    public int getId() {
        return id;
    }

    /**
     * id setter.
     * @param id the new id of this dish.
     */
    public void setId(int id) {
        this.id = id;
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
     * Getter for the list of grades given to this dish.
     * @return The list of grades given to this dish.
     */
    public ArrayList<Integer> getGrades() {
        return grades;
    }

    /**
     * Setter for the list of grades given to this dish.
     * @param grades The list of grades to be set.
     */
    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
    }

    /**
     * Image path getter.
     * @return The path to the image of this dish.
     */
    public String getImgpath() {
        return imgpath;
    }

    /**
     * Image path setter.
     * @param imgpath The new path to the image of this dish.
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
