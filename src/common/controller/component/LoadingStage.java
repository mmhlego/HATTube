package common.controller.component;

import common.controller.MainStructure;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class LoadingStage extends Stage {
    public LoadingStage() throws IOException {
        MainStructure.rootBlur();
        this.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(new File("src/common/visual/component/LoadingPage.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        this.setScene(scene);
        this.show();
    }

    public void load() {
        this.show();
    }

    public void unLoad() {
        this.close();
    }
}
