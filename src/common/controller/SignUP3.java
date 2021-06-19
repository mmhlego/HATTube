package common.controller;

import com.jfoenix.controls.JFXButton;
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

public class SignUP3 implements Initializable {

    @FXML
    private TextField CodeTXF;

    @FXML
    private Label CodeLBL;

    @FXML
    private Button NextBTN;

    @FXML
    private ImageView CodeIMG;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelFloat(CodeTXF, CodeLBL, CodeIMG);
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 1.2));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 10 / 12));
    }
}
