package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Dish;
import view.rating.RatingController;
import view.review.ReviewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View extends Application {

    private List<DishValues> dishes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dishes = new ArrayList<>();
        testDishes();

        Parent root = loadReview();
        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("RestVote");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent loadReview() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/review/root.fxml"));
        fxmlLoader.setController(new ReviewController(dishes));
        return fxmlLoader.load();
    }

    private Parent loadRating() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/rating/root.fxml"));
        fxmlLoader.setController(new RatingController(dishes));
        return fxmlLoader.load();
    }

    private void testDishes() {
        for (int i = 0; i < 11; i++) {
            Dish dish = new Dish("Steak number " + i, "This is steak number " + i + ". It's a steak, made of meat, with pepper and salt.");
            dishes.add(new DishValues(dish));
        }
    }
}
