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
        //Content c;
        //try {
        //    c = new Content("The Conjuring 3", "Horror Movie Realesed In 2021", Rate.G, new ArrayList<Genre>(), 9.2,
        //            new String[][] { { "Time", "120min" } }, true,
        //            new URL("https://www.planetware.com/wpimages/2019/10/switzerland-in-pictures-most-beautiful-places-matterhorn.jpg"));
        //
        //    System.out.println(c.GenerateInvitationLink());
        //} catch (MalformedURLException e) {
        //    e.printStackTrace();
        //}
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/common/visual/StartPage.fxml").toURI().toURL());
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
