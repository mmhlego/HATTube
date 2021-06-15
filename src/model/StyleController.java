package model;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StyleController {
    public static void LabelFloat(TextField t, Label l, ImageView i) {
        l.setCursor(Cursor.TEXT);
        l.setOnMouseClicked(e-> t.requestFocus());
        t.focusedProperty().addListener((observable, oldValue, newValue) -> {
            TranslateTransition transition = new TranslateTransition();
            transition.setInterpolator(Interpolator.EASE_BOTH);
            transition.setNode(l);
            transition.setCycleCount(1);
            transition.setDuration(Duration.seconds(0.3));
            if (newValue) {
                i.setVisible(false);
                t.setPadding(new Insets(0,15,0,15));
                if (!t.getText().equals("")) {


                } else {
                    transition.setByY(t.getHeight() / 2 * -1);
                    l.setStyle("-fx-background-color: #2d2d35;");

                }


                transition.play();
                transition.setOnFinished(e -> l.setStyle("-fx-background-color: #2d2d35;-fx-text-fill: #4B8B90;"));
                ChangeTextField(t, t.getWidth() * 1.2);

            } else {
                i.setVisible(true);
                t.setPadding(new Insets(0,15,0,50));
                if (t.getText().equals("")) {
                    l.setStyle("-fx-background-color: transparent;");
                    transition.setByY(t.getHeight() / 2);

                } else {


                    t.setStyle("-fx-border-color: rgba(255, 255, 255, 0.2);\n" +
                            "    -fx-border-width: 2 2 2 2;\n" +
                            "    -fx-border-radius: 50px;\n" +
                            "    -fx-background-color: rgba(255, 255, 255, 0);");
                }


                transition.play();
                if (t.getText().equals("")) {
                    transition.setOnFinished(e -> l.setStyle("-fx-background-color: transparent;-fx-text-fill: #b9b9b9;"));
                }
                ChangeTextField(t, t.getWidth() * 10 / 12);
            }
        });
    }

    public static void ChangeTextField(TextField tf, double NewWidth) {
        KeyValue value1 = new KeyValue(tf.layoutXProperty(), (tf.getLayoutX() - (NewWidth - tf.getWidth()) / 2));
        KeyValue value2 = new KeyValue(tf.prefWidthProperty(), NewWidth);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.3), value1, value2);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }
    public static void ChangeTextField(JFXButton tf, double NewWidth) {
        KeyValue value1 = new KeyValue(tf.layoutXProperty(), (tf.getLayoutX() - (NewWidth - tf.getWidth()) / 2));
        KeyValue value2 = new KeyValue(tf.prefWidthProperty(), NewWidth);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.3), value1, value2);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }
}
