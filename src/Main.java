import java.io.File;
import java.time.LocalDate;

import database.DataAdder;
import database.DataBase;
import database.DataSelector;
import database.DataUpdator;
import database.DataSelector.Table;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;

public class Main extends Application {
    public static void main(String[] args) {
        // DataBase.LoadScreen();
        // launch(args);
        DataBase.Connect();
        // DataSelector.Select(Table.Users).ToArrayList();
        User u = new User("Arya", "Tabani", "09394397528", "k.tabani82@gmail.com", "CyberGhost", "HAT_Cyber",
                LocalDate.now());
        // DataAdder.AddData(u);

        DataUpdator.UpadateData(u);
        System.out.println("Done");
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
