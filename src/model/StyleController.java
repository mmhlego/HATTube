package model;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StyleController {
    public static void LabelFloat(TextField t, Label l, ImageView i) {
        l.setCursor(Cursor.TEXT);
        l.setOnMouseClicked(e -> t.requestFocus());
        t.focusedProperty().addListener((observable, oldValue, newValue) -> {
            i.setVisible(!newValue);
            t.setPadding(new Insets(0, 15, 0, newValue ? 15 : 50));
            TranslateTransition transition = MoveLabel(l, t.getHeight() / 2, newValue, t.getText());

            if (!newValue && !t.getText().equals(""))
                t.setStyle("-fx-border-color: rgba(255, 255, 255, 0.2);\n" + "    -fx-border-width: 2 2 2 2;\n"
                        + "    -fx-border-radius: 50px;\n" + "    -fx-background-color: rgba(255, 255, 255, 0);");

            transition.play();

            transition.setOnFinished(e -> {
                if (newValue) {
                    l.setStyle("-fx-background-color: #2d2d35;-fx-text-fill: #4B8B90;");
                } else if (t.getText().equals("")) {
                    l.setStyle("-fx-background-color: transparent;-fx-text-fill: #b9b9b9;");
                } else {
                    l.setStyle("-fx-background-color: #2d2d35;-fx-text-fill: #b9b9b9;");
                }
            });

            ChangeTextField(t, newValue ? 300 : 250);
        });

        if (!t.getText().equals("")) {
            t.setStyle("-fx-border-color: rgba(255, 255, 255, 0.2);\n" + "    -fx-border-width: 2 2 2 2;\n"
                    + "    -fx-border-radius: 50px;\n" + "    -fx-background-color: rgba(255, 255, 255, 0);");
            l.setLayoutY(l.getLayoutY() - t.getPrefHeight() / 2);
            l.setStyle("-fx-background-color:  #2d2d35;-fx-text-fill: #b9b9b9;");
        }
    }

    // TranslateTransition transition = new TranslateTransition();
    // transition.setInterpolator(Interpolator.EASE_BOTH);
    // transition.setNode(l);
    // transition.setCycleCount(1);
    // transition.setDuration(Duration.seconds(0.3));

    // t.setPadding(new Insets(0, 15, 0, focused ? 15 : 50));

    // if (t.getText().equals("")) {
    //             if (!focused)
    //                 l.setStyle("-fx-background-color: transparent;");
    // 
    //             transition.setByY(t.getHeight() / (focused ? -2 : 2));
    // 
    //             if (focused)
    //                 l.setStyle("-fx-background-color: #2d2d35;");
    // } else if (!focused) {
    //     t.setStyle("-fx-border-color: rgba(255, 255, 255, 0.2);\n" + "    -fx-border-width: 2 2 2 2;\n"
    //             + "    -fx-border-radius: 50px;\n" + "    -fx-background-color: rgba(255, 255, 255, 0);");
    // }

    //         transition.play();
    // 
    //         if (focused) {
    //             transition.setOnFinished(e -> l.setStyle("-fx-background-color: #2d2d35;-fx-text-fill: #4B8B90;"));
    //         } else if (t.getText().equals("")) {
    //             transition.setOnFinished(e -> l.setStyle("-fx-background-color: transparent;-fx-text-fill: #b9b9b9;"));
    //         }
    // 
    //         ChangeTextField(t, focused ? 300 : 250);

    private static TranslateTransition MoveLabel(Label label, double dheight, boolean focused, String text) {
        TranslateTransition transition = new TranslateTransition();
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setNode(label);
        transition.setCycleCount(1);
        transition.setDuration(Duration.seconds(0.3));
        if (text.equals(""))
            transition.setByY(dheight * (focused ? -1 : 1));

        if (!focused && text.equals(""))
            label.setStyle("-fx-background-color: transparent;");
        else
            label.setStyle("-fx-background-color: #2d2d35;");

        return transition;
    }

    public static void ChangeTextField(TextField tf, double NewWidth) {
        KeyValue value1 = new KeyValue(tf.layoutXProperty(), (tf.getLayoutX() - (NewWidth - tf.getWidth()) / 2));
        KeyValue value2 = new KeyValue(tf.prefWidthProperty(), NewWidth);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.3), value1, value2);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }

    public static void ChangeTextField(Button tf, double NewWidth) {
        KeyValue value1 = new KeyValue(tf.layoutXProperty(), (tf.getLayoutX() - (NewWidth - tf.getWidth()) / 2));
        KeyValue value2 = new KeyValue(tf.prefWidthProperty(), NewWidth);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.3), value1, value2);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }
}
