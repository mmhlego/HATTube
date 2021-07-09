package common.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import common.controller.component.AddLinkPlaceComponent;
import database.DataSelector;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Content;
import model.Link;

public class AddLinkPage {
    @FXML
    private VBox ComponentPlaceBOX;
    @FXML
    private Button AddBTN;
    @FXML
    private Button SaveBTN;

    public void ShowContent(Content content) {
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
