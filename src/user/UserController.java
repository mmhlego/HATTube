package user;

import model.User;

public class UserController {
    private static User CurrentUser = null;

    public User GetCurrentUser() {
        return CurrentUser;
    }

    public static boolean LoggedIn() {
        return CurrentUser != null;
    }
}
