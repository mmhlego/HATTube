package common.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

public class SignUp1 implements Initializable {

    @FXML
    private TextField FirstnameTXF;

    @FXML
    private Label FirstnameLBL;

    @FXML
    private TextField LastnameTXF;

    @FXML
    private Label LastnameLBL;

    @FXML
    private JFXButton NextBTN;

    @FXML
    private HBox SignInBTN;

    @FXML
    private ImageView FirstnameIMG;

    @FXML
    private ImageView LastnameIMG;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelFloat(FirstnameTXF, FirstnameLBL, FirstnameIMG);
        LabelFloat(LastnameTXF, LastnameLBL, LastnameIMG);
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 1.2));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 10 / 12));
    }
}
