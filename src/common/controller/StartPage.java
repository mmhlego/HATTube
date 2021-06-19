package common.controller;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Content;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import database.DataBase;

public class StartPage implements Initializable {

    @FXML
    private Label LBL;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ProgressIndicator pr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LBL.setText("");
        pr.setTranslateY(100);
        TranslateTransition transition = new TranslateTransition();
        transition.setByY(-100);
        transition.setDuration(Duration.seconds(1));
        transition.setCycleCount(1);
        transition.setNode(pr);
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.play();

        Thread DBConnection = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataBase.Connect()) {
                    System.out.println("Connection Failed. Retrying ...");
                }
                System.out.println("Connected");

                Content.CheckImages();
                System.out.println("Loaded Images");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader loader = new FXMLLoader(
                                    new File("src/common/visual/MainStructure.fxml").toURI().toURL());
                            ((Stage) anchor.getParent().getScene().getWindow()).hide();
                            Stage stage = new Stage(StageStyle.TRANSPARENT);
                            Scene s = new Scene(loader.load());
                            s.setFill(Color.TRANSPARENT);
                            stage.setScene(s);
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int millis = 400;
                try {
                    String Text = "HAT TUBE";
                    for (int i = 1; i <= Text.length(); i++) {
                        ShowText(Text.substring(0, i));
                        Thread.sleep(millis);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void ShowText(String substring) {
                Platform.runLater(() -> LBL.setText(substring));
            }
        });

        DataBase.LoadScreen();

        DBConnection.start();
        thread.start();
    }
}
