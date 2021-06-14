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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LBL.setText("H");
                        }
                    });
                    Thread.sleep(500);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LBL.setText("HA");
                        }
                    });
                    Thread.sleep(500);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LBL.setText("HAT ");
                        }
                    });
                    Thread.sleep(500);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LBL.setText("HAT T");
                        }
                    });
                    Thread.sleep(500);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LBL.setText("HAT Tu");
                        }
                    });
                    Thread.sleep(500);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LBL.setText("HAT Tub");
                        }
                    });
                    Thread.sleep(500);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LBL.setText("HAT Tube");
                        }
                    });
                    Thread.sleep(500);
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
