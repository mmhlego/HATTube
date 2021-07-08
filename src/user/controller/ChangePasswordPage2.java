package user.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tools.Animation;
import tools.Animation.Direction;
import tools.Animation.Speed;
import tools.Dialog;
import user.UserController;

public class ChangePasswordPage2 implements Initializable {

    @FXML
    private AnchorPane Page;

    @FXML
    private ImageView CancelBTN;

    @FXML
    private JFXTextField CurrentPasswordTXF;

    @FXML
    private ImageView NextBTN;

    @FXML
    private ImageView BackBTN;

    public static boolean Returned;
    public static String UserCurrentPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NextBTN.setCursor(Cursor.HAND);
        BackBTN.setCursor(Cursor.HAND);
        CancelBTN.setCursor(Cursor.HAND);

        if (Returned) {
            CurrentPasswordTXF.setText(UserCurrentPassword);
        }

        // TODO CancelBTN.setOnMouseClicked((e) ->{});

        BackBTN.setOnMouseClicked((e) -> {
            //TODO The Back btn must send page to accoount information
            try {
                Parent root = FXMLLoader.load(new File("src/user/visual/ChangePasswordPage1.fxml").toURI().toURL());
                Animation.NextPageAnimation(Page, root, Direction.LEFT, Speed.FAST);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        NextBTN.setOnMouseClicked((e) -> {
            if (UserController.getCurrentUser().getPassword().equals(CurrentPasswordTXF.getText())) {
                UserCurrentPassword = CurrentPasswordTXF.getText();
                try {
                    Parent root = FXMLLoader.load(new File("src/user/visual/ChangePasswordPage3.fxml").toURI().toURL());
                    Animation.NextPageAnimation(Page, root, Direction.RIGHT, Speed.FAST);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                Dialog.Alert(AlertType.ERROR, "Error", "Current Password Is Wrong !");
            }
        });

    }

}
