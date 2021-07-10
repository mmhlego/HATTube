package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DataSelector.Table;
import model.Channel;
import model.Comment;
import model.Content;
import model.Link;
import model.User;

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

    public static void RemoveDate(Object obj) {
        PreparedStatement ps;
        if (obj instanceof User) {
            try {
                ps = DataBase.Con.prepareStatement("DELETE FROM Users WHERE ID='" + (((User) obj).getID()) + "'");
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Channel) {
            try {
                ps = DataBase.Con.prepareStatement("DELETE FROM Channels WHERE ID='" + (((Channel) obj).GetID()) + "'");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Content) {
            try {
                ps = DataBase.Con.prepareStatement("DELETE FROM Contents WHERE ID='" + (((Content) obj).getID()) + "'");
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Link) {
            try {
                ps = DataBase.Con.prepareStatement("DELETE FROM Links WHERE ID='" + (((Link) obj).getID()) + "'");
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Comment) {
            try {
                ps = DataBase.Con.prepareStatement("DELETE FROM Comments WHERE ID='" + (((Comment) obj).getID()) + "'");
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
