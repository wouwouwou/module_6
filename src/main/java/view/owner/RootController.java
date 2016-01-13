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
        deleteButton.visibleProperty().bind(pagination.visibleProperty());
    }

    private Node generatePage(int pageIndex) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/owner/page.fxml"));
            fxmlLoader.setController(new PageController(owner.getMenu().getDishes().get(pageIndex)));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setPageCount(int index) {
        if (index > 0) {
            pagination.setPageCount(index);
            if (!pagination.isVisible()) {
                pagination.setVisible(true);
            }
        } else if (pagination.isVisible()) {
            pagination.setVisible(false);
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
            pagination.setPageCount(owner.getMenu().getDishes().size());
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
        setPageCount(size);
        pagination.setCurrentPageIndex(Math.min(pageIndex, size));
    }
}
