package model;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import database.DataSelector;
import database.DataSelector.Table;
import javafx.scene.image.Image;
import tools.Encoder;
import tools.IDGenerator;
import tools.ImageDownloader;
import user.UserController;

public class User extends Unique {
    String FirstName, LastName, Phone, Email, Username, Password;
    LocalDate BirthDate;
    int Age, AccessID = 0;
    String ChannelID;
    ArrayList<String> Subcriptions;

    public String getChannelID() {
        return ChannelID;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public LocalDate getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        BirthDate = birthDate;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getAccessID() {
        return AccessID;
    }

    public void setAccessID(int accessID) {
        AccessID = accessID;
    }

    public ArrayList<String> getSubcriptions() {
        return Subcriptions;
    }

    public void setSubcriptions(ArrayList<String> subcriptions) {
        Subcriptions = subcriptions;
    }

    public User(String id, String firstName, String lastName, String phone, String email, String username,
            String password, LocalDate birthDate, int accessID, String channelID, ArrayList<String> subcriptions) {
        ID = id;
        FirstName = firstName;
        LastName = lastName;
        Phone = phone;
        Email = email;
        Username = username;
        Password = password;
        BirthDate = birthDate;
        CalculateAge(BirthDate);
        AccessID = accessID;
        ChannelID = channelID;
        Subcriptions = subcriptions;
    }

    public User(String firstName, String lastName, String phone, String email, String username, String password,
            LocalDate birthDate) {
        this(GenerateID(), firstName, lastName, phone, email, username, Encoder.EncodePassword(password), birthDate, 0,
                "", new ArrayList<String>());
    }

    public User(String username, String password) {
        this("", "", "", "", "", username, Encoder.EncodePassword(password), LocalDate.now(), 0, "", null);
    }

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
        int temp = AccessID;
        for (int i = 0; i < access.Location; i++)
            temp /= 2;
        return temp % 2 == 1;
    }

    public static boolean Approve(User user) {
        User temp = (User) DataSelector.Select(Table.Users,
                new String[] { "Username='" + user.getUsername() + "'", "Password='" + user.getPassword() + "'" })
                .GetFirstResult();
        UserController.setCurrentUser(temp);
        return temp != null;
    }

    public static String GenerateID() {
        return IDGenerator.RandomID("HUSR", 8);
    }

    public static void CheckImages() {
        ImageDownloader.setDownloaded(false);

        ArrayList<String> Images = new ArrayList<>();
        ArrayList<String> Names = new ArrayList<>();
        Images.add("https://s19.picofile.com/file/8437844600/1.png");
        Names.add("RandomAvatar0");
        Images.add("https://s18.picofile.com/file/8437844618/2.png");
        Names.add("RandomAvatar1");
        Images.add("https://s19.picofile.com/file/8437844626/3.png");
        Names.add("RandomAvatar2");
        Images.add("https://s19.picofile.com/file/8437844634/4.png");
        Names.add("RandomAvatar3");
        Images.add("https://s18.picofile.com/file/8437844642/5.png");
        Names.add("RandomAvatar4");
        Images.add("https://s19.picofile.com/file/8437844668/6.png");
        Names.add("RandomAvatar5");
        Images.add("https://s19.picofile.com/file/8437844684/7.png");
        Names.add("RandomAvatar6");
        Images.add("https://s18.picofile.com/file/8437844700/8.png");
        Names.add("RandomAvatar7");
        Images.add("https://s18.picofile.com/file/8437844718/9.png");
        Names.add("RandomAvatar8");
        Images.add("https://s19.picofile.com/file/8437844734/10.png");
        Names.add("RandomAvatar9");

        int index = 0;
        while (index < Images.size()) {
            if (new File("resource/images/Avatars/" + Names.get(index) + ".png").exists()) {
                Images.remove(index);
            } else {
                index++;
            }
        }

        ImageDownloader.setDownloaded(Images.size() == 0);

        for (int i = 0; i < Images.size(); i++) {
            final String ImageUrl = Images.get(i);
            final String ImageName = Names.get(i);
            final boolean change = i == Images.size() - 1;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageDownloader.DownloadImage(ImageUrl, ImageName, change, "resource/images/Avatars/");
                }
            }).start();
        }
    }

    public void GrantAccess(Access access) {
        int current = 1 << access.Location;
        AccessID = AccessID % current + current + (AccessID / (2 * current)) * 2 * current;
    }

    public void DenyAccess(Access access) {
        int current = 1 << access.Location;
        AccessID = AccessID % current + (AccessID / (2 * current)) * 2 * current;
    }

    public static Image RandomUserImage() {
        try {
            return new Image(new FileInputStream(new File("resource/images/Avatars/RandomAvatar"
                    + new Random(System.currentTimeMillis()).nextInt(10) + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
