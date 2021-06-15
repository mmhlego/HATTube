package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.User;
import user.UserController;

public class DataUpdator {

    public static void UpadateData(Object obj) {
        if (obj instanceof User) {
            try {
                String SQL = "UPDATE `Users` SET `FirstName`='[FirstName]',`LastName`='[LastName]',`BirthDate`='[BirthDate]',`Phone`='[Phone]',`Email`='[Email]',`Username`='[Username]',`Password`='[Password]',`ChannelID`='[ChannelID]',`AccessID`='[AccessID]',`Subscription`='[Subscription]' WHERE ID='HUSR-4c1be7b4'";
                SQL.replace("[FirstName]", ((User) obj).getFirstName());
                SQL.replace("[LastName]", ((User) obj).getLastName());
                SQL.replace("[BirthDate]", ((User) obj).getBirthDate().toString());
                SQL.replace("[Phone]", ((User) obj).getPhone());
                SQL.replace("[Email]", ((User) obj).getEmail());
                SQL.replace("[Username]", ((User) obj).getUsername());
                SQL.replace("[Password]", ((User) obj).getPassword());
                SQL.replace("[ChannelID]", ((User) obj).getChannelID());
                SQL.replace("[AccessID]", String.valueOf(((User) obj).getAccessID()));
                SQL.replace("[Subscription]", new Gson().toJson(((User) obj).getSubcriptions()));
                PreparedStatement ps = DataBase.Con.prepareStatement(SQL);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
