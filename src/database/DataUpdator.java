package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.Gson;
import model.*;

public class DataUpdator {

    public static void UpadateData(Object obj) {
        PreparedStatement ps;
        String SQL;
        if (obj instanceof User) {
            try {
                SQL = "UPDATE `Users` SET `FirstName`='[FirstName]',`LastName`='[LastName]',`BirthDate`='[BirthDate]',`Phone`='[Phone]',`Email`='[Email]',`Username`='[Username]',`Password`='[Password]',`ChannelID`='[ChannelID]',`AccessID`=[AccessID],`Subscription`='[Subscription]' WHERE ID='[ID]'";
                SQL = SQL.replace("[FirstName]", ((User) obj).getFirstName());
                SQL = SQL.replace("[LastName]", ((User) obj).getLastName());
                SQL = SQL.replace("[BirthDate]", ((User) obj).getBirthDate().toString());
                SQL = SQL.replace("[Phone]", ((User) obj).getPhone());
                SQL = SQL.replace("[Email]", ((User) obj).getEmail());
                SQL = SQL.replace("[Username]", ((User) obj).getUsername());
                SQL = SQL.replace("[Password]", ((User) obj).getPassword());
                SQL = SQL.replace("[ChannelID]", ((User) obj).getChannelID());
                SQL = SQL.replace("[AccessID]", String.valueOf(((User) obj).getAccessID()));
                SQL = SQL.replace("[Subscription]", new Gson().toJson(((User) obj).getSubcriptions()));
                SQL = SQL.replace("[ID]", ((User) obj).getID());
                ps = DataBase.Con.prepareStatement(SQL);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Channel) {
            try {
                SQL = "UPDATE `Channels` SET `Name`=[Name],`Contents`=[Contents] WHERE `ID`=[ID]";
                SQL = SQL.replace("[Name]", ((Channel) obj).getChannelName());
                SQL = SQL.replace("[Contents]", new Gson().toJson(((Channel) obj).getContents()));
                ps = DataBase.Con.prepareStatement(SQL);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Content) {
            try {
                SQL = "UPDATE `Contents` SET `Name`=[Name],`Description`=[Description],`Rate`=[Rate],`Genres`=[Genres],`Score`=[Score],`Views`=[Views],`Likes`=[Likes],`Info`=[Info],`Visibility`=[Visibility],`Poster`=[Poster] WHERE `ID`=[ID]";
                SQL = SQL.replace("[Name]", ((Content) obj).getName());
                SQL = SQL.replace("[Description]", ((Content) obj).getDescription());
                SQL = SQL.replace("[Rate]", ((Content) obj).getContentRate().toString());
                SQL = SQL.replace("[Genres]", new Gson().toJson(((Content) obj).getGenres()));
                SQL = SQL.replace("[Score]", String.valueOf(((Content) obj).getScore()));
                SQL = SQL.replace("[Views]", ((Content) obj).getViews().toString());
                SQL = SQL.replace("[Likes]", ((Content) obj).getLikes().toString());
                SQL = SQL.replace("[Info]", new Gson().toJson(((Content) obj).getInfo()));
                SQL = SQL.replace("[Visibility]", String.valueOf(((Content) obj).isVisibility()));
                SQL = SQL.replace("[Poster]", ((Content) obj).getPoster().toString());
                SQL = SQL.replace("[ID]", ((Content) obj).GetID());
                ps = DataBase.Con.prepareStatement(SQL);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Link) {
            try {
                SQL = "UPDATE `Links` SET `Url`=[Url],`Name`=[Name],`Description`=[Description] `ID`=[ID]";
                SQL = SQL.replace("[Url]", ((Link) obj).getUrl().toString());
                SQL = SQL.replace("[Name]", ((Link) obj).getName());
                SQL = SQL.replace("[Description]", ((Link) obj).getDescription());
                SQL = SQL.replace("[ID]", new Gson().toJson(((Link) obj).getID()));
                ps = DataBase.Con.prepareStatement(SQL);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Comment) {
            try {
                SQL = "UPDATE `Comments` SET `Likes`=[Likes] `ID`=[ID]";
                SQL = SQL.replace("[Likes]", String.valueOf(((Comment) obj).getLikes()));
                ps = DataBase.Con.prepareStatement(SQL);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void Like(String ID) {
        long Likes;
        PreparedStatement ps;
        try {
            ResultSet r = DataBase.RunCommand("SELECT Likes FROM Contents WHERE ID =" + "\'" + ID + "\'");
            ResultSet r1 = DataBase.RunCommand("SELECT Likes FROM Comments WHERE ID =" + "\'" + ID + "\'");
            if (r.next()) {
                Likes = r.getLong(1);
                ps = DataBase.Con.prepareStatement(
                        "UPDATE `Comments` SET `Likes`=" + (Likes + 1) + " WHERE ID =" + "\'" + ID + "\'");
                ps.executeUpdate();
            } else if (r1.next()) {
                Likes = r1.getLong(1);
                ps = DataBase.Con.prepareStatement(
                        "UPDATE `Contents` SET `Likes`=" + (Likes + 1) + " WHERE ID =" + "\'" + ID + "\'");
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void View(String ID) {
        long Views;
        PreparedStatement ps;
        try {
            ResultSet r = DataBase.RunCommand("SELECT Views FROM Contents WHERE ID =" + "\'" + ID + "\'");
            r.next();
            Views = r.getLong(1);
            ps = DataBase.Con.prepareStatement(
                    "UPDATE `Contents` SET `Views`=" + (Views + 1) + " WHERE ID =" + "\'" + ID + "\'");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
