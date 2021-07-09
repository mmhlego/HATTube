package common.controller.component.Player.examples.simpleembed;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class PCPMediaPlayer implements Initializable {
    @FXML
    private WebView Player;
    @FXML
    private Label ReturnBTN;
    @FXML
    private VBox Handle;

    public Label getReturnBTN() {
        return ReturnBTN;
    }

    public void setReturnBTN(Label returnBTN) {
        ReturnBTN = returnBTN;
    }

    int size = 140;
    int i = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tools.OtherTools.MakeStageMovable(Player.getParent(), Handle);
    }

    public void OpenMediaPlayer(String url) {
        String content = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "\n"
                + "    <title>Video.js | HTML5 Video Player</title>\n"
                + "    <link href=\"http://vjs.zencdn.net/7.0/video-js.min.css\" rel=\"stylesheet\">\n"
                + "    <script src=\"http://vjs.zencdn.net/7.0/video.min.js\"></script>\n" + "\n" + "</head>\n"
                + "<body style=\"margin: 0;padding: 0;background-color: black;\">\n" + "\n"
                + "  <video style=\"margin: auto\" id=\"example_video_1\" class=\"video-js\" controls preload=\"none\" width=\"auto\" height=\""
                + size + "\" data-setup=\"{}\">\n" + "    <source src=\"" + url + "\" type=\"video/mp4\">\n"
                + "    <p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href=\"https://videojs.com/html5-video-support/\" target=\"_blank\">supports HTML5 video</a></p>\n"
                + "  </video>\n" + "\n" + "</body>\n" + "\n" + "</html>\n";
        Player.getEngine().setJavaScriptEnabled(true);
        Player.getEngine().loadContent(content);
    }
}
