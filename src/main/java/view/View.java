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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Dish;
import org.controlsfx.control.Rating;

import java.util.ArrayList;
import java.util.List;

public class View extends Application {
    private static final int DISH_AMOUNT = 3;
    private static final double SIZE = 250d;
    private static final double PADDING = 10d;
    private static final double MIN_WIDTH = 3d * SIZE + 3d * DISH_AMOUNT * PADDING + 2d * PADDING;
    private static final double MIN_HEIGHT = 2d * SIZE + 2d * PADDING;

    private List<Pair<Dish, GuiValues>> dishes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dishes = new ArrayList<>();
        testDishes();

        AnchorPane root = generateAnchorPane();
        Pagination pagination = generatePagination();
        Button back = generateButton("back");
        Button next = generateButton("next");

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
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.show();
    }

    private void testDishes() {
        for (int i = 0; i < 15; i++) {
            dishes.add(new Pair<>(
                    new Dish("Steak number " + i, "This is steak number " + i + ". It's a steak, made of meat, with pepper and salt."),
                    new GuiValues()
            ));
        }
    }

    private AnchorPane generateAnchorPane() {
        return new AnchorPane();
    }

    private Pagination generatePagination() {
        Pagination pagination = new Pagination(dishes.size() / DISH_AMOUNT);
        pagination.setPageFactory(this::generateHBox);
        return pagination;
    }

    private HBox generateHBox(int pageIndex) {
        HBox hBox = new HBox(3d * PADDING);
        hBox.setAlignment(Pos.CENTER);

        int start = DISH_AMOUNT * pageIndex;
        for (int i = start; i < start + DISH_AMOUNT; i++) {
            hBox.getChildren().add(generateVBox(i));
        }

        return hBox;
    }

    private VBox generateVBox(int dishIndex) {
        Pair<Dish, GuiValues> pair = dishes.get(dishIndex);
        Dish dish = pair.getKey();
        GuiValues values = pair.getValue();

        VBox vBox = new VBox(PADDING);
        vBox.setAlignment(Pos.CENTER);

        Text title = generateVBoxTitle(dish.getTitle());
        Text description = generateVBoxDescription(dish.getDescription());
        ImageView imageView = generateVBoxImageView("/steak.jpg");
        TextArea comment = generateVBoxComment(values);
        Rating rating = generateVBoxRating(values);

        vBox.getChildren().addAll(title, description, imageView, comment, rating);
        return vBox;
    }

    private Text generateVBoxTitle(String text) {
        Text title = new Text(text);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20d));
        title.setWrappingWidth(SIZE);
        return title;
    }

    private Text generateVBoxDescription(String text) {
        Text description = new Text(text);
        description.setWrappingWidth(SIZE);
        return description;
    }

    private ImageView generateVBoxImageView(String imgPath) {
        ImageView imageView = new ImageView();
        imageView.setSmooth(true);
        imageView.setPreserveRatio(false);
        imageView.setFitHeight(SIZE);
        imageView.setFitWidth(SIZE);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20d);
        clip.setArcHeight(20d);
        imageView.setClip(clip);
        imageView.setImage(new Image(View.class.getResourceAsStream(imgPath)));
        return imageView;
    }

    private TextArea generateVBoxComment(GuiValues values) {
        TextArea comment = new TextArea();
        comment.setWrapText(true);
        comment.setText(values.getComment());
        comment.setPromptText("Comment (optional)");
        comment.setPrefSize(SIZE, SIZE / 2d);
        comment.setOnKeyTyped(event -> values.setComment(comment.getText()));
        return comment;
    }

    private Rating generateVBoxRating(GuiValues values) {
        Rating rating = new Rating(5, values.getRating());
        rating.setOnMouseClicked(event -> values.setRating((int) rating.getRating()));
        return rating;
    }

    private Button generateButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(100d, 20d);
        return button;
    }

    private static class GuiValues {
        private String comment;
        private int rating;

        public GuiValues() {
            comment = "";
            rating = 0;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }
    }
}
