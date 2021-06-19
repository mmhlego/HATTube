package common.controller.component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class LoadingStage extends Stage {
    public LoadingStage() {
        //MainStructure.rootBlur();
        try {
            this.initStyle(StageStyle.TRANSPARENT);
            FXMLLoader loader = new FXMLLoader(
                    new File("src/common/visual/component/LoadingPage.fxml").toURI().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            this.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.show();
    }

    public void load() {
        this.show();
    }

    public void unLoad() {
        this.close();
    }
}
