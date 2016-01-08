package view.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import view.Util;

import java.io.File;
import java.io.IOException;

public class DialogController {
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
                Util.loadImage(target, image);
                if (imageLabel.isVisible()) {
                    imageLabel.setVisible(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
