package common.controller.component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingPage implements Initializable {

    final long duration = 100;
    boolean running = true;
    int i = 0;
    @FXML
    private Label LBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LBL.setText("");


        Thread thread = new Thread(() -> {
            while (running) {
                Platform.runLater(() -> {

                    switch (i % 13) {
                        case 0:
                            LBL.setText("");
                            break;
                        case 1:
                            LBL.setText("C");
                            break;
                        case 2:
                            LBL.setText("Co");
                            break;
                        case 3:
                            LBL.setText("Con");
                            break;
                        case 4:
                            LBL.setText("Conn");
                            break;
                        case 5:
                            LBL.setText("Conne");
                            break;
                        case 6:
                            LBL.setText("Connect");
                            break;
                        case 7:
                            LBL.setText("Connecti");
                            break;
                        case 8:
                            LBL.setText("Connectin");
                            break;
                        case 9:
                            LBL.setText("Connecting");
                            break;
                        case 10:
                            LBL.setText("Connecting .");
                            break;
                        case 11:
                            LBL.setText("Connecting ..");
                            break;
                        case 12:
                            LBL.setText("Connecting ...");
                            break;
                    }

                });

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });
        thread.start();

    }
}
