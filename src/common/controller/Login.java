package common.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

public class Login implements Initializable {

    @FXML
    private AnchorPane LoginAnchor;

    @FXML
    private TextField UsernameTXF;

    @FXML
    private Label UsernameLBL;

    @FXML
    private TextField PasswordTXF;

    @FXML
    private Label PasswordLBL;

    @FXML
    private Button LoginBTN;

    @FXML
    private HBox SignupBTN;

    @FXML
    private ImageView UsernameIMG;

    @FXML
    private ImageView PasswordIMG;

    @FXML
    private JFXCheckBox RemembermeCHB;

    public TextField getUsernameTXF() {
        return UsernameTXF;
    }

    public void setUsernameTXF(TextField usernameTXF) {
        UsernameTXF = usernameTXF;
    }

    public Label getUsernameLBL() {
        return UsernameLBL;
    }

    public void setUsernameLBL(Label usernameLBL) {
        UsernameLBL = usernameLBL;
    }

    public TextField getPasswordTXF() {
        return PasswordTXF;
    }

    public void setPasswordTXF(TextField passwordTXF) {
        PasswordTXF = passwordTXF;
    }

    public Label getPasswordLBL() {
        return PasswordLBL;
    }

    public void setPasswordLBL(Label passwordLBL) {
        PasswordLBL = passwordLBL;
    }

    public ImageView getUsernameIMG() {
        return UsernameIMG;
    }

    public void setUsernameIMG(ImageView usernameIMG) {
        UsernameIMG = usernameIMG;
    }

    public ImageView getPasswordIMG() {
        return PasswordIMG;
    }

    public void setPasswordIMG(ImageView passwordIMG) {
        PasswordIMG = passwordIMG;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RemembermeCHB.setCursor(Cursor.HAND);
        LoginBTN.setCursor(Cursor.HAND);
        SignupBTN.setCursor(Cursor.HAND);

        LabelFloat(UsernameTXF, UsernameLBL, UsernameIMG);
        LabelFloat(PasswordTXF, PasswordLBL, PasswordIMG);
        LoginBTN.setOnMouseEntered(e -> ChangeTextField(LoginBTN, 300));
        LoginBTN.setOnMouseExited(e -> ChangeTextField(LoginBTN, 250));

        SignupBTN.setOnMouseClicked((e) -> {
            try {
                Parent root = FXMLLoader.load(new File("src/common/visual/SignUp1.fxml").toURI().toURL());
                LoginAnchor.getChildren().clear();
                LoginAnchor.getChildren().add(root);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
