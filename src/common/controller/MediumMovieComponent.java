package common.controller;

import java.io.File;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.Content;

public class MediumMovieComponent {

    @FXML
    private ImageView PosterIMG;

    @FXML
    private Label NameLBL;

    @FXML
    private TextArea DescriptionAea;

    @FXML
    private Label LikeLBL;

    @FXML
    private Label ViewLBL;

    @FXML
    private Label ScoreLBL;

    @FXML
    private Label DetailLBL;

    @FXML
    private Label RateLBL;

    @FXML
    private JFXButton WatchBTN;

    @FXML
    private JFXButton AddToWatchBTN;

    public void ShowContent(Content content) {
        PosterIMG.setImage(content.getPosterImage());
        NameLBL.setText(content.getName());
        DescriptionAea.setText(content.getDescription());
        LikeLBL.setText(Long.toString(content.getLikes()));
        ViewLBL.setText(Long.toString(content.getViews()));
        ScoreLBL.setText(String.format("%.1f", content.getScore()));
        // if (content.getInfo().length > 0) {
        //     DetailLBL.setText(content.getInfo()[0].toString());
        // }
        RateLBL.setText(content.getContentRate().toString());

        WatchBTN.setOnAction((e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        new File("src/common/visual/BigMovieComponent.fxml").toURI().toURL());
                Parent movie = loader.load();
                ((BigMovieComponent) loader.getController()).ShowContent(content);
                MainStructure.OpenPage(movie);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //TODO Hjazi kill you :)
        AddToWatchBTN.setOnAction((e) -> {

        });
    }
}
