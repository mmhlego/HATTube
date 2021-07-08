package user;

import common.controller.MainStructure;
import model.User;
import tools.RememberMe;

public class UserController {
    private static User CurrentUser = null;

    public static void LogOut() {
        setCurrentUser(null);
        RememberMe.RemoveRemember();
        MainStructure.OpenFirstPage();
    }

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public static boolean LoggedIn() {
        return CurrentUser != null;
    }
}
