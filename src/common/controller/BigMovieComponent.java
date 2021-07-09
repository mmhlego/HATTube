package common.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import common.controller.component.CommentsComponent;
import common.controller.component.DownloadLinkComponent;
import database.DataAdder;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private HBox SimilarsPlaces;

    @FXML
    private VBox CommentsPlace;
    @FXML
    private TextArea NewCommentText;
    @FXML
    private VBox SendComment;

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

        String DescriptionText = content.getDescription();
        for (String[] row : content.getInfo()) {
            DescriptionText += "\n" + Arrays.toString(row).replace("[", "").replace("]", "").replace(",", " :");
        }
        DescriptionAea.setText(DescriptionText);

        BackgroundPosterIMG.setStyle("-fx-background-image: url(" + content.getPoster().toString()
                + ");    -fx-background-repeat: stretch;    -fx-background-size: cover;");

        LinksPlace.getChildren().clear();
        ArrayList<?> Links = DataSelector.Select(Table.Links, new String[] { "ContentID='" + content.getID() + "'" })
                .ToArrayList();
        for (Link link : (ArrayList<Link>) Links) {
            LinksPlace.getChildren().add(LoadLink(link));
        }
        if (UserController.LoggedIn()) {
            if (content.BelongsTo(UserController.getCurrentUser().getChannelID())) {
                Parent parent = MainStructure.GetParent("src/common/visual/component/AddComponent.fxml");
                LinksPlace.getChildren().add(parent);
                ((AnchorPane) parent).setOnMouseClicked(e -> {
                    FXMLLoader loader = MainStructure.GetLoader("src/common/visual/AddLinkPage.fxml");
                    try {
                        MainStructure.OpenPage((Parent) loader.load());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    ((AddLinkPage) loader.getController()).ShowContent(content);
                });
            }
        }

        CommentsPlace.getChildren().clear();
        ArrayList<?> Comments = DataSelector
                .Select(Table.Comments, new String[] { "ContentID='" + content.getID() + "'" },
                        new OrderBy[] { OrderBy.Date }, new Arrangement[] { Arrangement.DESC })
                .ToArrayList();
        for (Comment comment : (ArrayList<Comment>) Comments) {
            CommentsPlace.getChildren().add(LoadComment(comment));
        }

        SendComment.setDisable(!UserController.LoggedIn());
        SendComment.setOnMouseClicked(e -> {
            if (NewCommentText.getText().equals("")) {
                tools.Dialog.Alert(AlertType.ERROR, "Empty Comment", "Please provide a text for your comment.");
            } else {
                Comment comment = new Comment(UserController.getCurrentUser().getID(), content.getID(),
                        NewCommentText.getText());
                DataAdder.AddData(comment);
                NewCommentText.setText("");
                ShowContent(content);
            }
        });

        int i = 0;
        for (Content c : ((ArrayList<Content>) DataSelector.Select(Table.Contents, new String[] { "1" },
                new OrderBy[] { OrderBy.Rand }, new Arrangement[] { Arrangement.NONE }).ToArrayList()).subList(0, 3)) {
            FXMLLoader loader = MainStructure.GetLoader("src/common/visual/SmallMovieComponent.fxml");
            try {
                SimilarsPlaces.getChildren().add(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((SmallMovieComponent) loader.getController()).ShowContent(c);
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
