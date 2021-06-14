package tools;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class Dialog {
    public static void Alert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
