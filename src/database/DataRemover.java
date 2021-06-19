package database;

import java.sql.SQLException;

import database.DataSelector.Table;

public class DataRemover {
    public static void ClearAllTables() {
        for (Table table : Table.values()) {
            ClearTable(table);
        }
    }

    public static void ClearTable(Table table) {
        try {
            DataBase.Con.prepareStatement("DELETE FROM `" + table.toString() + "` WHERE 1").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table " + table.toString() + " has been cleared");
    }
}
