package model;

import java.util.ArrayList;
import java.util.List;

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
     * The votes given to the Dish.
     */
    private List<Vote> votes;

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
        this.votes = new ArrayList<>();
        this.imgpath = null;
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
     * Getter for the list of votes given to this dish.
     * @return
     */
    public List<Vote> getVotes() {
        return votes;
    }

    /**
     * Setter for the list of votes given to this dish.
     * @param votes
     */
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
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
