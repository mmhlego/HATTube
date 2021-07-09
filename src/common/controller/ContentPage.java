package common.controller;

import java.io.File;
import java.util.ArrayList;

import database.DataSelector;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Content;
import user.UserController;

public class ContentPage {
    @FXML
    private Label PageName;
    @FXML
    private VBox MoviesPlace;

    public void LoadSubscriptions() {
        PageName.setText("Your Subscriptions");
        for (String string : UserController.getCurrentUser().getSubcriptions()) {
            Content content = (Content) DataSelector.Select(Table.Contents, new String[] { "ID='" + string + "'" })
                    .GetFirstResult();
            AddContent(content);
        }
    }

    public void LoadSearchResults(String search) {
        PageName.setText("Search Results");
        ArrayList<?> Contents = DataSelector
                .Select(Table.Contents, new String[] { "ID LIKE '%" + search + "%' OR Name Like '%" + search + "%'" })
                .ToArrayList();
        for (Content content : (ArrayList<Content>) Contents) {
            AddContent(content);
        }
    }

    private void AddContent(Content content) {
        System.out.println(content.getName());
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/common/visual/MediumMovieComponent.fxml").toURI().toURL());
            Parent root = loader.load();
            ((MediumMovieComponent) loader.getController()).ShowContent(content);
            MoviesPlace.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((VBox) MoviesPlace.getParent()).setPrefHeight(MoviesPlace.getChildren().size() * (250 + 20) + 110);
    }
}
