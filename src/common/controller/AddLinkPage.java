package common.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;

import common.controller.component.AddLinkPlaceComponent;
import database.DataAdder;
import database.DataRemover;
import database.DataSelector;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import model.Content;
import model.Link;

public class AddLinkPage {
    @FXML
    private VBox ComponentPlaceBOX;
    @FXML
    private Button AddBTN;
    @FXML
    private Button SaveBTN;

    private String ContentID;
    private Content CurrentContent;

    public void ShowContent(Content content) {
        ContentID = content.getID();
        CurrentContent = content;

        ArrayList<?> Links = DataSelector.Select(Table.Links, new String[] { "ContentID='" + content.getID() + "'" })
                .ToArrayList();
        for (Link link : (ArrayList<Link>) Links) {
            AddLink(link);
        }

        AddBTN.setOnMouseClicked(e -> {
            try {
                AddLink(new Link(content.getID(), "", "", new URL("http://YourLink.com")));
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        });

        SaveBTN.setOnMouseClicked(e -> SaveLinks());
    }

    private void SaveLinks() {
        for (int i = 1; i < ComponentPlaceBOX.getChildren().size(); i++) {
            HBox box = (HBox) ((AnchorPane) ComponentPlaceBOX.getChildren().get(i)).getChildren().get(0);
            String ID = ((Label) ((AnchorPane) ComponentPlaceBOX.getChildren().get(i)).getChildren().get(1)).getText();
            String Name = ((JFXTextField) box.getChildren().get(1)).getText();
            String Description = ((JFXTextField) box.getChildren().get(3)).getText();
            String Url = ((JFXTextField) box.getChildren().get(5)).getText();
            try {
                Link link = new Link(ID, ContentID, Name, Description, new URL(Url));
                DataRemover.RemoveDate(link);
                DataAdder.AddData(link);
            } catch (MalformedURLException e) {
                tools.Dialog.Alert(AlertType.ERROR, "Invalid Link", "Tour Link is invalid.");
                e.printStackTrace();
                return;
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(new File("src/common/visual/BigMovieComponent.fxml").toURI().toURL());
            Parent parent = loader.load();
            BigMovieComponent controller = loader.getController();
            controller.ShowContent(CurrentContent);
            MainStructure.OpenPage(parent);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void AddLink(Link link) {
        FXMLLoader loader = MainStructure.GetLoader("src/common/visual/component/AddLinkPlaceComponent.fxml");
        try {
            ComponentPlaceBOX.getChildren().add((Parent) loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((AddLinkPlaceComponent) loader.getController()).setLink(link);
    }
}
