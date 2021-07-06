package common.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Content;
import tools.ImageDownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        MovieAnc.setOnMouseEntered(e -> {
            LikeLBL.toFront();
            ViewLBL.toFront();
            ImdbLBL.toFront();
        });

        MovieAnc.setOnMouseExited(e -> {
            LikeLBL.toBack();
            ViewLBL.toBack();
            ImdbLBL.toBack();
        });
    }

    public void ShowContent(Content content) {
        LikeLBL.setText(Long.toString(content.getLikes()) + " Likes");
        ViewLBL.setText(Long.toString(content.getViews()) + " Views");
        ImdbLBL.setText(String.format("%.1f", content.getScore()));
        try {
            MovieIMG.setImage(new Image(new FileInputStream(new File("resource/images/posters/" + content.getName()
                    + ImageDownloader.GetImageFormat(content.getPoster().toString())))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MovieNameLBL.setText(content.getName());
    }
}
