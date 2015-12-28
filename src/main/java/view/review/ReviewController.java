package view.review;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import view.DishValues;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReviewController implements Initializable {
    public static final int DISH_AMOUNT = 3;

    @FXML
    private AnchorPane root;
    @FXML
    private Pagination pagination;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    private List<DishValues> dishes;

    public ReviewController(List<DishValues> dishes) {
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/review/page.fxml"));
            fxmlLoader.setController(new ReviewPageController(pageDishes));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void submit() {
        dishes.stream()
                .filter(values -> values.getRating() > 0)
                .forEach(values -> values.getDish().getGrades().add(values.getRating()));
    }

    @FXML
    private void next() {
        if (dishes.stream().noneMatch(dishValues -> dishValues.getRating() == 0)) {
            submit();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Not all dishes are rated. Do you want to continue?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> submit());
        }
    }

    @FXML
    private void back() {
        //TODO
    }
}
