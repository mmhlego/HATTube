package user.controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AccessPage {

    @FXML
    private Label AccessIdLBL;

    @FXML
    private JFXToggleButton RegularUserTGL;

    @FXML
    private JFXToggleButton PermiumUserTGL;

    @FXML
    private JFXToggleButton CreateChannelTGL;

    @FXML
    private JFXToggleButton EditchannelTGL;

    @FXML
    private JFXToggleButton RestrictChannelTGL;

    @FXML
    private JFXToggleButton CreateContentTGL;

    @FXML
    private JFXToggleButton EditContentTGL;

    @FXML
    private JFXToggleButton RestrictContentTGL;

    @FXML
    private JFXToggleButton EditUserTGL;

    @FXML
    private JFXToggleButton BanUserTGL;

    @FXML
    private JFXToggleButton EditAdminTGL;

    @FXML
    private JFXToggleButton BanAdminTGL;

    @FXML
    private ImageView ExitBTN;

}
