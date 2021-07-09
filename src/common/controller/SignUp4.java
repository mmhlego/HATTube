package common.controller;

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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

public class SignUp4 implements Initializable {

    @FXML
    private AnchorPane RegisterAnchor;

    @FXML
    private TextField UsernameTXF;

    @FXML
    private Label UsernameLBL;
    @FXML
    private ImageView CloseBTN;
    @FXML
    private TextField PasswordTXF;

    @FXML
    private Label PasswordLBL;

    @FXML
    private Button NextBTN;

    @FXML
    private ImageView UsernameIMG;

    @FXML
    private ImageView PasswordIMG;

    @FXML
    private TextField RepPasswordTXF;

    @FXML
    private Label RepPasswordLBL;

    @FXML
    private ImageView RepPasswordIMG;

    @FXML
    private ImageView ReturnBTN;

    public static String UserUsername;
    public static String UserPassword;
    public static boolean Returned = false;
    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (Returned) {
            UsernameTXF.setText(UserUsername);
            PasswordTXF.setText(UserPassword);
            RepPasswordTXF.setText(UserPassword);
        }

        ReturnBTN.setCursor(Cursor.HAND);
        NextBTN.setCursor(Cursor.HAND);

        LabelFloat(UsernameTXF, UsernameLBL, UsernameIMG);
        LabelFloat(PasswordTXF, PasswordLBL, PasswordIMG);
        LabelFloat(RepPasswordTXF, RepPasswordLBL, RepPasswordIMG);
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, 300));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, 250));

        ReturnBTN.setOnMouseClicked((e) -> {
            try {
                SignUp2.Returned = true;
                root = FXMLLoader.load(new File("src/common/visual/SignUp2.fxml").toURI().toURL());
                Animation.NextPageAnimation(RegisterAnchor, root, Direction.LEFT, Speed.FAST);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        NextBTN.setOnAction((e) -> {
            if (!Validator.CheckTextFieldsValidaty(new TextField[] { UsernameTXF, PasswordTXF, RepPasswordTXF })) {
                Dialog.Alert(AlertType.ERROR, "Error", "Some Fields Are Empty ! ");
            } else if (!PasswordTXF.getText().equals(RepPasswordTXF.getText())) {
                Dialog.Alert(AlertType.ERROR, "Error", "Passwords Don't Match !");
            } else if(Validator.CheckUsername(UsernameTXF.getText())){
                Dialog.Alert(AlertType.ERROR, "Error" , "This Username Already Exists Choose Another One !");
            } 
            else {
                UserUsername = UsernameTXF.getText();
                UserPassword = PasswordTXF.getText();
                try {
                    root = FXMLLoader.load(new File("src/common/visual/SignUp5.fxml").toURI().toURL());
                    Animation.NextPageAnimation(RegisterAnchor, root, Direction.RIGHT, Speed.FAST);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
