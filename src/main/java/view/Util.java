package view;

import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.nio.file.Files;

public class Util {
    public static void setPageCount(Pagination pagination, int index) {
        if (index > 0) {
            pagination.setPageCount(index);
            if (!pagination.isVisible()) {
                pagination.setVisible(true);
            }
        } else if (pagination.isVisible()) {
            pagination.setVisible(false);
        }
    }

    public static void loadImage(String path, ImageView image) {
        loadImage(new File(path), image);
    }

    public static void loadImage(File file, ImageView image) {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(image.fitWidthProperty());
        clip.heightProperty().bind(image.fitHeightProperty());
        clip.setArcWidth(20d);
        clip.setArcHeight(20d);
        image.setClip(clip);
        image.setImage(new Image("file:///" + file.getAbsolutePath(), true));
    }

    public static File copyFile(File source) throws IOException {
        return copyFile(source, 0);
    }

    private static File copyFile(File source, int i) throws IOException {
        String targetName = "img/" + source.getName();
        if (i != 0) {
            targetName += " (" + i + ")";
        }
        File target = new File(targetName);
        if (target.exists()) {
            if (!fileEquals(source, target)) {
                return copyFile(source, ++i);
            }
        } else {
            Files.copy(source.toPath(), target.toPath());
        }
        return target;
    }

    public static boolean fileEquals(File f1, File f2) {
        if (f1.equals(f2)) {
            return true;
        } else if (f1.length() != f2.length()) {
            return false;
        } else {
            InputStream in1 = null;
            InputStream in2 = null;
            try {
                in1 = new BufferedInputStream(new FileInputStream(f1));
                in2 = new BufferedInputStream(new FileInputStream(f2));
                int expectedByte = in1.read();
                while (expectedByte != -1) {
                    if (expectedByte != in2.read()) {
                        return false;
                    }
                    expectedByte = in1.read();
                }
                return in2.read() == -1;
            } catch (IOException e) {
                return false;
            } finally {
                if (in1 != null) {
                    try {
                        in1.close();
                    } catch (IOException ignored) {
                    }
                }
                if (in2 != null) {
                    try {
                        in2.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }
}
