package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import database.DataSelector.Table;
import model.Channel;
import model.Comment;
import model.Content;
import model.Link;
import model.Rate;
import model.User;

public class CustomResultSet<Type> {
    Table DataMode;
    ResultSet Results;

    public CustomResultSet(Table dataMode, ResultSet results) {
        DataMode = dataMode;
        Results = results;
    }

    public ArrayList<?> ToArrayList() {
        ResultSet temp = Results;
        ArrayList<Object> ans = new ArrayList<>();

        try {
            while (temp.next()) {
                ans.add(ConvertToCurrectForm(temp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public Object GetFirstResult() {
        try {
            ResultSet temp = Results;
            if (temp.next())
                return ConvertToCurrectForm(temp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object ConvertToCurrectForm(ResultSet temp) throws SQLException {
        if (DataMode.equals(Table.Users)) {
            return (new User(temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(5),
                    temp.getString(6), temp.getString(7), temp.getString(8), temp.getDate(4).toLocalDate(),
                    temp.getInt(10), temp.getString(9), new Gson().fromJson(temp.getString(11), ArrayList.class)));

        } else if (DataMode.equals(Table.Channels)) {
            return (new Channel(temp.getString(1), temp.getString(2), temp.getString(3),
                    new Gson().fromJson(temp.getString(1), ArrayList.class)));

        } else if (DataMode.equals(Table.Contents)) {
            return (new Content(temp.getString(1), temp.getString(2), temp.getString(3),
                    Rate.valueOf(temp.getString(4)), new Gson().fromJson(temp.getString(5), ArrayList.class),
                    temp.getDouble(6), temp.getLong(7), temp.getLong(8),
                    new Gson().fromJson(temp.getString(9), String[][].class), temp.getBoolean(10), temp.getURL(11)));

        } else if (DataMode.equals(Table.Links)) {
            return (new Link(temp.getString(1), temp.getString(2), temp.getString(4), temp.getString(5),
                    temp.getURL(3)));

        } else if (DataMode.equals(Table.Comments)) {
            return (new Comment(temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4),
                    temp.getDate(5).toLocalDate(), temp.getLong(6)));
        }
        return null;
    }
}
