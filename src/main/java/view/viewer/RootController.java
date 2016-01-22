package view.viewer;

import controller.Viewer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import model.Dish;
import view.DishPagination;
import view.Util;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RootController implements Initializable {

    private final List<Dish> dishes;

    @FXML
    private StackPane root;
    @FXML
    private DishPagination pagination;

    public RootController() {
        this(new Viewer());
    }

    public RootController(Viewer viewer) {
        dishes = viewer.getMenu().getDishes().stream()
                .sorted((dish1, dish2) -> {
                    double average1 = dish1.getGrades().stream().collect(Collectors.averagingDouble(Integer::doubleValue));
                    double average2 = dish2.getGrades().stream().collect(Collectors.averagingDouble(Integer::doubleValue));
                    if (average1 < average2) {
                        return 1;
                    } else if (average1 > average2) {
                        return -1;
                    } else {
                        return 0;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pagination.setPageCount(dishes.size());
        pagination.setPageFactory(this::generatePage);
    }

    private Node generatePage(int pageIndex) {
        if (pageIndex < dishes.size()) {
            return Util.generatePage("/viewer/page.fxml", new PageController(dishes.get(pageIndex)));
        } else {
            return null;
        }
    }
}
