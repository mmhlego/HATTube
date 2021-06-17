import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Content;
import model.Genre;
import model.Rate;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import database.DataAdder;
import database.DataBase;

public class Main extends Application {
    public static void main(String[] args) throws MalformedURLException {
        // DataBase.LoadScreen();
        // launch(args);

        // System.out.println(DataBase.Connect());
        // System.out.println(DataSelector.Select(Table.Users).GetFirstResult());
        DataBase.Connect();
        Content c = new Content("The Conjuring 3", "Horror Movie Realesed In 2021", Rate.G, new ArrayList<Genre>(), 9.2,
                new String[][] { { "Time", "120min" } }, true,
                new URL("https://www.planetware.com/wpimages/2019/10/switzerland-in-pictures-most-beautiful-places-matterhorn.jpg"));
        // DataAdder.AddData(c);
        // System.out.println("Done");
        System.out.println(c.GenerateInvitationLink());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/common/visual/StartPage.fxml").toURI().toURL());
        //
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
