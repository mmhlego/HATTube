package database;

import model.CustomResultSet;

public class DataSelector {
    //public static String GetFields(ResultSet resultSet, String columnName) {
    //    return resultSet.getString(columnName);
    //}

    public static CustomResultSet Select(Table table, String[] conditions, OrderBy[] orderBy,
            Arrangement[] arrangement) {
        String Sql = "SELECT * FROM " + table.toString() + " WHERE ";
        Sql += conditions[0];
        for (int i = 1; i < conditions.length; i++)
            Sql += " AND " + conditions[i];

        Sql += " ORDER BY " + orderBy[0].toString() + " " + arrangement[0].toString();
        for (int i = 1; i < orderBy.length; i++)
            Sql += " , " + orderBy[i].toString() + " " + arrangement[i].toString();

        return new CustomResultSet(table, DataBase.RunCommand(Sql));
    }

    public static CustomResultSet Select(Table table, String[] conditions) {
        String Sql = "SELECT * FROM " + table.toString() + " WHERE ";
        Sql += conditions[0];
        for (int i = 1; i < conditions.length; i++)
            Sql += " AND " + conditions[i];

        return new CustomResultSet(table, DataBase.RunCommand(Sql));
    }

    public static CustomResultSet Select(Table table) {
        return Select(table, new String[] { "1" });
    }

    public enum Table {
        Users, Channels, Contents, Links, Comments;
    }

    public enum OrderBy {
        ID;
    }

    public enum Arrangement {
        ASC, DESC;
    }
}
