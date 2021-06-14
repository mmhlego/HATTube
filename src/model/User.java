package model;

import java.time.LocalDate;

public class User extends Unique {
    String FirstName, LastName, Phone, Email, Username, Password;
    LocalDate BirthDate;
    int Age, AccessID = 0;
    Channel channel;

    public void CalculateAge() {
        Age = CalculateAge(BirthDate);
    }

    public static int CalculateAge(LocalDate local) {
        int now = LocalDate.now().getYear() * 365 + LocalDate.now().getMonthValue() * 30
                + LocalDate.now().getDayOfMonth();
        int birth = local.getYear() * 365 + local.getMonthValue() * 30 + local.getDayOfMonth();
        return (now - birth) / 365;
    }

    public boolean HasAccess(Access access) {
        // TODO check access index
        return false;
    }

    public static boolean Approve(User user) {
        //TODO check with db
        return false;
    }

    @Override
    public void GenerateID() {
        // TODO
    }
}
