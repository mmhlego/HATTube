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
            Con = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com/sql11423044", "sql11423044",
                    "Qu6KFNnmTP");

            return Con != null;

        } catch (SQLException e) {
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
