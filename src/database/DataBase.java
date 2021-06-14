package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.stage.Stage;

public class DataBase {
    private static Connection Con;
    private static Stage LoadingScreen;

    public static boolean Connect() throws SQLException {
        Con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_hatbanksystem", "freedbtech_hatuser",
                "hatpassword");

        // TODO

        return Con != null;
    }

    public static ResultSet RunCommand(String command) {
        // TODO
        return null;
    }

    public static void ShowLoading(Runnable runnable) {
        LoadingScreen.show();
        new Thread(runnable).start();
        LoadingScreen.hide();
    }
}
