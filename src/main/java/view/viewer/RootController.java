package view.viewer;

import controller.Viewer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import view.DishPagination;
import view.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    private final Viewer viewer;

    @FXML
    private StackPane root;
    @FXML
    private DishPagination pagination;

    public RootController() {
        this(new Viewer());
    }

    public RootController(Viewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pagination.setPageCount(viewer.getMenu().getDishes().size());
        pagination.setPageFactory(this::generatePage);
    }

    private Node generatePage(int pageIndex) {
        if (pageIndex < viewer.getMenu().getDishes().size()) {
            return Util.generatePage("/viewer/page.fxml", new PageController(viewer.getMenu().getDishes().get(pageIndex)));
        } else {
            return null;
        }
    }
}
