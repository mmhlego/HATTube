package samples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.Channel;
import model.User;
import tools.Encoder;

class UserCreator {
    private static Random random = new Random(System.currentTimeMillis());

    protected static User CreateUser(int count) {
        String ID = User.GenerateID();
        String FirstName = RandomFirstName();
        String LastName = RandomLastName();
        String Phone = RandomPhone();
        String Email = RandomEmail(FirstName, LastName);
        String Username = "User" + count;
        String Password = Encoder.EncodePassword("User" + count);
        int AccessID = RandomAccessID();

        LocalDate BirthDate = RandomBirthDate();

        if (HasChannel(AccessID))
            return new User(ID, FirstName, LastName, Phone, Email, Username, Password, BirthDate, AccessID,
                    Channel.GenerateID(), new ArrayList<String>());

        return new User(ID, FirstName, LastName, Phone, Email, Username, Password, BirthDate, AccessID, "",
                new ArrayList<String>());
    }

    private static String RandomFirstName() {
        String[] FirstNames = new String[] { "Aaren", "Abbey", "Belita", "Bell", "Candy", "Caprice", "Darell",
                "Darelle", "Edin", "Edita", "Farrand", "Faun", "Gayleen", "Gaylene", "Harriott", "Hatti", "Ivory",
                "Ivy", "Jaclin", "Jaclyn", "Karalynn", "Kare", "Larissa", "Lark", "Mady", "Mae", "Nanon", "Nola", "Ora",
                "Otha", "Perry", "Robinia", "Sally", "Shandee", "Theresa", "Timmy", "Uta", "Val", "Wilie", "Winny",
                "Yoko", "Zara", "Zsazsa" };

        return FirstNames[random.nextInt(FirstNames.length)];
    }

    private static String RandomLastName() {
        String[] LastNames = new String[] { "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis",
                "Garcia", "Rodriguez", "Wilson" };

        return LastNames[random.nextInt(LastNames.length)];
    }

    private static String RandomPhone() {
        String Phone = "09";
        for (int i = 0; i < 9; i++)
            Phone += random.nextInt(10);
        return Phone;
    }

    private static String RandomEmail(String firstName, String LastName) {
        String[] Emails = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com" };
        if (random.nextBoolean())
            return LastName + "." + firstName + "@" + Emails[random.nextInt(Emails.length)];
        return firstName + "." + LastName + "@" + Emails[random.nextInt(Emails.length)];
    }

    private static LocalDate RandomBirthDate() {
        int Year = 1991 + random.nextInt(16);
        int Month = random.nextInt(12) + 1;
        int Day = random.nextInt(29) + 1;
        return LocalDate.of(Year, Month, Day);
    }

    private static int RandomAccessID() {
        int AccessID = 0;

        if (random.nextInt(5) > 0) { //user
            AccessID += 1; // mode(0)
            if (random.nextInt(5) == 4)
                AccessID += (2); // premium(1)

            if (random.nextInt(5) == 4)
                AccessID += (4 + 8 + 32 + 64); // createChan(2) editChan(3) createCont(5) EditCont(6)
        } else { //admin
            if (random.nextInt(3) > 0)
                AccessID += (8 + 16);
            AccessID += (16 + 128); // restrictChan(4) restrictCont(7)
            if (random.nextInt(3) == 0)
                AccessID += (256 + 512); // editUser(8) BanUser(9)
            if (random.nextInt(10) == 0)
                AccessID += (1024 + 2048); //editAdmin(10) banAdmin(11)
        }

        if (random.nextInt(3) == 2)
            AccessID += 4;

        return AccessID;
    }

    private static boolean HasChannel(int accessID) {
        return (accessID / 4) % 2 == 1;
    }
}
