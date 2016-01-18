package model;

import java.util.ArrayList;

/**
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
     * The comments on the Dish.
     */
    private ArrayList<String> comments;

    /**
     * The path to the image of the Dish.
     */
    private String imgpath;

    /**
     * Constructor of a Dish.
     *
     * @param title       The dish needs a title.
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
     *
     * @param id          The dish needs an id.
     * @param title       The dish needs a title.
     * @param description The dish needs a description.
     * @param grades      The grades of the dish.
     * @param comments    The comments on the dish.
     * @param imgpath     The path to the image of the dish.
     */
    public Dish(int id, String title, String description, ArrayList<Integer> grades, ArrayList<String> comments, String imgpath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.grades = grades;
        this.comments = comments;
        this.imgpath = imgpath;
    }

    /**
     * Constructor of a Dish.
     *
     * @param id          The dish needs an id.
     * @param title       The dish needs a title.
     * @param description The dish needs a description.
     * @param grades      The grades of the dish.
     * @param comments    The comments on the dish.
     * @param imgpath     The path to the image of the dish.
     */
    public Dish(Long id, String title, String description, ArrayList<Integer> grades, ArrayList<String> comments, String imgpath) {
        this.id = id.intValue();
        this.title = title;
        this.description = description;
        this.grades = grades;
        this.comments = comments;
        this.imgpath = imgpath;
    }

    /**
     * id getter.
     *
     * @return The id of the dish.
     */
    public int getId() {
        return id;
    }

    /**
     * id setter.
     *
     * @param id the new id of this dish.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Title getter.
     *
     * @return The title of this dish.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Title setter.
     *
     * @param title The new title of this dish.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Description getter.
     *
     * @return The description of this dish.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description setter.
     *
     * @param description The new description of this dish.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the list of grades given to this dish.
     *
     * @return The list of grades given to this dish.
     */
    public ArrayList<Integer> getGrades() {
        return grades;
    }

    /**
     * Setter for the list of grades given to this dish.
     *
     * @param grades The list of grades to be set.
     */
    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
    }

    /**
     * Getter for the comments on this dish.
     *
     * @return The comments on this dish.
     */
    public ArrayList<String> getComments() {
        return comments;
    }

    /**
     * Setter for the comments on this dish.
     *
     * @param comments The comments on this dish.
     */
    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    /**
     * Image path getter.
     *
     * @return The path to the image of this dish.
     */
    public String getImgpath() {
        return imgpath;
    }

    /**
     * Image path setter.
     *
     * @param imgpath The new path to the image of this dish.
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
