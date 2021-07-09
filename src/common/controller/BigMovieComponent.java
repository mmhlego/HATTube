package common.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import common.controller.component.CommentsComponent;
import common.controller.component.DownloadLinkComponent;
import database.DataSelector;
import database.DataUpdator;
import database.DataSelector.Arrangement;
import database.DataSelector.OrderBy;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Comment;
import model.Content;
import model.Link;
import user.UserController;

public class BigMovieComponent {
    @FXML
    private AnchorPane BackgroundPosterIMG;
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
    private Label LikeLBL;

    @FXML
    private Label ViewLBL;

    @FXML
    private Label RatingLBL;

    @FXML
    private JFXButton AddToWatchListBTN;

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
        DataUpdator.View(content.GetID());

        if (UserController.LoggedIn()) {
            if (UserController.getCurrentUser().getSubcriptions().contains(content.getID())) {
                AddToWatchListBTN.setText("Remove From Watchlist");
            } else {
                AddToWatchListBTN.setText("Add To Watchlist");
            }
        }

        PosterIMG.setImage(content.getPosterImage());
        NameLBL.setText(content.getName());
        GenresLBL.setText(Arrays.toString(content.getGenres().toArray()).replace("[", "").replace("]", ""));
        YearLBL.setText(Arrays.toString(content.getInfo()[0]).replace("[", "").replace("]", "").replace(",", " :"));
        RateLBL.setText(content.getContentRate().toString());
        LikeLBL.setText(Long.toString(content.getLikes()));
        ViewLBL.setText(Long.toString(content.getViews()));
        RatingLBL.setText(String.format("%.1f", content.getScore()));
        AddToWatchListBTN.setDisable(!UserController.LoggedIn());
        AddToWatchListBTN.setOnMouseClicked(e -> {
            if (UserController.getCurrentUser().getSubcriptions().contains(content.getID())) {
                UserController.getCurrentUser().getSubcriptions().remove(content.getID());
                AddToWatchListBTN.setText("Add To Watchlist");
            } else {
                UserController.getCurrentUser().getSubcriptions().add(content.getID());
                AddToWatchListBTN.setText("Remove From Watchlist");
            }

            new Thread(() -> DataUpdator.UpadateData(UserController.getCurrentUser())).start();
        });

        LikeBTN.setOnMouseClicked(e -> {
            if (LikeLBL.getText().equals(Long.toString(content.getLikes()))) {
                LikeLBL.setText(Long.toString(content.getLikes() + 1));
                DataUpdator.Like(Table.Contents, content.getID());
            }
        });

        DescriptionAea.setText(content.getDescription());

        BackgroundPosterIMG.setStyle("-fx-background-image: url(" + content.getPoster().toString()
                + ");    -fx-background-repeat: stretch;    -fx-background-size: cover;");

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
