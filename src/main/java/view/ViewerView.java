package view;

import controller.Viewer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.viewer.RootController;

import java.util.ArrayList;

public class ViewerView extends Application {

    private Viewer viewer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        viewer = new Viewer();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewer/root.fxml"));
        fxmlLoader.setController(new RootController(viewer));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("RestVote");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
