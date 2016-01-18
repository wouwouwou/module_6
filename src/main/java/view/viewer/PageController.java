package view.viewer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Dish;
import org.controlsfx.control.Rating;
import view.Util;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PageController implements Initializable {

    private final Dish dish;

    @FXML
    private GridPane root;
    @FXML
    private Text title;
    @FXML
    private Text description;
    @FXML
    private ImageView image;
    @FXML
    private Rating rating;
    @FXML
    private Text votes;
    @FXML
    private Text label;
    @FXML
    private ListView<Text> comments;

    public PageController(Dish dish) {
        this.dish = dish;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(dish.getTitle());
        description.setText(dish.getDescription());
        Util.loadImage(dish.getImgpath(), image);
        if (!dish.getGrades().isEmpty()) {
            rating.setRating(dish.getGrades().stream().mapToInt(Integer::intValue).average().getAsDouble());
        } else {
            rating.setRating(0);
        }
        votes.setText(dish.getGrades().size() + " votes");
        List<Text> texts = dish.getComments().stream()
                .map(Text::new)
                .collect(Collectors.toList());
        texts.forEach(text -> text.wrappingWidthProperty().bind(comments.widthProperty().subtract(30d)));
        comments.setItems(FXCollections.observableArrayList(texts));
    }
}