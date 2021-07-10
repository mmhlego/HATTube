package user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.*;
import com.jfoenix.controls.JFXTextField;
import api.OTPSender;
import common.controller.MainStructure;
import database.DataUpdator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Access;
import tools.Dialog;
import tools.Limiter;
import tools.Validator;
import tools.Validator.Card;
import user.UserController;
import user.visual.Captcha;

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

    boolean inValidCard = true;
    Captcha captcha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Limiter.Limit(CardNumberTXF, 19, false);
        Limiter.Limit(CvvTXF, 8, true);
        Limiter.Limit(ExpTXF, 5, false);
        Limiter.Limit(CaptchaTXF, 6, false);

        AccountIdLBL.setText(UserController.getCurrentUser().getID());

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

        SendBTN.setOnAction((e) -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OTPSender.SendOTP(UserController.getCurrentUser().getPhone());
                }
            }).start();
            // System.out.println(OTPSender.CreateOTP());
        });

        PayBTN.setOnAction((e) -> {
            while (OTPSender.getCurrentState() == OTPSender.State.SENDING) {
                try {
                    Thread.sleep(500);
                } catch (Exception e2) {
                }
            }
            if (inValidCard) {
                Dialog.Alert(AlertType.ERROR, "Error", "Card Is Invalid !");
            } else if (!(OTPSender.CheckOTP(CvvTXF.getText()))) {
                Dialog.Alert(AlertType.ERROR, "Error", "CVV Is Not Invalid !");
            } else if (!(ExpTXF.getText().length() == 5)) {
                Dialog.Alert(AlertType.ERROR, "Error", "ExpirationDate Is Not Invalid !");
            } else if (!captcha.getCaptchaResult().equals(CaptchaTXF.getText())) {
                Dialog.Alert(AlertType.ERROR, "Error", "Captcha Is W !");
            } else {
                System.out.println(UserController.getCurrentUser().getAccessID());
                UserController.getCurrentUser().GrantAccess(Access.Level);
                DataUpdator.UpadateData(UserController.getCurrentUser());
                System.out.println(UserController.getCurrentUser().getAccessID());
                MainStructure.OpenFirstPage();
                UserController.LogOut();
                Dialog.Alert(AlertType.INFORMATION, "Error",
                        "Your Payment Was Successful Log in To your Account To apply Changes");
            }
        });

        ExpTXF.setOnKeyReleased((e) -> {
            if (ExpTXF.getText().length() == 2) {
                PressButton(47);
            }
        });

        CardNumberTXF.textProperty().addListener((e) -> {
            if (CardNumberTXF.getText().length() == 4 || CardNumberTXF.getText().length() == 9
                    || CardNumberTXF.getText().length() == 14) {
                PressButton(32);
            }
            if (Validator.CardType(CardNumberTXF.getText()) == Card.VISA) {
                SetImage("Visa");
                inValidCard = false;
            } else if (Validator.CardType(CardNumberTXF.getText()) == Card.MASTER) {
                SetImage("Master");
                inValidCard = false;
            } else if (Validator.CardType(CardNumberTXF.getText()) == Card.AMERICAN) {
                SetImage("American");
                inValidCard = false;
            } else if (Validator.CardType(CardNumberTXF.getText()) == Card.DISCOVER) {
                SetImage("Discover");
                inValidCard = false;
            } else {
                TypeIMG.setImage(null);
                inValidCard = true;
            }
        });

    }

    private void SetImage(String Card) {
        try {
            TypeIMG.setImage(new Image(new FileInputStream(new File("resource/images/icons/Cards/" + Card + ".png"))));
        } catch (Exception e) {

        }
    }

    private static void PressButton(int Event) {
        try {
            Robot Switcher = new Robot();
            Switcher.keyPress(Event);
            Switcher.keyRelease(Event);
            // Switcher.keyRelease(KeyEvent.VK_Sl);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
