package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataSelector.Table;

public class CustomResultSet {
    Table DataMode;
    ResultSet Results;

    public CustomResultSet(Table dataMode, ResultSet results) {
        DataMode = dataMode;
        Results = results;
    }

    public ArrayList<?> ToArrayList() {
        ResultSet temp = Results;
        ArrayList<?> ans = null;

        try {
            if (DataMode.equals(Table.Users)) {
                ans = new ArrayList<User>();
            } else if (DataMode.equals(Table.Channels)) {
                ans = new ArrayList<Channel>();
            } else if (DataMode.equals(Table.Contents)) {
                ans = new ArrayList<Content>();
            } else if (DataMode.equals(Table.Links)) {
                ans = new ArrayList<Link>();
            } else if (DataMode.equals(Table.Comments)) {
                ans = new ArrayList<Comment>();
            }

            while (temp.next()) {
                temp.next();
                if (DataMode.equals(Table.Users)) {

                } else if (DataMode.equals(Table.Channels)) {

                } else if (DataMode.equals(Table.Contents)) {

                } else if (DataMode.equals(Table.Links)) {

                } else if (DataMode.equals(Table.Comments)) {

                }
            }
            // TODO
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
