package view.owner;

import controller.Owner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Dish;
import view.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DialogController {

    private final Owner owner;

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

    public DialogController(Owner owner) {
        this.owner = owner;
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
        }
    }

    @FXML
    private void addButton() {
        owner.addDish(new Dish(-1, title.getText(), description.getText(), new ArrayList<>(), new ArrayList<>(), path));
        close();
    }

    @FXML
    private void cancelButton() {
        close();
    }

    private void close() {
        ((Stage) root.getScene().getWindow()).close();
    }
}
