package common.controller;

import common.controller.component.LoadingStage;
import common.controller.component.MainStructureInsideComponent;
import database.DataSelector;
import database.DataSelector.Arrangement;
import database.DataSelector.OrderBy;
import database.DataSelector.Table;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import user.UserController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStructure implements Initializable {
    @FXML
    private AnchorPane Root;
    @FXML
    private HBox EndArea;
    @FXML
    private Label MiniIMG;
    @FXML
    private Label ExitIIMG;
    @FXML
    private AnchorPane main;
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
    private VBox InsideComponent;
    @FXML
    private VBox SideBar;

    private static ScrollPane scrollPane;
    private static VBox Scroll;
    private static AnchorPane root;
    boolean isWatchlistOpen = false;
    boolean isSearchOpen = false;
    static AnchorPane pane;

    public static void rootBlur() {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(10);
        root.setEffect(gaussianBlur);
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: rgba(255,255,255,0.2)");
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        root.getChildren().add(pane);
    }

    public static void rootUnBlur() {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(0);
        root.setEffect(gaussianBlur);
        root.getChildren().remove(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane = (ScrollPane) main.getChildren().get(0);
        Scroll = InsideComponent;
        root = main;

        LogoLBL.setOnMouseClicked(e -> OpenFirstPage());

        SideBar.setTranslateX(300);
        MenuIMG.setOnMouseClicked(e -> sidebarAnimation());
        ExitIIMG.setOnMouseClicked(e -> Platform.exit());
        MiniIMG.setOnMouseClicked(e -> ((Stage) EndArea.getParent().getScene().getWindow()).setIconified(true));
        WatchlistLBL.setVisible(false);
        AccountLBL.setVisible(false);

        // TranslateNode(Sep, -85, false);
        TranslateNode(Sep, 60, false);
        // TranslateNode(AccountANC, -85, false);
        TranslateNode(AccountANC, -85, false);

        AccountIMG.getParent().setOnMouseEntered(e -> AccountLBL.setVisible(true));
        AccountIMG.getParent().setOnMouseExited(e -> AccountLBL.setVisible(false));

        WatchlistIMG.getParent().setOnMouseEntered(e -> {
            // TranslateNode(Sep, 85, true);
            TranslateNode(Sep, 145, true);
            // TranslateNode(AccountANC, 85, true);
            TranslateNode(AccountANC, 0, true);
        });

        WatchlistIMG.getParent().setOnMouseExited(e -> {
            // TranslateNode(Sep, -85, false);
            TranslateNode(Sep, 60, false);
            // TranslateNode(AccountANC, -85, false);
            TranslateNode(AccountANC, -85, false);
        });

        SearchIMG.setOnMouseClicked(e -> {
            if (!isSearchOpen) {
                SearchBar.setVisible(true);
                TranslateNode(SearchANC, 211, false);
                LogoLBL.setVisible(false);
                ChangeSize(SearchANC, 700);
                SearchIMG.getStyleClass().set(0, "searchBa");
                isSearchOpen = true;
            } else {
                SearchIMG.getStyleClass().set(0, "iBack");
                SearchIMG.setStyle("-fx-background-color: transparent");
                // TranslateNode(SearchANC, 700, false);
                TranslateNode(SearchANC, 911, false);
                LogoLBL.setVisible(true);
                ChangeSize(SearchANC, 0);
                isSearchOpen = false;
            }
        });

        AccountANC.setOnMouseClicked(e -> {
            if (UserController.LoggedIn()) {
                OpenPage("src/user/visual/AccountInfoPage.fxml");
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(new File("src/common/visual/Login.fxml").toURI().toURL());
                    Stage stage = new Stage();
                    Parent parent = loader.load();
                    Scene scene = new Scene(parent);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    rootBlur();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        new LoadingStage();

        tools.OtherTools.MakeStageMovable(Root, EndArea);

        OpenFirstPage();
    }

    private static MainStructureInsideComponent CreateCategory(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    new File("src/common/visual/component/MainStructureInsideComponet.fxml").toURI().toURL());
            Parent parent = loader.load();
            ((Label) ((HBox) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0)).setText(name);
            Scroll.getChildren().add(parent);
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void OpenPage(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(new File(path).toURI().toURL());
            Parent root = loader.load();
            // AnchorPane.setTopAnchor(root, 40.0);
            // ((AnchorPane) EndArea.getParent()).getChildren().add(root);
            OpenPage(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //         // TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), node);
    //         // transition.setByX(dis);
    //         // transition.play();
    //         // transition.setOnFinished(e -> WatchlistLBL.setVisible(s));

    private void TranslateNode(Node node, double finalValue, boolean s) {
        Timeline transition = new Timeline(
                new KeyFrame(Duration.millis(50), new KeyValue(node.layoutXProperty(), finalValue)));
        transition.play();
        transition.setOnFinished(e -> WatchlistLBL.setVisible(s));
    }

    private void ChangeSize(AnchorPane field, double end) {
        KeyValue value = new KeyValue(field.prefWidthProperty(), end);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.1), value);
        Timeline timeline = new Timeline(frame);
        timeline.play();
        if (isSearchOpen)
            timeline.setOnFinished(e -> SearchBar.setVisible(false));
    }

    boolean isSidebarOpen = false;

    private void sidebarAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), SideBar);
        transition.setByX((isSidebarOpen) ? 300 : -300);
        transition.play();
        isSidebarOpen = !isSidebarOpen;
    }

    public static void OpenFirstPage() {
        Scroll.getChildren().clear();
        scrollPane.setVvalue(0.0);

        MainStructureInsideComponent TopRated = CreateCategory("Top Rated");
        TopRated.ShowContents(DataSelector.Select(Table.Contents, new String[] { "Visibility=1" },
                new OrderBy[] { OrderBy.Score }, new Arrangement[] { Arrangement.DESC }).ToArrayList());

        // MainStructureInsideComponent MostCommented = CreateCategory("Most Commented");
        // TopRated.ShowContents(DataSelector.Select(Table.Contents, new String[] { "Visibility=1" },
        //         new OrderBy[] { OrderBy.C }, new Arrangement[] { Arrangement.DESC }).ToArrayList());

        MainStructureInsideComponent MostLiked = CreateCategory("Most Liked");
        MostLiked.ShowContents(DataSelector.Select(Table.Contents, new String[] { "Visibility=1" },
                new OrderBy[] { OrderBy.Likes }, new Arrangement[] { Arrangement.DESC }).ToArrayList());

        MainStructureInsideComponent MostViewed = CreateCategory("Most Viewed");
        MostViewed.ShowContents(DataSelector.Select(Table.Contents, new String[] { "Visibility=1" },
                new OrderBy[] { OrderBy.Views }, new Arrangement[] { Arrangement.DESC }).ToArrayList());
    }

    public static void OpenPage(Parent parent) {
        Scroll.getChildren().clear();
        scrollPane.setVvalue(0.0);
        Scroll.getChildren().add(parent);
    }
}
