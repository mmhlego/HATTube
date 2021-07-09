package user.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.controller.MainStructure;
import common.controller.MediumMovieComponent;
import database.DataSelector;
import database.DataSelector.Table;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Channel;
import model.Content;

public class ChannelPage {

    @FXML
    private ImageView AvatarIMG;

    @FXML
    private Label ChannelCreatorNameLBL;

    @FXML
    private Label ChannelNameLBL;

    @FXML
    private Button FollowBTN;

    @FXML
    private VBox ChannelMoviesBOX;

    public void ShowChannel(String ID) {
        Channel channel = (Channel) DataSelector.Select(Table.Channels, new String[] { "ID='" + ID + "'" })
                .GetFirstResult();
        ShowChannel(channel);
    }

    public void ShowChannel(Channel channel) {

        ChannelCreatorNameLBL
                .setText(DataSelector.Select(Table.Users, new String[] { "ID='" + channel.getOwnerID() + "'" })
                        .GetColumn("Username").get(0));
        ChannelNameLBL.setText(channel.getChannelName());
        for (String content : channel.getContents()) {
            AddContent(content);
        }

    }

    public void AddContent(String ID) {
        Content content = (Content) DataSelector.Select(Table.Contents, new String[] { "ID='" + ID + "'" })
                .GetFirstResult();
        FXMLLoader loader = MainStructure.GetLoader("src/common/visual/MediumMovieComponent.fxml");
        try {
            ChannelMoviesBOX.getChildren().add(loader.load());
            ((MediumMovieComponent) loader.getController()).ShowContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
