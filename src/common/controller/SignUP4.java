package common.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

public class SignUP4 implements Initializable {

    @FXML
    private TextField UsernameTXF;

    @FXML
    private Label UsernameLBL;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelFloat(UsernameTXF, UsernameLBL, UsernameIMG);
        LabelFloat(PasswordTXF, PasswordLBL, PasswordIMG);
        LabelFloat(RepPasswordTXF, RepPasswordLBL, RepPasswordIMG);
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 1.2));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 10 / 12));
    }
}
