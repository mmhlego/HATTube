package common.controller;

import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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
import tools.Animation.Direction;
import tools.Animation.Speed;
import tools.Dialog;
import tools.Validator;

public class SignUp2 implements Initializable {

    @FXML
    private AnchorPane RegisterAnchor;

    @FXML
    private TextField PhoneNumberTXF;

    @FXML
    private Label PhoneNumberLBL;

    @FXML
    private TextField EmailTXF;

    @FXML
    private Label EmailLBL;

    @FXML
    private Button NextBTN;

    @FXML
    private ImageView PhoneNumberIMG;

    @FXML
    private ImageView EmailIMG;

    @FXML
    private ImageView ReturnBTN;

    public static String UserEmail;
    public static String UserPhone;
    public static boolean Returned = false;
    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (Returned) {
            PhoneNumberTXF.setText(UserPhone);
            EmailTXF.setText(UserEmail);
        }

        LabelFloat(PhoneNumberTXF, PhoneNumberLBL, PhoneNumberIMG);
        LabelFloat(EmailTXF, EmailLBL, EmailIMG);
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 1.2));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 10 / 12));

        ReturnBTN.setCursor(Cursor.HAND);
        NextBTN.setCursor(Cursor.HAND);

        ReturnBTN.setOnMouseClicked((e) -> {
            try {
                SignUp1.Returned = true;
                FXMLLoader loader = new FXMLLoader(new File("src/common/visual/SignUp1.fxml").toURI().toURL());
                root = loader.load();
                Animation.PreviousPageAnimation(RegisterAnchor, root, ReturnBTN , Direction.RIGHT,
                        Speed.FAST);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        NextBTN.setOnAction((e) -> {
            if (!Validator.CheckTextFieldsValidaty(new TextField[] { PhoneNumberTXF, EmailTXF })) {
                Dialog.Alert(AlertType.ERROR, "Error", "Some Fields Are Empty ! ");
            } else if (!Validator.CheckEmailValidaty(EmailTXF.getText())) {
                Dialog.Alert(AlertType.ERROR, "Error",
                        "Email Has Wrong Format ! (Supported Domains : gmail.com , yahoo.com , hotmail.com , outlook.com");
            } else {
                UserEmail = EmailTXF.getText();
                UserPhone = PhoneNumberTXF.getText();
                Dialog.Alert(AlertType.INFORMATION, "Code", String.valueOf(OTPSender.CreateOTP()));
                try {
                    root = FXMLLoader.load(new File("src/common/visual/SignUp3.fxml").toURI().toURL());
                    Animation.NextPageAnimation(RegisterAnchor, root, NextBTN , Direction.LEFT,
                            Speed.FAST);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
