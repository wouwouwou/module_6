package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                loadCustomerGUI(primaryStage);
                break;
            case "v":
                loadViewerGUI(primaryStage);
                break;
            case "o":
                loadOwnerGUI(primaryStage);
                break;
            default:
                throw new IllegalArgumentException("You gave a wrong argument. Please use only these: c = customer, v = viewer, o = owner");
        }
    }

    private void loadCustomerGUI(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customer/root.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("RestVote");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadViewerGUI(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewer/root.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("RestVote");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadOwnerGUI(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/owner/root.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("RestVote");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
