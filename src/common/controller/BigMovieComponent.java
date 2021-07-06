package common.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import common.controller.component.CommentsComponent;
import common.controller.component.DownloadLinkComponent;
import database.DataSelector;
import database.DataSelector.Arrangement;
import database.DataSelector.OrderBy;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Comment;
import model.Content;
import model.Link;
import user.UserController;

public class BigMovieComponent {

    @FXML
    private ImageView PosterIMG;

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

    public void ShowContent(Content content) {
        PosterIMG.setImage(content.getPosterImage());
        NameLBL.setText(content.getName());
        GenresLBL.setText(Arrays.toString(content.getGenres().toArray()).replace("[", "").replace("]", ""));
        YearLBL.setText("-----"); //TODO
        RateLBL.setText(content.getContentRate().toString());
        TimeLBL.setText("-----"); //TODO
        LikeLBL.setText(Long.toString(content.getLikes()));
        ViewLBL.setText(Long.toString(content.getViews()));
        RatingLBL.setText(String.format("%.1f", content.getScore()));
        AddToWatchListBTN.setDisable(!UserController.LoggedIn());
        AddToWatchListBTN.setOnMouseClicked(e -> System.out.println("-----")); //TODO
        DislikeBTN.setOnMouseClicked(e -> System.out.println("dislike")); //TODO
        LikeBTN.setOnMouseClicked(e -> System.out.println("like")); //TODO
        DescriptionAea.setText(content.getDescription());

        LinksPlace.getChildren().clear();
        ArrayList<?> Links = DataSelector.Select(Table.Links, new String[] { "ContentID='" + content.getID() + "'" })
                .ToArrayList();
        for (Link link : (ArrayList<Link>) Links) {
            LinksPlace.getChildren().add(LoadLink(link));
        }

        CommentsPlace.getChildren().clear();
        ArrayList<?> Comments = DataSelector
                .Select(Table.Comments, new String[] { "ContentID='" + content.getID() + "'" },
                        new OrderBy[] { OrderBy.Date }, new Arrangement[] { Arrangement.DESC })
                .ToArrayList();
        for (Comment comment : (ArrayList<Comment>) Comments) {
            CommentsPlace.getChildren().add(LoadComment(comment));
        }
    }

    private Node LoadComment(Comment comment) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    new File("src/common/visual/component/CommentsComponent.fxml").toURI().toURL());
            Parent parent = loader.load();
            ((CommentsComponent) loader.getController()).SetComment(comment);
            return parent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Node LoadLink(Link link) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    new File("src/common/visual/component/DownloadLinkComponent.fxml").toURI().toURL());
            Parent parent = loader.load();
            ((DownloadLinkComponent) loader.getController()).SetLink(link);
            return parent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
