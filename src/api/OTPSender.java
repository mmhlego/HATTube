package api;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javafx.scene.control.Alert.AlertType;
import tools.Dialog;

public class OTPSender {
    private static String CurrentOTP = "";

    private static String LoadApiToken() throws IOException {
        Scanner reader = new Scanner(new File("src/api/Token.api.txt"));
        return reader.next();
    }

    public static void SendOTP(String Phone) {
        try {
            String Token = LoadApiToken();

            //URL url = new URL("http://89.165.64.251:1500/api/MMHSmsSender?phone=" + Phone + "&Token=" + Token);
            URL Url = new URL("http://127.0.0.1:1000/api/MMHSmsSender?phone=" + Phone + "&token=" + Token);

            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner scanner = new Scanner(Url.openStream());
            CurrentOTP = scanner.next();
        } catch (IOException e) {
            Dialog.Alert(AlertType.ERROR, "Error", "Check Your Internet Connection !");
            e.printStackTrace();
        }
    }

    public static boolean CheckOTP(String otp) {
        return otp.equals(CurrentOTP);
    }
}
