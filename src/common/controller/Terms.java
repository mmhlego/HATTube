package common.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.util.Duration;

public class Terms implements Initializable{

    @FXML
    private Button AcceptBTN;

    @FXML
    private Button DeclineBTN;

    @FXML
    private ScrollPane ScrollPane;

    public static boolean Check;

    Timeline animation;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AcceptBTN.setCursor(Cursor.HAND);
        DeclineBTN.setCursor(Cursor.HAND);

        slowScrollToBottom(ScrollPane);

        ScrollPane.setOnMouseClicked((e) ->{
            animation.stop();
        });

        DeclineBTN.setOnAction((e) ->{
            DeclineBTN.getParent().getScene().getWindow().hide();
        });
        
        AcceptBTN.setOnAction((e) -> {
            AcceptBTN.getParent().getScene().getWindow().hide();
        });
    }
    
    private void slowScrollToBottom(ScrollPane scrollPane) {
        animation = new Timeline(new KeyFrame(Duration.seconds(300), new KeyValue(scrollPane.vvalueProperty(), 1)));
        animation.play();
    }
    
}

