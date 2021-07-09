package tools;

import java.sql.*;

import database.DataBase;
import javafx.scene.control.TextField;

public class Validator {

    public static final String[] ValidEmails = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com" };

    public static boolean CheckTextFieldsValidaty(TextField[] textfields) {
        for (int i = 0; i < textfields.length; i++) {
            if (textfields[i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean CheckEmailValidaty(String Email) {
        if (Email.indexOf('@') <= 0 || Email.indexOf('@') != Email.lastIndexOf('@')) {
            return false;
        }

        for (int i = 0; i < ValidEmails.length; i++) {
            if (Email.substring(Email.indexOf('@') + 1, Email.length()).toLowerCase().equals(ValidEmails[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean CheckUsername(String Username) {
        try {
            ResultSet r = DataBase.RunCommand("SELECT * FROM Users WHERE Username ='" + Username + "'");
            return r.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
