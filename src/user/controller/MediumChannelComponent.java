package user.controller;

import java.io.File;

import common.controller.MainStructure;
import database.DataSelector;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Channel;
import model.User;

public class MediumChannelComponent {
    @FXML
    private ImageView AvatarIMG;
    @FXML
    private Label ChannelCreatorNameLBL;
    @FXML
    private Label ChannelNameLBL;
    @FXML
    private Button FollowBTN;
    @FXML
    private Button OpenChannelBTN;

    public void ShowChannel(Channel channel) {
        AvatarIMG.setImage(User.RandomUserImage());
        ChannelCreatorNameLBL
                .setText(DataSelector.Select(Table.Users, new String[] { "ID='" + channel.getOwnerID() + "'" })
                        .GetColumn("Username").get(0));
        ChannelNameLBL.setText(channel.getChannelName());
        OpenChannelBTN.setOnMouseClicked(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(new File("src/user/visual/ChannelPage.fxml").toURI().toURL());
                MainStructure.OpenPage((Parent) loader.load());
                ((ChannelPage) loader.getController()).ShowChannel(channel);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
