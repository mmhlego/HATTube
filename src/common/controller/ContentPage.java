package common.controller;

import java.io.File;
import java.util.ArrayList;

import database.DataSelector;
import database.DataSelector.Arrangement;
import database.DataSelector.OrderBy;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Channel;
import model.Content;
import model.Genre;
import user.UserController;
import user.controller.MediumChannelComponent;

public class ContentPage {
    @FXML
    private Label PageName;
    @FXML
    private VBox MoviesPlace;

    int Height = 110;

    public void LoadGenre(Genre genre) {
        Height = 110;

        PageName.setText("Search Results");

        ArrayList<?> Contents = DataSelector
                .Select(Table.Contents, new String[] { "Genres LIKE '%" + genre.toString() + "%'", "Visibility=1" },
                        new OrderBy[] { OrderBy.Score }, new Arrangement[] { Arrangement.DESC })
                .ToArrayList();

        for (Content content : (ArrayList<Content>) Contents) {
            AddContent(content);
        }

        CheckEmpty(Contents);
        FixHeight();
    }

    public void LoadSubscriptions() {
        Height = 110;

        PageName.setText("Your Subscriptions");

        for (String string : UserController.getCurrentUser().getSubcriptions()) {
            Content content = (Content) DataSelector
                    .Select(Table.Contents, new String[] { "ID='" + string + "'", "Visibility=1" }).GetFirstResult();
            AddContent(content);
        }

        CheckEmpty(UserController.getCurrentUser().getSubcriptions());
        FixHeight();
    }

    public void LoadSearchResults(String search) {
        Height = 110;

        PageName.setText("Search Results");
        ArrayList<?> Channels = DataSelector.Select(Table.Channels, new String[] {
                "ID LIKE '%" + search + "%' OR Name Like '%" + search + "%' OR Contents LIKE '%" + search + "%'" })
                .ToArrayList();

        for (Channel channel : (ArrayList<Channel>) Channels) {
            AddChannel(channel);
        }

        ArrayList<?> Contents = DataSelector.Select(Table.Contents,
                new String[] {
                        "ID LIKE '%" + search + "%' OR Name Like '%" + search + "%' OR Genres LIKE '%" + search + "%'",
                        "Visibility=1" })
                .ToArrayList();

        for (Content content : (ArrayList<Content>) Contents) {
            AddContent(content);
        }

        CheckEmpty(Contents, Channels);
        FixHeight();
    }

    private void AddChannel(Channel channel) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    new File("src/common/visual/component/MediumChannelComponent.fxml").toURI().toURL());
            Parent root = loader.load();
            ((MediumChannelComponent) loader.getController()).ShowChannel(channel);
            MoviesPlace.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Height += 200 + 20;
    }

    private void AddContent(Content content) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/common/visual/MediumMovieComponent.fxml").toURI().toURL());
            Parent root = loader.load();
            ((MediumMovieComponent) loader.getController()).ShowContent(content);
            MoviesPlace.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Height += 250 + 20;
    }

    private void CheckEmpty(ArrayList<?> list) {
        CheckEmpty(list, new ArrayList<>());
    }

    private void CheckEmpty(ArrayList<?> list1, ArrayList<?> list2) {
        if (list1.size() == 0 && list2.size() == 0) {
            MoviesPlace.getChildren().add(MainStructure.GetParent("src/common/visual/component/NotfoundPage.fxml"));
        }
    }

    private void FixHeight() {
        // ((VBox) MoviesPlace.getParent()).setPrefHeight(MoviesPlace.getChildren().size() * (250 + 20) + 110);
        ((VBox) MoviesPlace.getParent()).setPrefHeight(Height);
    }
}
