package database;

import java.sql.*;

import common.controller.component.LoadingStage;

public class DataBase {
    protected static Connection Con;
    protected static LoadingStage LoadingScreen;
    protected static ResultSet Results;

    public static void LoadScreen() {
        LoadingScreen = new LoadingStage();
    }

    public static boolean Connect() {
        try {
            Con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4418978", "sql4418978",
                    "IFsr6JbPnp");

            return Con != null;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet RunCommand(String Command) {
        try {
            return Con.createStatement().executeQuery(Command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void ShowLoading(Runnable runnable) {
        LoadingScreen.load();
        new Thread(runnable).start();
        LoadingScreen.unLoad();
    }
}
