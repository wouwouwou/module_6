package view.review;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import view.DishValues;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReviewPageController implements Initializable {

    @FXML
    private HBox root;

    private List<DishValues> dishes;

    public ReviewPageController(List<DishValues> dishes) {
        this.dishes = dishes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dishes.stream()
                .map(this::generateDish)
                .forEach(root.getChildren()::add);
    }

    private Node generateDish(DishValues values) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/review/dish.fxml"));
            fxmlLoader.setController(new ReviewDishController(values));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
