package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

public class Util {
    public static void loadImage(String path, ImageView image) {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(image.fitWidthProperty());
        clip.heightProperty().bind(image.fitHeightProperty());
        clip.setArcWidth(20d);
        clip.setArcHeight(20d);
        image.setClip(clip);
        InputStream s = Util.class.getResourceAsStream(path);
        Image i = new Image(s);
        image.setImage(i);
    }
}
