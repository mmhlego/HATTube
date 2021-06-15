package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.*;

public class DataAdder {

    public static void AddData(Object obj) {
        PreparedStatement ps;
        if (obj instanceof User) {
            try {
                ps = DataBase.Con.prepareStatement(
                        "INSERT INTO `Users`(`ID`, `FirstName`, `LastName`, `BirthDate`, `Phone`, `Email`, `Username`, `Password`, `ChannelID`, `AccessID`, `Subscription`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, ((User) obj).getID());
                ps.setString(2, ((User) obj).getFirstName());
                ps.setString(3, ((User) obj).getLastName());
                ps.setDate(4, Date.valueOf(((User) obj).getBirthDate()));
                ps.setString(5, ((User) obj).getPhone());
                ps.setString(6, ((User) obj).getEmail());
                ps.setString(7, ((User) obj).getUsername());
                ps.setString(8, ((User) obj).getPassword());
                ps.setString(9, ((User) obj).getChannel().GetID());
                ps.setInt(10, ((User) obj).getAccessID());
                ps.setString(11, new Gson().toJson(((User) obj).getSubcriptions()));
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Channel) {
            try {
                ps = DataBase.Con.prepareStatement(
                        "INSERT INTO `Channels`(`ID`, `OwnerID`, `Name`, `Contents`) VALUES (?,?,?,?)");
                ps.setString(1, ((Channel) obj).GetID());
                ps.setString(2, ((Channel) obj).getOwnerID());
                ps.setString(3, ((Channel) obj).getChannelName());
                ps.setString(4, ((Channel) obj).getContentsString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Content) {
            try {
                ps = DataBase.Con.prepareStatement(
                        "INSERT INTO `Contents`(`ID`, `Name`, `Description`, `Rate`, `Genres`, `Score`, `Views`, `Likes`, `Info`, `Visibility`, `Poster`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, ((Content) obj).GetID());
                ps.setString(2, ((Content) obj).getName());
                ps.setString(3, ((Content) obj).getDescription());
                ps.setString(4, ((Content) obj).getContentRate().toString());
                ps.setString(5, new Gson().toJson(((Content) obj).getGenres()));
                ps.setDouble(6, ((Content) obj).getScore());
                ps.setLong(7, ((Content) obj).getViews());
                ps.setLong(8, ((Content) obj).getLikes());
                ps.setString(9, new Gson().toJson(((Content) obj).getInfo()));
                ps.setBoolean(10, ((Content) obj).isVisibility());
                ps.setString(11, ((Content) obj).getPoster().toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Link) {
            try {
                ps = DataBase.Con.prepareStatement(
                        "INSERT INTO `Links`(`ID`, `ContentID`, `Url`, `Name`, `Description`) VALUES (?,?,?,?,?)");
                ps.setString(1, ((Link) obj).getID());
                ps.setString(2, ((Link) obj).getContentID());
                ps.setString(3, ((Link) obj).getUrl().toString());
                ps.setString(4, ((Link) obj).getName());
                ps.setString(5, ((Link) obj).getDescription());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj instanceof Comment) {
            try {
                ps = DataBase.Con.prepareStatement(
                        "INSERT INTO `Comments`(`ID`, `WriterID`, `ContentID`, `Text`, `Date`, `Likes`) VALUES (?,?,?,?,?,?)");
                ps.setString(1, ((Comment) obj).getID());
                ps.setString(2, ((Comment) obj).getWriterID());
                ps.setString(3, ((Comment) obj).getContentID());
                ps.setString(4, ((Comment) obj).getText());
                ps.setDate(5, Date.valueOf(((Comment) obj).getDate()));
                ps.setLong(6, ((Comment) obj).getLikes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
