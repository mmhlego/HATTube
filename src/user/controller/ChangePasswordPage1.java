package user.controller;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import api.OTPSender;
import javafx.fxml.*;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tools.Animation;
import tools.Dialog;
import tools.Animation.Direction;
import tools.Animation.Speed;

public class ChangePasswordPage1 implements Initializable{

    @FXML
    private AnchorPane Page;

    @FXML
    private ImageView CancelBTN;

    @FXML
    private JFXTextField CodeTXF;

    @FXML
    private ImageView NextBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dialog.Alert(AlertType.INFORMATION, "Code", String.valueOf(OTPSender.CreateOTP()));
        NextBTN.setCursor(Cursor.HAND);
        CancelBTN.setCursor(Cursor.HAND);

        //TODO Send OTP

        //TODO CancelBTN.setOnMouseClicked((e) ->{});

        NextBTN.setOnMouseClicked((e) -> {
            if (OTPSender.CheckOTP(CodeTXF.getText())) {
                try {
                    Parent root = FXMLLoader.load(new File("src/user/visual/ChangePasswordPage2.fxml").toURI().toURL());
                    Animation.NextPageAnimation(Page, root, Direction.RIGHT, Speed.FAST);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else{
                Dialog.Alert(AlertType.ERROR, "Error", "Entered OTP Is Wrong !");
            }
        });
        
    }

}
