package user.controller;

import api.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Rate;

public class AddContentPage implements Initializable {

    @FXML
    private ImageView PosterSelectIMG;

    @FXML
    private TextField NameTXF;

    @FXML
    private TextField GenresIMG;

    @FXML
    private ImageView GenersOpenIMG;

    @FXML
    private ComboBox<String> RateCMB;

    @FXML
    private TextField ScoreTXF;

    @FXML
    private TextArea DescriptionTXF;

    @FXML
    private TextField DetailTXF;

    @FXML
    private Button AddInfoBTN;

    @FXML
    private Button UploadBTN;

    @FXML
    private Button AddContentBTN;

    final static FileChooser fileChooser = new FileChooser();
    private static Stage stage = new Stage();
    ObservableList<String> Rates = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Rate rate : Rate.values()) {
            Rates.add(rate.toString());
        }
        RateCMB.setItems(Rates);
        PosterSelectIMG.setCursor(Cursor.HAND);

        PosterSelectIMG.setOnMouseClicked((e) -> {
            OpenFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    PosterSelectIMG.setImage(new Image(new FileInputStream(file)));
                    URL PosterIMG = Uploader.UploadImage(file);
                    System.out.println(PosterIMG.toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    private static void OpenFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choose Profile Picture");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }

}
