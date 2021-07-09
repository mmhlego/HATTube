package common.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Content;

import java.io.File;
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
        MovieIMG.setImage(content.getPosterImage());
        MovieNameLBL.setText(content.getName());
        MovieAnc.setOnMouseClicked(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        new File("src/common/visual/BigMovieComponent.fxml").toURI().toURL());
                Parent parent = loader.load();
                BigMovieComponent controller = loader.getController();
                controller.ShowContent(content);
                MainStructure.OpenPage(parent);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
