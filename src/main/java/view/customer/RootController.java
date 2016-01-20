package view.customer;

import controller.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.DishPagination;
import view.Util;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RootController implements Initializable {
    private static final int DISH_AMOUNT = 3;

    private final Customer customer;
    private final List<DishValues> dishes;

    @FXML
    private BorderPane root;
    @FXML
    private DishPagination pagination;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    public RootController() {
        this(new Customer());
    }

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
        return Util.generatePage("/customer/page.fxml", new PageController(pageDishes));
    }

    private void submit() {
        dishes.forEach(values -> customer.gradeDish(values.getDish(), values.getComment(), values.getRating()));
        try {
            customer.exportMenu();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error when exporting the menu, please try again. If this issue persists, contact the developers.").showAndWait();
            return;
        }
        close();
    }

    private void close() {
        ((Stage) root.getScene().getWindow()).close();
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
