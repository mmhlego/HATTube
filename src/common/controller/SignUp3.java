package common.controller;

import api.OTPSender;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tools.Animation;
import tools.Dialog;
import tools.Validator;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

public class SignUp3 implements Initializable {

    @FXML
    private AnchorPane RegisterAnchor;

    @FXML
    private TextField CodeTXF;

    @FXML
    private Label CodeLBL;

    @FXML
    private Button NextBTN;

    @FXML
    private ImageView ReturnBTN;

    @FXML
    private ImageView CodeIMG;

    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LabelFloat(CodeTXF, CodeLBL, CodeIMG);
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 1.2));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 10 / 12));

        ReturnBTN.setCursor(Cursor.HAND);
        NextBTN.setCursor(Cursor.HAND);

        // new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         OTPSender.SendOTP(SignUp2.UserPhone);
        //     }
        // }).start();

        ReturnBTN.setOnMouseClicked((e) -> {
            try {
                SignUp2.Returned = true;
                root = FXMLLoader.load(new File("src/common/visual/SignUp2.fxml").toURI().toURL());
                Animation.PreviousPageAnimation(RegisterAnchor, root, ReturnBTN);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        NextBTN.setOnAction((e) -> {
            if (!Validator.CheckTextFieldsValidaty(new TextField[] { CodeTXF })) {
                Dialog.Alert(AlertType.ERROR, "Error", "Field Is Empty !");
            } else {
                while (OTPSender.getCurrentState() == OTPSender.State.SENDING) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e2) {
                    }
                }
                if (OTPSender.getCurrentState() == OTPSender.State.ERROR) {

                } else if (!OTPSender.CheckOTP(CodeTXF.getText())) {
                    Dialog.Alert(AlertType.ERROR, "Error", "OTP Is Wrong !");
                } else {
                    try {
                        root = FXMLLoader.load(new File("src/common/visual/SignUp4.fxml").toURI().toURL());
                        Animation.NextPageAnimation(RegisterAnchor, root, NextBTN);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }
}
