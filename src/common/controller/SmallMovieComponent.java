package common.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SmallMovieComponent implements Initializable {

    @FXML
    private AnchorPane MovieAnc;

    @FXML
    private Label LikeLBL;

    @FXML
    private Label ViewLBL;

    @FXML
    private Label ImdbLBL;

    @FXML
    private ImageView MovieIMG;

    @FXML
    private Label MovieNameLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MovieAnc.setOnMouseEntered(e->{
            LikeLBL.toFront();
            ViewLBL.toFront();
            ImdbLBL.toFront();
        });
        MovieAnc.setOnMouseExited(e->{
            LikeLBL.toBack();
            ViewLBL.toBack();
            ImdbLBL.toBack();
        });
    }
}
