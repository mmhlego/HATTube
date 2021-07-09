package user.controller;

import common.controller.MainStructure;
import common.controller.MediumMovieComponent;
import database.DataSelector;
import database.DataSelector.Table;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Channel;
import model.Content;
import model.User;
import user.UserController;

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
        AvatarIMG.setImage(User.RandomUserImage());
        ChannelCreatorNameLBL
                .setText(DataSelector.Select(Table.Users, new String[] { "ID='" + channel.getOwnerID() + "'" })
                        .GetColumn("Username").get(0));
        ChannelNameLBL.setText(channel.getChannelName());
        for (String content : channel.getContents()) {
            AddContent(content);
        }

        if (channel.getOwnerID().endsWith(UserController.getCurrentUser().getID())) {
            AddAddButton();
        }
    }

    private void AddAddButton() {
        Parent parent = MainStructure.GetParent("src/common/visual/component/AddComponent.fxml");
        ((AnchorPane) parent).setMaxWidth(700);

        ChannelMoviesBOX.getChildren().add(parent);
        parent.setOnMouseClicked(e -> {
            MainStructure.OpenPage("src/user/visual/AddContentPage.fxml");
        });
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
