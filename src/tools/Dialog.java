package tools;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Dialog {
    @FXML
    private ImageView CancelBTN;

    @FXML
    private Label TextLBL;

    @FXML
    private Button NoBTN;

    @FXML
    private Button YesBTN;

    public ImageView getCancelBTN() {
        return CancelBTN;
    }

    public void setCancelBTN(ImageView cancelBTN) {
        CancelBTN = cancelBTN;
    }

    public Label getTextLBL() {
        return TextLBL;
    }

    public void setTextLBL(Label textLBL) {
        TextLBL = textLBL;
    }

    public Button getNoBTN() {
        return NoBTN;
    }

    public void setNoBTN(Button noBTN) {
        NoBTN = noBTN;
    }

    public Button getYesBTN() {
        return YesBTN;
    }

    public void setYesBTN(Button yesBTN) {
        YesBTN = yesBTN;
    }

    public static void Alert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
