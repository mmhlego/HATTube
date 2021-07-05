import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import database.DataBase;

public class Main extends Application {
    public static void main(String[] args) {
        // launch(args);
        //samples.FullDataCreator.CreateData(4);

        // model.Content c;
        // try {
        //     c = new model.Content("The Conjuring 3", "Horror Movie Realesed In 2021", model.Rate.G,
        //             new ArrayList<model.Genre>(), 9.2, new String[][] { { "Time", "120min" } }, true,
        //             new URL("https://www.planetware.com/wpimages/2019/10/switzerland-in-pictures-most-beautiful-places-matterhorn.jpg"));

        //     System.out.println(c.GenerateInvitationLink());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
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
