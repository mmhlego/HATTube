package tools;

import java.io.*;

public class RememberMe implements Serializable {

    public String Username, Password;

    public RememberMe(String username, String password) {
        Username = username;
        Password = password;
    }

    public static void SetRemember(RememberMe rm) {
        try {
            File Remembered = new File(System.getProperty("user.dir") + "/.AppData");
            if (!Remembered.exists()) {
                Remembered.mkdir();
            }
            ObjectOutputStream OOS = new ObjectOutputStream(
                    new FileOutputStream(System.getProperty("user.dir") + "\\.AppData\\Data.np"));
            OOS.writeObject(rm);
            OOS.flush();
            OOS.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RememberMe GetRemember() {
        RememberMe rm = null;
        try {
            File Remembered = new File(System.getProperty("user.dir") + "\\.AppData\\Data.np");
            if (Remembered.exists()) {
                ObjectInputStream OIS = new ObjectInputStream(
                        new FileInputStream(System.getProperty("user.dir") + "\\.AppData\\Data.np"));
                rm = (RememberMe) OIS.readObject();
                OIS.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rm;
    }

    public static void RemoveRemember() {
        try {
            File Remembered = new File(System.getProperty("user.dir") + "\\.AppData\\Data.np");
            Remembered.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
