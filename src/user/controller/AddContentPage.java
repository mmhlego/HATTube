package user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import api.Uploader;
import common.controller.MainStructure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Genre;
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

    private boolean IsGenreOpen=false;
    VBox Genres=new VBox();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Rate rate : Rate.values()) {
            Rates.add(rate.toString());
        }

        double h=40.0;

        Genres.setPrefWidth(280.0);
        Genres.setPrefHeight(h*Genre.values().length);
        Genres.setStyle("-fx-background-color:#d4d4d4; -fx-background-radius:5;");
        Genres.setLayoutX(0.0);
        Genres.setLayoutY(100.0);

        for(Genre genre:Genre.values()){
            HBox box=new HBox();
            box.setPrefWidth(280.0);

            Label label=new Label("\t"+genre.toString());
            label.setFont(new Font(15));
            label.setPrefWidth(240.0);
            label.setPrefHeight(h);

            CheckBox check=new CheckBox();
                        check.setPrefWidth(h);
            check.setPrefHeight(h);

            box.getChildren().add(label);
            box.getChildren().add(check);

            Genres.getChildren().add(box);
        }

        GenersOpenIMG.setOnMouseClicked(e->{
            if(IsGenreOpen){
                ((Group) GenersOpenIMG.getParent()).getChildren().remove(Genres);
            }else{
                ((Group) GenersOpenIMG.getParent()).getChildren().add(Genres);
            }
            IsGenreOpen=!IsGenreOpen;
        });

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

        UploadBTN.setOnAction((e) -> {
            MainStructure.OpenPopup("src/user/visual/UploadComponent.fxml");
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
