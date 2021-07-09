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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Genre;
import user.UserController;

import java.io.File;
import java.io.IOException;
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
    @FXML
    private AnchorPane Header;

    private static ScrollPane scrollPane;

    public static ScrollPane getScrollPane() {
        return scrollPane;
    }

    public static void setScrollPane(ScrollPane scrollPane) {
        MainStructure.scrollPane = scrollPane;
    }

    private static VBox Scroll;

    public static VBox getScroll() {
        return Scroll;
    }

    public static void setScroll(VBox scroll) {
        Scroll = scroll;
    }

    private static AnchorPane root, UserAccount, header;
    boolean isWatchlistOpen = false;
    boolean isSearchOpen = false;
    boolean isUserAccountHovered = false;
    public static VBox PopupPane;

    public static void rootBlur() {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(10);
        root.getChildren().get(0).setEffect(gaussianBlur);
        root.getChildren().get(1).setEffect(gaussianBlur);
        PopupPane = new VBox();
        PopupPane.setAlignment(Pos.CENTER);
        VBox parent = new VBox(PopupPane);
        parent.setFillWidth(false);

        Rectangle clip = new Rectangle();

        clip.layoutXProperty().bind(parent.layoutXProperty());
        clip.setLayoutY(0);
        clip.widthProperty().bind(PopupPane.widthProperty());
        clip.heightProperty().bind(PopupPane.heightProperty());

        PopupPane.setClip(new Rectangle(200, 0, 200, 200));
        PopupPane.setClip(clip);

        parent.setAlignment(Pos.CENTER);
        parent.setStyle("-fx-background-color: rgba(255,255,255,0.2)");
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
        root.getChildren().add(parent);
    }

    public static void rootUnBlur() {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(0);
        root.getChildren().get(0).setEffect(gaussianBlur);
        root.getChildren().get(1).setEffect(gaussianBlur);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane = (ScrollPane) main.getChildren().get(0);
        Scroll = InsideComponent;
        root = main;
        header = Header;
        UserAccount = (AnchorPane) GetParent("src/user/visual/UserAccount.fxml");

        LogoLBL.setOnMouseClicked(e -> OpenFirstPage());

        SideBar.setTranslateX(300);
        MenuIMG.setOnMouseClicked(e -> sidebarAnimation());
        ExitIIMG.setOnMouseClicked(e -> Platform.exit());
        MiniIMG.setOnMouseClicked(e -> ((Stage) EndArea.getParent().getScene().getWindow()).setIconified(true));
        WatchlistLBL.setVisible(false);
        AccountLBL.setVisible(false);

        TranslateNode(Sep, 60, false);
        TranslateNode(AccountANC, -85, false);

        UserAccount.setOnMouseEntered(e -> isUserAccountHovered = true);
        UserAccount.setOnMouseExited(e -> {
            header.getChildren().remove(UserAccount);
            isUserAccountHovered = false;
            AccountLBL.setVisible(false);
        });

        AccountIMG.getParent().setOnMouseEntered(e -> {
            AccountLBL.setVisible(true);
            if (UserController.LoggedIn() && header.getChildren().indexOf(UserAccount) == -1) {
                isUserAccountHovered = true;
                header.getChildren().add(UserAccount);
                UserAccount.setLayoutX(AccountIMG.getParent().getParent().getLayoutX() - 85);
                UserAccount.setLayoutY(AccountIMG.getParent().getParent().getLayoutY() + 40);
            }
        });
        AccountIMG.getParent().setOnMouseExited(e -> {
            if (UserController.LoggedIn()) {
                isUserAccountHovered = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        if (!isUserAccountHovered) {
                            header.getChildren().remove(UserAccount);
                            AccountLBL.setVisible(false);
                        }
                    });
                }).start();
            } else {
                AccountLBL.setVisible(false);
            }
        });
        AccountANC.setOnMouseClicked(e -> {
            if (UserController.LoggedIn()) {
                OpenPage("src/user/visual/AccountInfoPage.fxml");
            } else {
                OpenPopup("src/common/visual/Login.fxml");
            }
        });

        WatchlistIMG.getParent().setOnMouseEntered(e -> {
            TranslateNode(Sep, 145, true);
            TranslateNode(AccountANC, 0, true);
        });
        WatchlistIMG.getParent().setOnMouseExited(e -> {
            TranslateNode(Sep, 60, false);
            TranslateNode(AccountANC, -85, false);
        });
        WatchlistANC.setOnMouseClicked((e) -> {
            if (UserController.LoggedIn()) {
                FXMLLoader loader = GetLoader("src/common/visual/ContentPage.fxml");
                try {
                    OpenPage((Parent) loader.load());
                } catch (IOException e1) {
                }
                ((ContentPage) loader.getController()).LoadSubscriptions();
            } else {
                OpenPopup("src/common/visual/Login.fxml");
            }
        });

        SearchIMG.setOnMouseClicked(e -> {
            if (!isSearchOpen) {
                SearchBar.setVisible(true);
                TranslateNode(SearchANC, 211, false);
                ChangeSize(SearchANC, 700);
                SearchIMG.getStyleClass().set(0, "searchBa");
                LogoLBL.setVisible(false);
                isSearchOpen = true;
            } else if (isSearchOpen && SearchBar.getText().equals("")) {
                SearchIMG.getStyleClass().set(0, "iBack");
                SearchIMG.setStyle("-fx-background-color: transparent");
                TranslateNode(SearchANC, 911, false);
                ChangeSize(SearchANC, 0);
                LogoLBL.setVisible(true);
                isSearchOpen = false;
            } else {
                FXMLLoader loader = GetLoader("src/common/visual/ContentPage.fxml");
                try {
                    OpenPage((Parent) loader.load());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ((ContentPage) loader.getController()).LoadSearchResults(SearchBar.getText());
            }
            //HCNT-4
        });

        SideBarListeners();

        new LoadingStage();

        tools.OtherTools.MakeStageMovable(Root, EndArea);

        OpenFirstPage();
    }

    private void SideBarListeners() {
        SideBar.getChildren().get(0).setOnMouseClicked(e -> {
            if (UserController.LoggedIn()) {
                FXMLLoader loader = GetLoader("src/common/visual/ContentPage.fxml");
                try {
                    OpenPage((Parent) loader.load());
                } catch (IOException e1) {
                }
                ((ContentPage) loader.getController()).LoadSubscriptions();
            } else {
                OpenPopup("src/common/visual/Login.fxml");
            }
            sidebarAnimation();
        });

        if (UserController.LoggedIn()) {
            SideBar.getChildren().get(1).setOnMouseClicked(e -> {
                OpenPopup("src/common/visual/Setting.fxml");
                sidebarAnimation();
            });
        } else {
            SideBar.getChildren().remove(1);
        }

        for (Genre genre : Genre.values()) {
            Label label = new Label(genre.toString());
            label.getStyleClass().add("sideItem");
            label.setPrefHeight(50);
            label.setPrefWidth(300);
            label.setTextFill(Color.WHITE);
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 15));
            SideBar.getChildren().add(label);

            label.setOnMouseClicked(e -> {
                System.out.println(genre);
            });
        }
    }

    public static void ClosePopup() {
        root.getChildren().remove(PopupPane.getParent());
        MainStructure.rootUnBlur();
    }

    public static void OpenPopup(String path) {
        OpenPopup(GetParent(path));
    }

    public static void OpenPopup(Parent root) {
        rootBlur();
        PopupPane.getChildren().clear();
        PopupPane.getChildren().add(root);
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
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.3), SideBar);
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

        /* MainStructureInsideComponent MostCommented = CreateCategory("Most Commented");
         TopRated.ShowContents(DataSelector.Select(Table.Contents, new String[] { "Visibility=1" },
                 new OrderBy[] { OrderBy.C }, new Arrangement[] { Arrangement.DESC }).ToArrayList());*/

        MainStructureInsideComponent MostLiked = CreateCategory("Most Liked");
        MostLiked.ShowContents(DataSelector.Select(Table.Contents, new String[] { "Visibility=1" },
                new OrderBy[] { OrderBy.Likes }, new Arrangement[] { Arrangement.DESC }).ToArrayList());

        MainStructureInsideComponent MostViewed = CreateCategory("Most Viewed");
        MostViewed.ShowContents(DataSelector.Select(Table.Contents, new String[] { "Visibility=1" },
                new OrderBy[] { OrderBy.Views }, new Arrangement[] { Arrangement.DESC }).ToArrayList());
    }

    public static FXMLLoader GetLoader(String path) {
        try {
            return new FXMLLoader(new File(path).toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Parent GetParent(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(new File(path).toURI().toURL());
            Parent root = loader.load();
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object GetController(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(new File(path).toURI().toURL());
            loader.load();
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void OpenPage(String path) {
        OpenPage(GetParent(path));
    }

    public static void OpenPage(Parent parent) {
        Scroll.getChildren().clear();
        scrollPane.setVvalue(0.0);
        Scroll.getChildren().add(parent);
    }
}
