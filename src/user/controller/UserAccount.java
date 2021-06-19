package user.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

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
        MyChannelBTN.setOnMouseEntered(e -> borderTransitionToFront(Sep2));
        SettingBTN.setOnMouseEntered(e -> borderTransitionToFront(Sep3));
        AccountInfoBTN.setOnMouseExited(e->borderTransitionToBack(Sep1));
        MyChannelBTN.setOnMouseExited(e->borderTransitionToBack(Sep2));
        SettingBTN.setOnMouseExited(e->borderTransitionToBack(Sep3));
    }
    private void borderTransitionToFront(AnchorPane anc){
        KeyValue value = new KeyValue(anc.prefWidthProperty(),298);
        KeyValue value1 = new KeyValue(anc.layoutXProperty(),0);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.5),value,value1);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }
    private void borderTransitionToBack(AnchorPane anc){
        KeyValue value = new KeyValue(anc.prefWidthProperty(),0);
        KeyValue value1 = new KeyValue(anc.layoutXProperty(),149);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.5),value,value1);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }
}
