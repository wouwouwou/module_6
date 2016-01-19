package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parameters p = getParameters();
        List<String> args = p.getUnnamed();
        if (args.size() != 1) {
            throw new IllegalArgumentException("You didn't give a single argument. Please give only one. c = customer, v = viewer, o = owner.");
        }
        String arg = args.get(0);
        switch (arg) {
            case "c":
                loadStage(primaryStage, "/customer/root.fxml");
                break;
            case "v":
                loadStage(primaryStage, "/viewer/root.fxml");
                break;
            case "o":
                loadStage(primaryStage, "/owner/root.fxml");
                break;
            default:
                throw new IllegalArgumentException("You gave a wrong argument. Please use only these: c = customer, v = viewer, o = owner");
        }
    }

    private void loadStage(Stage primaryStage, String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("DishRate");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/logo.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
