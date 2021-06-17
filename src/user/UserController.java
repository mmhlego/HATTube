package user;

import model.User;

public class UserController {
    private static User CurrentUser = null;

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
