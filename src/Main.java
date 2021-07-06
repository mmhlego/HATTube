import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        // samples.FullDataCreator.CreateData(10);
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/common/visual/StartPage.fxml").toURI().toURL());
        Parent parent = loader.load();
        /*    MediaPlayerPage c =loader.getController();
        c.OpenMediaPlayer("https://download.toplearn.com/downloads/demo/01_Html.mp4");*/
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
