package common.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private TextField UsernameTXF;

    @FXML
    private Label UsernameLBL;

    @FXML
    private TextField PasswordTXF;

    @FXML
    private Label PasswordLBL;

    @FXML
    private JFXButton LoginBTN;

    @FXML
    private HBox SignupBTN;

    public static void LabelFloat(TextField t, Label l) {
        t.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                TranslateTransition transition = new TranslateTransition();
                transition.setInterpolator(Interpolator.EASE_BOTH);
                transition.setNode(l);
                transition.setCycleCount(1);
                transition.setDuration(Duration.seconds(0.3));
                if (newValue) {
                    if (!t.getText().equals("")) {


                    } else {
                        transition.setByY(t.getHeight() / 2 * -1);
                        l.setStyle("-fx-background-color: #2d2d35;");

                    }


                    transition.play();
                    transition.setOnFinished(e -> {

                        l.setStyle("-fx-background-color: #2d2d35;-fx-text-fill: #4B8B90;");
                        t.setPadding(new Insets(0, 10, 0, 10));

                    });
                    ChangeTextField(t, t.getWidth() * 1.2);

                } else {
                    t.setPadding(new Insets(0, 0, 0, 0));
                    if (t.getText().equals("")) {
                        l.setStyle("-fx-background-color: transparent;");
                        transition.setByY(t.getHeight() / 2);

                    } else {

                        t.setPadding(new Insets(0, 10, 0, 10));
                        t.setStyle("-fx-border-color: rgba(255, 255, 255, 0.2);\n" +
                                "    -fx-border-width: 2 2 2 2;\n" +
                                "    -fx-border-radius: 50px;\n" +
                                "    -fx-background-color: rgba(255, 255, 255, 0);");
                    }


                    transition.play();
                    if (t.getText().equals("")) {
                        transition.setOnFinished(e -> {
                            l.setStyle("-fx-background-color: transparent;-fx-text-fill: #b9b9b9;");
                        });
                    }
                    ChangeTextField(t, t.getWidth() * 10 / 12);
                }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelFloat(UsernameTXF, UsernameLBL);
        LabelFloat(PasswordTXF, PasswordLBL);
        LoginBTN.setOnMouseEntered(e->{
            ChangeTextField(LoginBTN,LoginBTN.getWidth()*1.2);
        });
        LoginBTN.setOnMouseExited(e->{
            ChangeTextField(LoginBTN,LoginBTN.getWidth()*10/12);
        });
    }
}
