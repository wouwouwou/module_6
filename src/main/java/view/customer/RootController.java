package view.customer;

import controller.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Dish;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RootController implements Initializable {
    public static final int DISH_AMOUNT = 3;

    private final Customer customer;
    private final List<DishValues> dishes;

    @FXML
    private AnchorPane root;
    @FXML
    private Pagination pagination;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    public RootController(Customer customer) {
        this.customer = customer;
        dishes = customer.getMenu().getDishes().stream()
                .map(DishValues::new)
                .collect(Collectors.toList());
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customer/page.fxml"));
            fxmlLoader.setController(new PageController(pageDishes));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void submit() {
        dishes.forEach(this::gradeDish);
        customer.exportMenu();
        close();
    }

    private void close() {
        ((Stage) root.getScene().getWindow()).close();
    }

    private void gradeDish(DishValues values) {
        Dish dish = values.getDish();
        String comment = values.getComment();
        int rating = values.getRating();
        if (!"".equals(comment)) {
            dish.getComments().add(comment);
        }
        if (rating > 0) {
            dish.getGrades().add(rating);
        }
    }

    @FXML
    private void submitButton() {
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
    private void cancelButton() {
        close();
    }
}
