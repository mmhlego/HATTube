package common.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;
import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

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
    private Button LoginBTN;

    @FXML
    private HBox SignupBTN;

    @FXML
    private ImageView UsernameIMG;

    @FXML
    private ImageView PasswordIMG;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelFloat(UsernameTXF, UsernameLBL, UsernameIMG);
        LabelFloat(PasswordTXF, PasswordLBL, PasswordIMG);
        LoginBTN.setOnMouseEntered(e -> ChangeTextField(LoginBTN, 300));
        LoginBTN.setOnMouseExited(e -> ChangeTextField(LoginBTN, 250));
    }
}
