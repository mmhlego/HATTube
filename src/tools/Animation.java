package tools;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Animation {
    public enum Direction {
        UP(90), RIGHT(0), DOWN(270), LEFT(180);

        private double X;
        private double Y;

        private Direction(double angle) {
            X = Math.cos(Math.toRadians(angle));
            Y = Math.sin(Math.toRadians(angle));
        }

        public double getX() {
            return X;
        }

        public double getY() {
            return Y;
        }
    }

    public enum Speed {
        VERYSLOW(4500), SLOW(3500), MEDIUM(2500), FAST(1500), VERYFAST(500);

        Duration duration;

        public Duration getDuration() {
            return duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        private Speed(int millis) {
            setDuration(Duration.millis(millis));
        }
    }

    public static void NextPageAnimation(AnchorPane CurrentAnchor, Parent root, Button NextStepBTN, Direction direction,
            Speed speed) {
        root.translateXProperty().set(NextStepBTN.getScene().getWidth() * (-direction.getX()));
        root.translateYProperty().set(NextStepBTN.getScene().getHeight() * (-direction.getY()));
        CurrentAnchor.getChildren().clear();
        CurrentAnchor.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv1 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyValue kv2 = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(speed.getDuration() , kv1 , kv2);
        timeline.getKeyFrames().add(kf);
        timeline.play();

    }

    public static void PreviousPageAnimation(AnchorPane CurrentAnchor, Parent root, ImageView ReturnBTN,
            Direction direction, Speed speed) {
        root.translateXProperty().set(ReturnBTN.getScene().getWidth() * (-direction.getX()));
        root.translateYProperty().set(ReturnBTN.getScene().getHeight() * (-direction.getY()));
        CurrentAnchor.getChildren().clear();
        CurrentAnchor.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv1 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyValue kv2 = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(speed.getDuration(), kv1, kv2);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}