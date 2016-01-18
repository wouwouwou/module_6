package view.owner;

import controller.Owner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Dish;
import view.Util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogController implements Initializable {

    private final Owner owner;

    private final Pagination pagination;

    private final Dish dish;

    private String path;

    @FXML
    private GridPane root;
    @FXML
    private TextField title;
    @FXML
    private ImageView image;
    @FXML
    private Label imageLabel;
    @FXML
    private TextArea description;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;

    public DialogController(Owner owner, Pagination pagination) {
        this(owner, pagination, null);
    }

    public DialogController(Owner owner, Pagination pagination, Dish dish) {
        this.owner = owner;
        this.pagination = pagination;
        this.dish = dish;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.textProperty().addListener(observable -> updateButton());
        description.textProperty().addListener(observable -> updateButton());
        if (dish != null) {
            initDish();
        }
        updateButton();
    }

    private void initDish() {
        addButton.setText("Edit Dish");
        title.setText(dish.getTitle());
        description.setText(dish.getDescription());
        path = dish.getImgpath();
        Util.loadImage(path, image);
        imageLabel.setVisible(false);
    }

    @FXML
    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
        File source = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (source != null) {
            try {
                File target = Util.copyFile(source);
                path = target.getPath();
                Util.loadImage(target, image);
                if (imageLabel.isVisible()) {
                    imageLabel.setVisible(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateButton();
        }
    }

    @FXML
    private void addButton() {
        if (dish == null) {
            owner.addDish(new Dish(title.getText(), description.getText(), path));
            Util.setPageCount(pagination, owner.getMenu().getDishes().size());
            pagination.setCurrentPageIndex(pagination.getPageCount() - 1);
        } else {
            dish.setTitle(title.getText());
            dish.setDescription(description.getText());
            dish.setImgpath(path);
            owner.editDish(dish);
            Util.setPageCount(pagination, owner.getMenu().getDishes().size() + 1); // Force refresh
            Util.setPageCount(pagination, owner.getMenu().getDishes().size());
        }
        try {
            owner.exportMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    @FXML
    private void cancelButton() {
        close();
    }

    private void updateButton() {
        addButton.setDisable(path == null || "".equals(title.getText()) || description.getParagraphs().isEmpty());
    }

    private void close() {
        ((Stage) root.getScene().getWindow()).close();
    }
}
