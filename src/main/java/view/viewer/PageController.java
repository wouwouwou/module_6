package view.viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Dish;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PageController implements Initializable {

    private final List<Dish> dishes;

    @FXML
    private VBox root;

    public PageController(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dishes.stream()
                .map(this::generateDish)
                .forEach(root.getChildren()::add);
    }

    private Node generateDish(Dish dish) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewer/dish.fxml"));
            fxmlLoader.setController(new DishController(dish));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
