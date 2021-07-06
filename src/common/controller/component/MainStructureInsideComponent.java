package common.controller.component;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.controller.BigMovieComponent;
import common.controller.MainStructure;
import common.controller.SmallMovieComponent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Content;

public class MainStructureInsideComponent implements Initializable {
    @FXML
    private Label Title;
    @FXML
    private ImageView MoreBTN;
    @FXML
    private HBox MoviesPlace;
    @FXML
    private ScrollPane Scroll;
    @FXML
    private VBox ToLeft;
    @FXML
    private VBox ToRight;

    private static final int Count = 10;
    private static final double Steps = 3.5 / (double) Count;
    int Counter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CheckArrows();

        ToLeft.setOnMouseClicked(e -> {
            Timeline animation = new Timeline(new KeyFrame(Duration.millis(850),
                    new KeyValue(Scroll.hvalueProperty(), Scroll.getHvalue() - Steps)));
            animation.play();
            animation.setOnFinished(e1 -> CheckArrows());
        });

        ToRight.setOnMouseClicked(e -> {
            Timeline animation = new Timeline(new KeyFrame(Duration.millis(850),
                    new KeyValue(Scroll.hvalueProperty(), Scroll.getHvalue() + Steps)));
            animation.play();
            animation.setOnFinished(e1 -> CheckArrows());
        });
    }

    private void CheckArrows() {
        ToLeft.setVisible(!(Scroll.getHvalue() < 0.01));
        ToRight.setVisible(!(Scroll.getHvalue() > 0.99));
    }

    public void ShowContents(ArrayList<?> arrayList) {
        Counter = 0;
        for (Content content : (ArrayList<Content>) arrayList) {
            Counter++;

            try {
                FXMLLoader loader = new FXMLLoader(
                        new File("src/common/visual/SmallMovieComponent.fxml").toURI().toURL());
                Parent movie = loader.load();
                ((SmallMovieComponent) loader.getController()).ShowContent(content);
                MoviesPlace.getChildren().add(movie);
                movie.setOnMouseClicked(e -> OpenContent(content));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (Counter == Count)
                break;
        }
    }

    private void OpenContent(Content content) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/common/visual/BigMovieComponent.fxml").toURI().toURL());
            Parent parent = loader.load();
            BigMovieComponent controller = loader.getController();
            controller.ShowContent(content);
            MainStructure.OpenPage(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
