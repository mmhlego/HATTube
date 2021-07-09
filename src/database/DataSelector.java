package database;

import java.nio.channels.Channel;
import java.sql.ResultSet;

import javax.swing.text.AbstractDocument.Content;

import model.Comment;
import model.Link;
import model.User;

public class DataSelector {
    //public static String GetFields(ResultSet resultSet, String columnName) {
    //    return resultSet.getString(columnName);
    //}

    public static CustomResultSet<?> Select(Table table, String[] conditions, OrderBy[] orderBy,
            Arrangement[] arrangement) {
        String Sql = "SELECT * FROM " + table.toString() + " WHERE ";
        Sql += conditions[0];
        for (int i = 1; i < conditions.length; i++)
            Sql += " AND " + conditions[i];

        Sql += " ORDER BY " + orderBy[0].getValue() + " " + arrangement[0].getValue();
        for (int i = 1; i < orderBy.length; i++)
            Sql += " , " + orderBy[i].getValue() + " " + arrangement[i].getValue();

        return Convert(table, DataBase.RunCommand(Sql));
    }

    public static CustomResultSet<?> Select(Table table, String[] conditions) {
        String Sql = "SELECT * FROM " + table.toString() + " WHERE ";
        Sql += conditions[0];
        for (int i = 1; i < conditions.length; i++)
            Sql += " AND " + conditions[i];

        return Convert(table, DataBase.RunCommand(Sql));
    }

    public static CustomResultSet<?> Convert(Table table, ResultSet results) {
        if (table.equals(Table.Users))
            return new CustomResultSet<User>(table, results);
        else if (table.equals(Table.Channels))
            return new CustomResultSet<Channel>(table, results);
        else if (table.equals(Table.Contents))
            return new CustomResultSet<Content>(table, results);
        else if (table.equals(Table.Links))
            return new CustomResultSet<Link>(table, results);
        else if (table.equals(Table.Contents))
            return new CustomResultSet<Comment>(table, results);
        else
            return new CustomResultSet<>(table, results);
    }

    public static CustomResultSet<?> Select(Table table) {
        return Select(table, new String[] { "1" });
    }

    public enum Table {
        Users, Channels, Contents, Links, Comments;
    }

    public enum OrderBy {
        ID("ID"), Score("Score"), Views("Views"), Likes("Likes"), Date("Date"), Rand("Rand()");

        String Value;

        OrderBy(String v) {
            Value = v;
        }

        public String getValue() {
            return Value;
        }
    }

    public enum Arrangement {
        ASC("ASC"), DESC("DESC"), NONE("");

        String Value;

        Arrangement(String v) {
            Value = v;
        }

        public String getValue() {
            return Value;
        }
    }
}
