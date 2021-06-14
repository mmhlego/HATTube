package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.stage.Stage;

public class DataBase {
    private static Connection Con;
    private static Stage LoadingScreen;

    public static void LoadScreen() {

    }

    public static boolean Connect() throws SQLException {
        Con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com", "sql4418978", "IFsr6JbPnp");

        // TODO

        return Con != null;
    }

    private static ResultSet RunCommand(String command) {
        // TODO
        return null;
    }

    private static void ShowLoading(Runnable runnable) {
        LoadingScreen.show();
        new Thread(runnable).start();
        LoadingScreen.hide();
    }
}
