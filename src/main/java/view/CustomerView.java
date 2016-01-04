package view;

import controller.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Dish;
import model.Menu;
import view.customer.RootController;

import java.util.ArrayList;

public class CustomerView extends Application {

    private Customer customer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        customer = new Customer();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customer/root.fxml"));
        fxmlLoader.setController(new RootController(customer));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("RestVote");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
//        generateMenu();
        customer.exportMenu();
    }

    private void generateMenu() {
        ArrayList<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            dishes.add(new Dish(i, "Steak number " + i, "This is steak number " + i + ". It's a steak, made of meat, with pepper and salt.", new ArrayList<>(), new ArrayList<>(), "/steak.jpg"));
        }
        customer.setMenu(new Menu(dishes));
    }
}
