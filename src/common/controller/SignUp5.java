package common.controller;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import database.DataAdder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;
import tools.Animation;
import tools.Animation.Direction;
import tools.Animation.Speed;
import tools.Dialog;

public class SignUp5 implements Initializable {

    @FXML
    private AnchorPane RegisterAnchor;

    @FXML
    private Label AgeLBL;

    @FXML
    private Label TermsLBL;

    @FXML
    private JFXDatePicker BirthPicker;

    @FXML
    private Label BirthDateLBL;

    @FXML
    private CheckBox TermBOX;

    @FXML
    private Button SignUpBTN;

    @FXML
    private ImageView ReturnBTN;

    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BirthPicker.setCursor(Cursor.HAND);
        ReturnBTN.setCursor(Cursor.HAND);
        SignUpBTN.setCursor(Cursor.HAND);
        TermsLBL.setCursor(Cursor.HAND);

        ReturnBTN.setOnMouseClicked((e) -> {
            try {
                SignUp4.Returned = true;
                root = FXMLLoader.load(new File("src/common/visual/SignUp4.fxml").toURI().toURL());
                Animation.PreviousPageAnimation(RegisterAnchor, root, ReturnBTN  , Direction.RIGHT , Speed.FAST);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        BirthPicker.setOnAction((e) -> {
            AgeLBL.setText(String.valueOf(User.CalculateAge(BirthPicker.getValue())));
        });

        TermsLBL.setOnMouseClicked((e) -> {
            OpenTerms();
        });

        SignUpBTN.setOnAction((e) -> {
            if (AgeLBL.getText().equals("")) {
                Dialog.Alert(AlertType.ERROR, "Error", "Choose Your BirthDate !");
            } else if (!TermBOX.isSelected()) {
                Dialog.Alert(AlertType.ERROR, "Error", "You Must Accept Our Terms And Conditions !");
            } else {
                try {
                    User u = new User(SignUp1.UserFirstName, SignUp1.UserLastname, SignUp2.UserPhone, SignUp2.UserEmail,
                            SignUp4.UserUsername, SignUp4.UserPassword,
                            LocalDate.parse(String.valueOf(BirthPicker.getValue())));
                    DataAdder.AddData(u);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    private void OpenTerms() {
        try {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            Parent root = FXMLLoader.load(new File("src/common/visual/Terms.fxml").toURI().toURL());
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.setOnHidden((e) -> {
                TermBOX.setSelected(Terms.Check);
                System.out.println(2);
            });
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
