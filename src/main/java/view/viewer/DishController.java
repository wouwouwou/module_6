package view.viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Dish;
import org.controlsfx.control.Rating;
import view.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class DishController implements Initializable {

    private final Dish dish;

    @FXML
    private GridPane root;
    @FXML
    private ImageView image;
    @FXML
    private Text title;
    @FXML
    private Rating rating;
    @FXML
    private Text votes;
    @FXML
    private Text comment;

    public DishController(Dish dish) {
        this.dish = dish;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Util.loadImage(dish.getImgpath(), image);
        title.setText(dish.getTitle());
        if (!dish.getGrades().isEmpty()) {
            rating.setRating(dish.getGrades().stream().mapToInt(Integer::intValue).average().getAsDouble());
        } else {
            rating.setRating(0);
        }
        votes.setText(dish.getGrades().size() + " votes");
        if (!dish.getComments().isEmpty()) {
            comment.setText(dish.getComments().get(dish.getComments().size() - 1));
        } else {
            comment.setText("");
        }
    }
}
