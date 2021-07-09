package user.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import user.visual.Captcha;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentPage implements Initializable {

    @FXML
    private JFXTextField CardNumberTXF;

    @FXML
    private ImageView TypeIMG;

    @FXML
    private JFXTextField CvvTXF;

    @FXML
    private Button SendBTN;

    @FXML
    private JFXTextField ExpTXF;

    @FXML
    private JFXTextField CaptchaTXF;

    @FXML
    private AnchorPane CaptchaPlace;

    @FXML
    private ImageView RecaptchaBTN;

    @FXML
    private Label AccountIdLBL;

    @FXML
    private Button PayBTN;
    Captcha captcha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        captcha = new Captcha(303, 30, 6);
        CaptchaPlace.getChildren().add(captcha);
        RecaptchaBTN.setOnMouseClicked(e -> {
            CaptchaPlace.getChildren().clear();
            captcha = new Captcha(303, 30, 6);
            CaptchaPlace.getChildren().add(captcha);
            RotateTransition transition = new RotateTransition(Duration.seconds(0.3), RecaptchaBTN);
            transition.setByAngle(360);
            transition.play();
        });
    }
}
