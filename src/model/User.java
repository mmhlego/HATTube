package model;

import java.time.LocalDate;
import java.util.ArrayList;

import tools.IDGenerator;

public class User extends Unique {
    String FirstName, LastName, Phone, Email, Username, Password;
    LocalDate BirthDate;

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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ArrayList<String> getSubcriptions() {
        return Subcriptions;
    }

    public void setSubcriptions(ArrayList<String> subcriptions) {
        Subcriptions = subcriptions;
    }

    int Age, AccessID = 0;
    Channel channel;
    ArrayList<String> Subcriptions;

    public User(String id , String firstName, String lastName, String phone, String email, String username, String password,
            LocalDate birthDate, int accessID, Channel channel, ArrayList<String> subcriptions) {
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
        this.channel = channel;
        Subcriptions = subcriptions;
    }

    public User(String firstName, String lastName, String phone, String email, String username, String password,
            LocalDate birthDate) {
        this(GenerateID() ,firstName, lastName, phone, email, username, password, birthDate, 0, null, new ArrayList<String>());
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
        // TODO check access index
        return false;
    }

    public static boolean Approve(User user) {
        //TODO check with db / with User or Username and password
        return false;
    }

    public static String GenerateID() {
        return IDGenerator.RandomID("HUSR", 8);
    }
}
