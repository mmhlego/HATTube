package common.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BigMovieComponent {

    @FXML
    private Label NameLBL;

    @FXML
    private Label GenresLBL;

    @FXML
    private Label YearLBL;

    @FXML
    private Label RateLBL;

    @FXML
    private Label TimeLBL;

    @FXML
    private Label LikeLBL;

    @FXML
    private Label ViewLBL;

    @FXML
    private Label RatingLBL;

    @FXML
    private JFXButton AddToWatchListBTN;

    @FXML
    private JFXButton DislikeBTN;

    @FXML
    private JFXButton LikeBTN;

    @FXML
    private JFXTextArea DescriptionAea;

    @FXML
    private VBox LinksPlace;

    @FXML
    private VBox SimilarsPlaces;

    @FXML
    private VBox CommentsPlace;

}
