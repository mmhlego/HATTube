package database;

import java.sql.*;

import javafx.stage.Stage;

public class DataBase {

    public static Connection Con;
    public static Stage LoadingScreen;
    public static ResultSet Results;

    public static boolean Connect() {
        try {
            Con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com", "sql4418978", "IFsr6JbPnp");
            if (Con != null) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public static ResultSet RunCommand(String Command) throws SQLException {
        Results = null;
        Statement stmt = Con.createStatement();
        Results = stmt.executeQuery(Command);
        return Results;
    }

    public static void ShowLoading(Runnable runnable) {
        LoadingScreen.show();
        new Thread(runnable).start();
        LoadingScreen.hide();
    }
}
