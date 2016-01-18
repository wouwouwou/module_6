package view.viewer;

import controller.Viewer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.StackPane;
import view.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    private final Viewer viewer;

    @FXML
    private StackPane root;
    @FXML
    private Pagination pagination;

    public RootController() {
        this(new Viewer());
    }

    public RootController(Viewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Util.setPageCount(pagination, viewer.getMenu().getDishes().size());
        pagination.setPageFactory(this::generatePage);
    }

    private Node generatePage(int pageIndex) {
        if (pageIndex >= viewer.getMenu().getDishes().size()) {
            //FIXME empty page pagination
            return null;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewer/page.fxml"));
            fxmlLoader.setController(new PageController(viewer.getMenu().getDishes().get(pageIndex)));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
