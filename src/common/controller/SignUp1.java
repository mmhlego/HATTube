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
import javafx.scene.layout.HBox;
import tools.Animation;
import tools.Dialog;
import tools.Validator;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static model.StyleController.ChangeTextField;
import static model.StyleController.LabelFloat;

public class SignUp1 implements Initializable {

    @FXML
    private AnchorPane RegisterAnchor;

    @FXML
    private TextField FirstnameTXF;

    @FXML
    private Label FirstnameLBL;

    @FXML
    private TextField LastnameTXF;

    @FXML
    private Label LastnameLBL;

    @FXML
    private Button NextBTN;

    @FXML
    private HBox SignInBTN;

    @FXML
    private ImageView FirstnameIMG;

    @FXML
    private ImageView LastnameIMG;

    @FXML
    private ImageView ReturnBTN;

    public static String UserFirstName;
    public static String UserLastname;
    public static boolean Returned = false;

    Parent root;

    public TextField getFirstnameTXF() {
        return FirstnameTXF;
    }

    public void setFirstnameTXF(TextField firstnameTXF) {
        FirstnameTXF = firstnameTXF;
    }

    public Label getFirstnameLBL() {
        return FirstnameLBL;
    }

    public void setFirstnameLBL(Label firstnameLBL) {
        FirstnameLBL = firstnameLBL;
    }

    public TextField getLastnameTXF() {
        return LastnameTXF;
    }

    public void setLastnameTXF(TextField lastnameTXF) {
        LastnameTXF = lastnameTXF;
    }

    public Label getLastnameLBL() {
        return LastnameLBL;
    }

    public void setLastnameLBL(Label lastnameLBL) {
        LastnameLBL = lastnameLBL;
    }

    public ImageView getFirstnameIMG() {
        return FirstnameIMG;
    }

    public void setFirstnameIMG(ImageView firstnameIMG) {
        FirstnameIMG = firstnameIMG;
    }

    public ImageView getLastnameIMG() {
        return LastnameIMG;
    }

    public void setLastnameIMG(ImageView lastnameIMG) {
        LastnameIMG = lastnameIMG;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        LabelFloat(FirstnameTXF, FirstnameLBL, FirstnameIMG);
        LabelFloat(LastnameTXF, LastnameLBL, LastnameIMG);
        if (Returned) {
            FirstnameTXF.setText(UserFirstName);
            LastnameTXF.setText(UserLastname);
        }
        NextBTN.setOnMouseEntered(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 1.2));
        NextBTN.setOnMouseExited(e -> ChangeTextField(NextBTN, NextBTN.getWidth() * 10 / 12));
        ReturnBTN.setCursor(Cursor.HAND);
        NextBTN.setCursor(Cursor.HAND);

        ReturnBTN.setOnMouseClicked((e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(new File("src/common/visual/Login.fxml").toURI().toURL());
                root = loader.load();

                Animation.PreviousPageAnimation(RegisterAnchor, root, ReturnBTN);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        NextBTN.setOnAction((e) -> {
            if (!Validator.CheckTextFieldsValidaty(new TextField[] { FirstnameTXF, LastnameTXF })) {
                Dialog.Alert(AlertType.ERROR, "Error", "Some Fields Are Empty ! ");
            } else {
                UserFirstName = FirstnameTXF.getText();
                UserLastname = LastnameTXF.getText();
                try {
                    root = FXMLLoader.load(new File("src/common/visual/SignUp2.fxml").toURI().toURL());
                    Animation.NextPageAnimation(RegisterAnchor, root, NextBTN);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }
}
