package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataSelector.Table;

public class CustomResultSet<T> {
    Table DataMode;
    ResultSet Results;

    public CustomResultSet(Table dataMode, ResultSet results) {
        DataMode = dataMode;
        Results = results;
    }

    public ArrayList<?> ToArrayList() {
        ResultSet temp = Results;
        ArrayList<T> ans = new ArrayList<T>();

        try {
            while (temp.next()) {
                temp.next();
                if (DataMode.equals(Table.Users)) {

                } else if (DataMode.equals(Table.Channels)) {

                } else if (DataMode.equals(Table.Contents)) {

                } else if (DataMode.equals(Table.Links)) {

                } else if (DataMode.equals(Table.Comments)) {

                }
            }
            // TODO (need Constructors)
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public Object GetFirstResult() {
        try {
            ResultSet temp = Results;
            temp.next();
            if (DataMode.equals(Table.Users)) {

            } else if (DataMode.equals(Table.Channels)) {

            } else if (DataMode.equals(Table.Contents)) {

            } else if (DataMode.equals(Table.Links)) {

            } else if (DataMode.equals(Table.Comments)) {

            }
            // TODO
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
