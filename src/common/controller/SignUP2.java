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

public class SignUP2 implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelFloat(PhoneNumberTXF, PhoneNumberLBL, PhoneNumberIMG);
        LabelFloat(EmailTXF, EmailLBL, EmailIMG);
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 1.2));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 10 / 12));
    }
}
