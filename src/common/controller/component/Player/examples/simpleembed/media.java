package common.controller.component.Player.examples.simpleembed;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class media implements Initializable {

    @FXML
    private WebView m;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String content = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "\n"
                + "    <title>Video.js | HTML5 Video Player</title>\n"
                + "    <link href=\"http://vjs.zencdn.net/7.0/video-js.min.css\" rel=\"stylesheet\">\n"
                + "    <script src=\"http://vjs.zencdn.net/7.0/video.min.js\"></script>\n" + "\n" + "</head>\n"
                + "<body>\n" + "\n"
                + "  <video id=\"example_video_1\" class=\"video-js\" controls preload=\"none\" width=\"640\" height=\"264\" poster=\"http://vjs.zencdn.net/v/oceans.png\" data-setup=\"{}\">\n"
                + "    <source src=\"http://vjs.zencdn.net/v/oceans.mp4\" type=\"video/mp4\">\n"
                + "    <source src=\"http://vjs.zencdn.net/v/oceans.webm\" type=\"video/webm\">\n"
                + "    <source src=\"http://vjs.zencdn.net/v/oceans.ogv\" type=\"video/ogg\">\n"
                + "    <track kind=\"captions\" src=\"../shared/example-captions.vtt\" srclang=\"en\" label=\"English\">\n"
                + "    <track kind=\"subtitles\" src=\"../shared/example-captions.vtt\" srclang=\"en\" label=\"English\">\n"
                + "    <p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href=\"https://videojs.com/html5-video-support/\" target=\"_blank\">supports HTML5 video</a></p>\n"
                + "  </video>\n" + "\n" + "</body>\n" + "\n" + "</html>\n";
        File f = new File("src/main/java/view/player/index.html");
        m.getEngine().setJavaScriptEnabled(true);
        m.getEngine().loadContent(content);
    }
}
