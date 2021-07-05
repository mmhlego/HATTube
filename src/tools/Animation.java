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

    public enum Directions {
        UP(90), RIGHT(0), DOWN(270), LEFT(180);

        private double X;
        private double Y;

        private Directions(double angle) {
            X = Math.sin(Math.toRadians(angle));
            Y = Math.cos(Math.toRadians(angle));
        }

        public double getX() {
            return X;
        }

        public double getY() {
            return Y;
        }
    }

    public enum Speed {
        VERYSLOW(4500),SLOW(3500),MEDIUM(2500),FAST(1500),VERYFAST(500);

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

    public static void NextPageAnimation(AnchorPane CurrentAnchor, Parent root, Button NextStepBTN) {
        root.translateXProperty().set(NextStepBTN.getScene().getWidth());
        CurrentAnchor.getChildren().clear();
        CurrentAnchor.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
    }

    public static void PreviousPageAnimation(AnchorPane CurrentAnchor, Parent root, ImageView ReturnBTN) {
        root.translateXProperty().set(-ReturnBTN.getScene().getWidth());
        CurrentAnchor.getChildren().clear();
        CurrentAnchor.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

}

class Direction {

}
