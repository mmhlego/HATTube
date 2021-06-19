package common.controller;

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
import java.net.URL;
import java.io.*;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXCheckBox;
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
