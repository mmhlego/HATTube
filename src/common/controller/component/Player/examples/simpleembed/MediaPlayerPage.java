package common.controller.component.Player.examples.simpleembed;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class MediaPlayerPage {

    @FXML
    private WebView Player;

    @FXML
    private AnchorPane SepAnc;

    @FXML
    private Label NameLBL;

    @FXML
    private ImageView CopyIMG;

    @FXML
    private ImageView DownloadIMG;

    @FXML
    private ImageView PcpIMG;

    @FXML
    private Label FullScreenBTN;

    int size = 530;
    int i = 0;

    public void OpenMediaPlayer(String url) {
        String content =
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "\n" +
                        "    <title>Video.js | HTML5 Video Player</title>\n" +
                        "    <link href=\"http://vjs.zencdn.net/7.0/video-js.min.css\" rel=\"stylesheet\">\n" +
                        "    <script src=\"http://vjs.zencdn.net/7.0/video.min.js\"></script>\n" +
                        "\n" +
                        "</head>\n" +
                        "<body style=\"margin: 0;padding: 0;background-color: black;\">\n" +
                        "\n" +
                        "  <video style=\"margin: auto\" id=\"example_video_1\" class=\"video-js\" controls preload=\"none\" width=\"auto\" height=\"" + size + "\" data-setup=\"{}\">\n" +
                        "    <source src=\"" + url + "\" type=\"video/mp4\">\n" +
                        "    <p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href=\"https://videojs.com/html5-video-support/\" target=\"_blank\">supports HTML5 video</a></p>\n" +
                        "  </video>\n" +
                        "\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>\n";
        Player.getEngine().setJavaScriptEnabled(true);
        Player.getEngine().loadContent(content);
        PcpIMG.setOnMouseClicked(e -> {
            System.out.println("1");
            try {
                FXMLLoader loader = new FXMLLoader(new File("src/common/visual/component/PCPMediaPlayer.fxml").toURI().toURL());
                Scene scene = new Scene(loader.load());
                PCPMediaPlayer c = loader.getController();
                c.OpenMediaPlayer(url);
                scene.setFill(Color.TRANSPARENT);
                Stage stage = new Stage(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                ((AnchorPane) PcpIMG.getParent().getParent()).getChildren().clear();
                ((AnchorPane) PcpIMG.getParent().getParent()).getScene().getWindow().hide();

            } catch (IOException malformedURLException) {
                malformedURLException.printStackTrace();
            }
        });
        FullScreenBTN.setOnMouseClicked(e -> {
            size = (int) Screen.getScreens().get(0).getBounds().getHeight() - 10;
            OpenMediaPlayer(url);
            Stage s = new Stage();
            AnchorPane.setRightAnchor(Player, 0.0);
            AnchorPane.setBottomAnchor(Player, 0.0);
            AnchorPane.setTopAnchor(Player, 0.0);
            AnchorPane.setLeftAnchor(Player, 0.0);
            AnchorPane pane = new AnchorPane(Player);
            Scene scene = new Scene(pane);
            s.setScene(scene);
            s.setFullScreen(true);
            s.show();
            i = 0;
            scene.setOnKeyPressed(e1 -> {
                if (e1.getCode().equals(KeyCode.ESCAPE)) {
                    size = 529;
                    AnchorPane.setRightAnchor(Player, 0.0);
                    AnchorPane.setBottomAnchor(Player, 90.0);
                    AnchorPane.setTopAnchor(Player, 0.0);
                    AnchorPane.setLeftAnchor(Player, 0.0);
                    ((AnchorPane) PcpIMG.getParent().getParent()).getChildren().add(Player);
                    OpenMediaPlayer(url);
                    FullScreenBTN.toFront();
                    s.hide();
                }
            });
        });
    }

}
