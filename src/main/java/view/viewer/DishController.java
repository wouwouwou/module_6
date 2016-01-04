package view.viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import view.DishValues;
import view.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class DishController implements Initializable {

    private final DishValues values;

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

    public DishController(DishValues values) {
        this.values = values;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Util.loadImage("/steak.jpg", image);
        title.setText(values.getDish().getTitle());
//        rating.setRating(values.getRating());
        rating.setRating(3);
//        votes.setText(values.getDish().getGrades().size() + " votes");
        votes.setText(26 + " votes");
//        comment.setText(values.getComment());
        comment.setText("This is a test please leave actual random comment here");
    }
}
