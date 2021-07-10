package common.controller.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.Access;
import model.Link;
import user.UserController;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;

import common.controller.MainStructure;
import common.controller.component.Player.examples.simpleembed.MediaPlayerPage;

public class DownloadLinkComponent {
    @FXML
    private Label LinkDescriptionLBL;
    @FXML
    private Label LinkNameLBL;
    @FXML
    private ImageView CopyToClipboardBTN;
    @FXML
    private ImageView DownloadBTN;
    @FXML
    private ImageView WatchBTN;

    public void SetLink(Link link) {
        LinkDescriptionLBL.setText(link.getDescription());
        LinkNameLBL.setText(link.getName());

        CopyToClipboardBTN.setOnMouseClicked(e -> {
            if (UserController.LoggedIn()) {
                Toolkit.getDefaultToolkit().getSystemClipboard()
                        .setContents(new StringSelection(link.getUrl().toString()), null);
            } else {
                tools.Dialog.Alert(AlertType.ERROR, "Access Denied",
                        "Please Login to your account to copy URL to clipboard.");
            }
        });

        DownloadBTN.setOnMouseClicked(e -> {
            if (UserController.LoggedIn()) {
                if (UserController.getCurrentUser().HasAccess(Access.Level)) {
                    tools.OtherTools.openURL(link.getUrl());
                } else {
                    tools.Dialog.Alert(AlertType.ERROR, "Access Denied",
                            "Download is available for premium accounnts only.");
                }
            } else {
                tools.Dialog.Alert(AlertType.ERROR, "Access Denied",
                        "Please Login to your account to download this content.");
            }
        });

        WatchBTN.setOnMouseClicked(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        new File("src/common/visual/component/MediaPlayerPage.fxml").toURI().toURL());
                Parent player = loader.load();
                MediaPlayerPage controller = loader.getController();
                controller.OpenMediaPlayer(link.getUrl().toString());
                controller.SetMovieName(link.getName());
                // controller.OpenMediaPlayer("https://download.toplearn.com/downloads/demo/01_Html.mp4");
                // controller.OpenMediaPlayer("https://vjs.zencdn.net/v/oceans.mp4");
                MainStructure.OpenPage(player);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
