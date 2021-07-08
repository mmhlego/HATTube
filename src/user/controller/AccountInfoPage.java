
package user.controller;

import java.net.URL;
import java.util.ResourceBundle;
import database.DataUpdator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.User;
import tools.Dialog;
import tools.OtherTools;
import tools.Validator;
import user.UserController;

public class AccountInfoPage implements Initializable {

    @FXML
    private ImageView AvatarIMG;

    @FXML
    private TextField FirstNameTXF;

    @FXML
    private TextField LastNameTXF;

    @FXML
    private TextField EmailTXF;

    @FXML
    private TextField PhoneTXF;

    @FXML
    private TextField UsernameTXF;

    @FXML
    private Label ChangePasswordBTN;

    @FXML
    private Button ChangeInfoBTN;

    @FXML
    private Label AccessIdLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FirstNameTXF.setText(UserController.getCurrentUser().getFirstName());
        LastNameTXF.setText(UserController.getCurrentUser().getLastName());
        EmailTXF.setText(UserController.getCurrentUser().getEmail());
        PhoneTXF.setText(UserController.getCurrentUser().getPhone());
        UsernameTXF.setText(UserController.getCurrentUser().getUsername());

        ChangeInfoBTN.setOnAction((e) -> {
            if (ChangeInfoBTN.getText().equals("Change Info")) {
                OtherTools.ChangeTextFieldAccess(
                        new TextField[] { FirstNameTXF, LastNameTXF, EmailTXF, PhoneTXF, UsernameTXF }, true);
                ChangeInfoBTN.setText("Save Info");
            } else {
                if (!Validator.CheckTextFieldsValidaty(
                        new TextField[] { FirstNameTXF, LastNameTXF, EmailTXF, PhoneTXF, UsernameTXF })) {
                    Dialog.Alert(AlertType.ERROR, "Error", "Some Fields Are Empty !");
                } else if (!Validator.CheckEmailValidaty(EmailTXF.getText())) {
                    Dialog.Alert(AlertType.ERROR, "Error",
                            "Email Has Wrong Format ! (Supported Domains : gmail.com , yahoo.com , hotmail.com , outlook.com");
                } else {
                    User u = UserController.getCurrentUser();
                    u.setFirstName(FirstNameTXF.getText());
                    u.setLastName(LastNameTXF.getText());
                    u.setEmail(EmailTXF.getText());
                    u.setPhone(PhoneTXF.getText());
                    u.setUsername(UsernameTXF.getText());
                    DataUpdator.UpadateData(u);
                    OtherTools.ChangeTextFieldAccess(
                            new TextField[] { FirstNameTXF, LastNameTXF, EmailTXF, PhoneTXF, UsernameTXF }, false);
                    ChangeInfoBTN.setText("Change Info");
                    Dialog.Alert(AlertType.INFORMATION, "Success", "Your Information Has Been Successfully Updated !");
                }
            }
        });

    }

}
