package common.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import database.DataSelector;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Content;
import user.UserController;

public class ContentPage implements Initializable{

    @FXML
    private Label PageName;

    @FXML
    private VBox MoviesPlace;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (String string : UserController.getCurrentUser().getSubcriptions()) {
            Content content = (Content) DataSelector.Select(Table.Contents, new String[] { "ID='" + string + "'" })
                    .GetFirstResult();
            try {
                FXMLLoader loader = new FXMLLoader(
                        new File("src/common/visual/MediumMovieComponent.fxml").toURI().toURL());
                Parent root = loader.load();
                MediumMovieComponent contr
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

}
