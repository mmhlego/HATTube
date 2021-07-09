package common.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Access;
import user.UserController;

public class Setting implements Initializable {

    @FXML
    private AnchorPane LoginAnchor;

    @FXML
    private Label AccountIdLBL;

    @FXML
    private Label AccountModeLBL;

    @FXML
    private TextArea AboutUsArea;

    @FXML
    private ImageView CancelBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CancelBTN.setCursor(Cursor.HAND);

        AccountIdLBL.setText(UserController.getCurrentUser().getID());
        AccountModeLBL.setText(UserController.getCurrentUser().HasAccess(Access.Level) ? "Premium" : "Regular");
        AboutUsArea.setText(
                "\n*** HAT TUBE ***\n\nOur mission is to give everyone a voice and show them the world.\n\n We believe that everyone deserves to have a voice, and that the world is a better place when we listen, share and build community through our stories.\n\n Our values are based on four essential freedoms that define who we are.");

        CancelBTN.setOnMouseClicked((e) -> {
            MainStructure.ClosePopup();
        });
    }

}
