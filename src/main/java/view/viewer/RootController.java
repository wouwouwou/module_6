package view.viewer;

import controller.Viewer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import model.Dish;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RootController implements Initializable {
    private static final int DISH_AMOUNT = 4;

    private final Viewer viewer;

    @FXML
    private AnchorPane root;
    @FXML
    private Pagination pagination;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    public RootController(Viewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pagination.setPageCount((int) Math.ceil((double) viewer.getMenu().getDishes().size() / (double) DISH_AMOUNT));
        pagination.setPageFactory(this::generatePage);
    }

    private Node generatePage(int pageIndex) {
        List<Dish> pageDishes = viewer.getMenu().getDishes().stream()
                .skip(DISH_AMOUNT * pageIndex)
                .limit(DISH_AMOUNT)
                .collect(Collectors.toList());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewer/page.fxml"));
            fxmlLoader.setController(new PageController(pageDishes));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void next() {
        pagination.setCurrentPageIndex((pagination.getCurrentPageIndex() + 1) % pagination.getPageCount());
    }

    @FXML
    private void back() {
        pagination.setCurrentPageIndex((pagination.getCurrentPageIndex() + pagination.getPageCount() - 1) % pagination.getPageCount());
    }
}
