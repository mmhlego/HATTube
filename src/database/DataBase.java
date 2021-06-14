package database;

import java.sql.*;

import javafx.stage.Stage;

public class DataBase {
    protected static Connection Con;
    protected static Stage LoadingScreen;
    protected static ResultSet Results;

    public static void LoadScreen() {

    }

    public static boolean Connect() {
        try {
            Con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com", "sql4418978", "IFsr6JbPnp");

            return Con != null;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet RunCommand(String Command) throws SQLException {
        Results = Con.createStatement().executeQuery(Command);
        return Results;
    }

    private static void ShowLoading(Runnable runnable) {
        LoadingScreen.show();
        new Thread(runnable).start();
        LoadingScreen.hide();
    }
}
