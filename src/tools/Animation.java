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
    
    public static void NextPageAnimation(AnchorPane CurrentAnchor, Parent root, Button NextStepBTN) {
        root.translateXProperty().set(NextStepBTN.getScene().getWidth());
        CurrentAnchor.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    public static void PreviousPageAnimation(AnchorPane CurrentAnchor, Parent root, ImageView ReturnBTN) {
        root.translateXProperty().set(-ReturnBTN.getScene().getWidth());
        CurrentAnchor.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

}
