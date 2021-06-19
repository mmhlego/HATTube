package common.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import user.UserController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStructure implements Initializable {

    boolean isSearchOpen = false;
    @FXML
    private HBox EndArea;
    @FXML
    private Label MiniIMG;
    @FXML
    private Label ExitIIMG;
    @FXML
    private Label LogoLBL;
    @FXML
    private ImageView MenuIMG;
    @FXML
    private AnchorPane WatchlistANC;
    @FXML
    private ImageView WatchlistIMG;
    @FXML
    private Label WatchlistLBL;
    @FXML
    private Separator Sep;
    @FXML
    private AnchorPane AccountANC;
    @FXML
    private ImageView AccountIMG;
    @FXML
    private Label AccountLBL;
    @FXML
    private AnchorPane SearchANC;
    @FXML
    private TextField SearchBar;
    @FXML
    private AnchorPane SearchIMG;
    @FXML
    private AnchorPane Root;
    private static AnchorPane root;

    public static void rootBlur() {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(10);
        root.setEffect(gaussianBlur);
        AnchorPane pane = new AnchorPane();
        pane.setStyle("-fx-background-color: rgba(255,255,255,0.2)");
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        root.getChildren().add(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root = Root;
        ExitIIMG.setOnMouseClicked(e -> Platform.exit());
        MiniIMG.setOnMouseClicked(e -> ((Stage) EndArea.getParent().getScene().getWindow()).setIconified(true));
        EndArea.setOnMouseEntered(e -> EndArea.setCursor(Cursor.OPEN_HAND));
        EndArea.setOnMousePressed(e -> EndArea.setCursor(Cursor.CLOSED_HAND));
        EndArea.setOnMouseReleased(e -> EndArea.setCursor(Cursor.OPEN_HAND));
        WatchlistLBL.setVisible(false);
        AccountLBL.setVisible(false);
        TranslateToBack(Sep, -85);
        TranslateToBack(AccountANC, -85);

        WatchlistIMG.setOnMouseEntered(e -> {
            TranslateToFront(Sep);
            TranslateToFront(AccountANC);
        });

        AccountIMG.setOnMouseEntered(e -> AccountLBL.setVisible(true));
        WatchlistIMG.setOnMouseExited(e -> {
            TranslateToBack(Sep, -85);
            TranslateToBack(AccountANC, -85);
        });

        AccountIMG.setOnMouseExited(e -> AccountLBL.setVisible(false));
        SearchIMG.setOnMouseClicked(e -> {
            if (!isSearchOpen) {
                SearchBar.setVisible(true);
                TranslateToBack(SearchANC, -700);
                LogoLBL.setVisible(false);
                ChangeSize(SearchANC);
            }
        });

        AccountANC.setOnMouseClicked(e -> {
            if (UserController.LoggedIn()) {
                // OpenPage("src/common/visual/Login.fxml"); TODO Profile
            } else
                OpenPage("src/common/visual/Login.fxml");
        });
        /*  try {
            new LoadingStage();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void OpenPage(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(new File(path).toURI().toURL());
            Parent root = loader.load();
            AnchorPane.setTopAnchor(root, 40.0);
            ((AnchorPane) EndArea.getParent()).getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void TranslateToBack(Node node, double dis) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), node);
        transition.setByX(dis);
        transition.play();
        transition.setOnFinished(e -> WatchlistLBL.setVisible(false));
    }

    private void TranslateToFront(Node node) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), node);
        transition.setByX(85);
        transition.play();
        transition.setOnFinished(e -> WatchlistLBL.setVisible(true));
    }

    private void ChangeSize(AnchorPane field) {
        KeyValue value = new KeyValue(field.prefWidthProperty(), 700);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.3), value);
        Timeline timeline = new Timeline(frame);
        timeline.play();
    }
}
