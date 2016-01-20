package view;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DishPagination extends StackPane implements Initializable {

    @FXML
    private Pagination pagination;
    @FXML
    private Label label;

    private int pageCount;

    public DishPagination() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pagination.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPageCount(1);
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int count) {
        if (count <= 0) {
            if (pageCount != 0) {
                pagination.setVisible(false);
                label.setVisible(true);
                pageCount = 0;
            }
        } else {
            if (pageCount == 0) {
                pagination.setVisible(true);
                label.setVisible(false);
            }
            pagination.setPageCount(count);
            pageCount = count;
        }
    }

    public void updatePageCount(int count) {
        if (count > 0) {
            setPageCount(count + 1);
        }
        setPageCount(count);
    }

    public void setPageFactory(Callback<Integer, Node> factory) {
        pagination.setPageFactory(factory);
    }

    public int getCurrentPage() {
        return pagination.getCurrentPageIndex();
    }

    public void setCurrentPage(int index) {
        pagination.setCurrentPageIndex(index);
    }

    public BooleanProperty getVisibleProperty() {
        return pagination.visibleProperty();
    }
}
