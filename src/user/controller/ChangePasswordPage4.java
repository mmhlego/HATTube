package user.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import database.DataUpdator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.User;
import tools.Animation;
import tools.Animation.Direction;
import tools.Animation.Speed;
import user.UserController;
import tools.Dialog;

public class ChangePasswordPage4 implements Initializable {

    @FXML
    private AnchorPane Page;

    @FXML
    private ImageView CancelBTN;

    @FXML
    private JFXTextField RepeatPasswordTXF;

    @FXML
    private ImageView ChangePasswordBTN;

    @FXML
    private ImageView BackBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChangePasswordBTN.setCursor(Cursor.HAND);
        BackBTN.setCursor(Cursor.HAND);
        CancelBTN.setCursor(Cursor.HAND);

        // TODO CancelBTN.setOnMouseClicked((e) ->{});

        BackBTN.setOnMouseClicked((e) -> {
            ChangePasswordPage3.Returned = true;
            try {
                Parent root = FXMLLoader.load(new File("src/user/visual/ChangePasswordPage3.fxml").toURI().toURL());
                Animation.NextPageAnimation(Page, root, Direction.LEFT, Speed.FAST);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        ChangePasswordBTN.setOnMouseClicked((e) -> {
            if (!RepeatPasswordTXF.getText().equals(ChangePasswordPage3.UsernewPass)) {
                User u = UserController.getCurrentUser();
                u.setPassword(RepeatPasswordTXF.getText());
                DataUpdator.UpadateData(u);
                Dialog.Alert(AlertType.INFORMATION, "Success", "Your Password Has Been Successfully Changed !");
                // TODO try {
                // Parent root = FXMLLoader.load(new
                // File("src/user/visual/ChangePasswordPage4.fxml").toURI().toURL());
                // Animation.NextPageAnimation(Page, root, Direction.RIGHT, Speed.FAST);
                // } catch (Exception e1) {
                // e1.printStackTrace();
                // }
            } else {
                Dialog.Alert(AlertType.ERROR, "Error", "Password Repeat Is Wrong !");
            }
        });

    }

}
