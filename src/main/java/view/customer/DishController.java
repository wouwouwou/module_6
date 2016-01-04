package view.customer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import view.DishValues;
import view.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class DishController implements Initializable {

    private final DishValues values;

    @FXML
    private VBox root;
    @FXML
    private Text title;
    @FXML
    private Text description;
    @FXML
    private ImageView image;
    @FXML
    private TextArea comment;
    @FXML
    private Rating rating;

    public DishController(DishValues values) {
        this.values = values;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(values.getDish().getTitle());
        description.setText(values.getDish().getDescription());
        Util.loadImage(values.getDish().getImgpath(), image);
        comment.setText(values.getComment());
        rating.setRating(values.getRating());
    }

    @FXML
    private void setComment() {
        values.setComment(comment.getText());
    }

    @FXML
    private void setRating() {
        values.setRating((int) rating.getRating());
    }
}
