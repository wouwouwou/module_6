package view.owner;

import controller.Owner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    private final Owner owner;

    @FXML
    private AnchorPane root;
    @FXML
    private Pagination pagination;
    @FXML
    private Button deleteButton;
    @FXML
    private Button newButton;

    public RootController(Owner owner) {
        this.owner = owner;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPageCount(owner.getMenu().getDishes().size());
        pagination.setPageFactory(this::generatePage);
        deleteButton.disableProperty().bind(pagination.visibleProperty().not());
    }

    private Node generatePage(int pageIndex) {
        if (pageIndex >= owner.getMenu().getDishes().size()) {
            return null;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/owner/page.fxml"));
            fxmlLoader.setController(new PageController(owner.getMenu().getDishes().get(pageIndex)));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void newButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/owner/dialog.fxml"));
            fxmlLoader.setController(new DialogController(owner));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Dish");
            stage.setScene(new Scene(root, 480d, 360d));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(newButton.getScene().getWindow());
            stage.showAndWait();
            owner.exportMenu();
            Util.setPageCount(pagination, owner.getMenu().getDishes().size());
            pagination.setCurrentPageIndex(pagination.getPageCount() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteButton() {
        int pageIndex = pagination.getCurrentPageIndex();
        owner.deleteDish(owner.getMenu().getDishes().get(pageIndex));
        try {
            owner.exportMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int size = owner.getMenu().getDishes().size();
        Util.setPageCount(pagination, size);
        pagination.setCurrentPageIndex(Math.min(pageIndex, size));
    }
}
