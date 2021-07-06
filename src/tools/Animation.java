package tools;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Animation {
    public enum Direction {
        BOTTOMRIGHT(315), BOTTOM(270), BOTTOMLEFT(225), LEFT(180), TOPLEFT(135), TOP(90), TOPRIGHT(45), RIGHT(0);

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
        VERYSLOW(4000), SLOW(3000), MEDIUM(2000), FAST(1000), VERYFAST(500);

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

    public static void NextPageAnimation(AnchorPane CurrentAnchor, Parent NextPage, Direction direction, Speed speed) {
        // NextPage.translateXProperty().set(NextStepBTN.getScene().getWidth() * (-direction.getX()));
        NextPage.translateXProperty().set(CurrentAnchor.getWidth() * (-direction.getX()));
        // NextPage.translateYProperty().set(NextStepBTN.getScene().getHeight() * (-direction.getY()));
        NextPage.translateYProperty().set(CurrentAnchor.getHeight() * (-direction.getY()));
        CurrentAnchor.getChildren().clear();
        CurrentAnchor.getChildren().add(NextPage);
        Timeline timeline = new Timeline();
        KeyValue kv1 = new KeyValue(NextPage.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyValue kv2 = new KeyValue(NextPage.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(speed.getDuration(), kv1, kv2);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}