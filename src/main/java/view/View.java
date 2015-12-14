package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Dish;
import org.controlsfx.control.Rating;

import java.util.ArrayList;
import java.util.List;

public class View extends Application {
    private static final int DISH_AMOUNT = 3;
    private static final double SIZE = 250d;
    private static final double PADDING = 10d;
    private static final double MIN_WIDTH = 3d * SIZE + (3d * DISH_AMOUNT + 2d) * PADDING;

    private List<Dish> dishes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dishes = new ArrayList<>();
        testDishes();

        Pagination pagination = generatePagination();
        Button back = generateButton("back");
        Button next = generateButton("next");

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(pagination, back, next);

        AnchorPane.setTopAnchor(pagination, PADDING);
        AnchorPane.setLeftAnchor(pagination, PADDING);
        AnchorPane.setRightAnchor(pagination, PADDING);
        AnchorPane.setBottomAnchor(pagination, PADDING);

        AnchorPane.setLeftAnchor(back, PADDING);
        AnchorPane.setBottomAnchor(back, PADDING);

        AnchorPane.setRightAnchor(next, PADDING);
        AnchorPane.setBottomAnchor(next, PADDING);

        Scene scene = new Scene(root, 1280d, 720d);
        primaryStage.setTitle("RestVote");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.show();
    }

    private void testDishes() {
        for (int i = 0; i < 15; i++) {
            dishes.add(new Dish("Steak number " + i, "This is steak number " + i + ". It's a steak, made of meat, with pepper and salt."));
        }
    }

    private Pagination generatePagination() {
        Pagination pagination = new Pagination(5);
        pagination.setPageFactory(this::generateHBox);
        return pagination;
    }

    private HBox generateHBox(int pageIndex) {
        int start = DISH_AMOUNT * pageIndex;
        List<Dish> pageDishes = dishes.subList(start, start + DISH_AMOUNT);

        HBox hBox = new HBox(3d * PADDING);
        hBox.setAlignment(Pos.CENTER);

        for (Dish dish : pageDishes) {
            hBox.getChildren().add(generateVBox(dish));
        }

        return hBox;
    }

    private VBox generateVBox(Dish dish) {
        VBox vBox = new VBox(PADDING);
        vBox.setAlignment(Pos.CENTER);

        Text title = new Text(dish.getTitle());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20d));
        title.setWrappingWidth(SIZE);

        Text description = new Text(dish.getDescription());
        description.setWrappingWidth(SIZE);

        ImageView imageView = new ImageView();
        imageView.setSmooth(true);
        imageView.setPreserveRatio(false);
        imageView.setFitHeight(SIZE);
        imageView.setFitWidth(SIZE);
        imageView.setImage(new Image(View.class.getResourceAsStream("/steak.jpg")));

        TextArea comment = new TextArea();
        comment.setWrapText(true);
        comment.setPromptText("Comment (optional)");
        comment.setPrefSize(SIZE, SIZE / 2d);

        Rating rating = new Rating(5, 0);

        vBox.getChildren().addAll(title, description, imageView, comment, rating);
        return vBox;
    }

    private Button generateButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(100d, 20d);
        return button;
    }
}
