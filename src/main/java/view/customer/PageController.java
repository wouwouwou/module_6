package view.customer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import view.Util;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PageController implements Initializable {

    private final List<DishValues> dishes;

    @FXML
    private HBox root;

    public PageController(List<DishValues> dishes) {
        this.dishes = dishes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dishes.stream()
                .map(this::generateDish)
                .forEach(root.getChildren()::add);
    }

    private Node generateDish(DishValues values) {
        return Util.generatePage("/customer/dish.fxml", new DishController(values));
    }
}
