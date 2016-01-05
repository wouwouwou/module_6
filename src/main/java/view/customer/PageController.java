package view.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.io.IOException;
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customer/dish.fxml"));
            fxmlLoader.setController(new DishController(values));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
