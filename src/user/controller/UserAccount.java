package user.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.Access;
import tools.Dialog;
import user.UserController;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import common.controller.MainStructure;

public class UserAccount implements Initializable {

    @FXML
    private HBox AccountInfoBTN;

    @FXML
    private AnchorPane Sep1;

    @FXML
    private HBox MyChannelBTN;

    @FXML
    private AnchorPane Sep2;

    @FXML
    private HBox SettingBTN;

    @FXML
    private AnchorPane Sep3;

    @FXML
    private HBox LogoutBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AccountInfoBTN.setOnMouseEntered(e -> borderTransitionToFront(Sep1));
        AccountInfoBTN.setOnMouseExited(e -> borderTransitionToBack(Sep1));
        AccountInfoBTN.setOnMouseClicked(e -> MainStructure.OpenPage("src/user/visual/AccountInfoPage.fxml"));

        MyChannelBTN.setOnMouseEntered(e -> borderTransitionToFront(Sep2));
        MyChannelBTN.setOnMouseExited(e -> borderTransitionToBack(Sep2));
        MyChannelBTN.setOnMouseClicked(e -> {
            if (!UserController.getCurrentUser().HasAccess(Access.CreateChannel)) {
                Dialog.Alert(AlertType.ERROR, "Error", "You Don't Have Access To Create Channel !");
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(new File("src/user/visual/ChannelPage.fxml").toURI().toURL());
                    MainStructure.OpenPage((Parent) loader.load());
                    ((ChannelPage) loader.getController()).ShowChannel(UserController.getCurrentUser().getChannelID());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        SettingBTN.setOnMouseEntered(e -> borderTransitionToFront(Sep3));
        SettingBTN.setOnMouseExited(e -> borderTransitionToBack(Sep3));
        SettingBTN.setOnMouseClicked(e -> {
            MainStructure.OpenPopup("src/common/visual/Setting.fxml");
        });

        LogoutBTN.setOnMouseClicked(e -> UserController.LogOut());
    }

    private void borderTransitionToFront(AnchorPane anc) {
        KeyValue value = new KeyValue(anc.prefWidthProperty(), 298);
        KeyValue value1 = new KeyValue(anc.layoutXProperty(), 0);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.5), value, value1);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }

    private void borderTransitionToBack(AnchorPane anc) {
        KeyValue value = new KeyValue(anc.prefWidthProperty(), 0);
        KeyValue value1 = new KeyValue(anc.layoutXProperty(), 149);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.5), value, value1);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }
}
