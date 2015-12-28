package view.rating;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import view.DishValues;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RatingController implements Initializable {
    public static final int DISH_AMOUNT = 4;

    @FXML
    private AnchorPane root;
    @FXML
    private Pagination pagination;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    private List<DishValues> dishes;

    public RatingController(List<DishValues> dishes) {
        this.dishes = dishes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pagination.setPageCount((int) Math.ceil((double) dishes.size() / (double) DISH_AMOUNT));
        pagination.setPageFactory(this::generatePage);
    }

    private Node generatePage(int pageIndex) {
        List<DishValues> pageDishes = dishes.stream()
                .skip(DISH_AMOUNT * pageIndex)
                .limit(DISH_AMOUNT)
                .collect(Collectors.toList());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/rating/page.fxml"));
            fxmlLoader.setController(new RatingPageController(pageDishes));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void next() {
        //TODO
    }

    @FXML
    private void back() {
        //TODO
    }
}
