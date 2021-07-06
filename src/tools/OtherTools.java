package tools;

import java.net.URL;
import java.awt.*;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.stage.Stage;

public class OtherTools {
    private static double dx, dy;

    public static void MakeStageMovable(Node Root, Node handle) {
        handle.setCursor(Cursor.OPEN_HAND);

        handle.setOnMousePressed(e -> {
            dx = e.getX();
            dy = e.getY();
            handle.setCursor(Cursor.CLOSED_HAND);
        });

        handle.setOnMouseDragged(e -> {
            Root.getScene().getWindow().setX(e.getScreenX() - dx);
            Root.getScene().getWindow().setY(e.getScreenY() - dy);
        });

        handle.setOnMouseReleased(e -> handle.setCursor(Cursor.OPEN_HAND));
    }

    public static void MakeStageMovable(Stage stage, Node handle) {
        MakeStageMovable(stage.getScene().getRoot().getChildrenUnmodifiable().get(0), handle);
    }

    public static void openURL(URL Url) {
        try {
            Desktop.getDesktop().browse(Url.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
