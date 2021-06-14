package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class OTPSender {
    private static String CurrentOTP = "";

    public static void SendOTP(String Phone) {
        try {
            URL url = new URL(
                    "http://89.165.64.251:1000/api/MMHSmsSender?username=MMHSMSSenderApi80Username&password=MMHSMSSenderApi80Password&phone="
                            + Phone);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner scanner = new Scanner(url.openStream());
            CurrentOTP = scanner.next();

        } catch (IOException e) {
            // TODO link not found ...
            e.printStackTrace();
        }
    }

    public static boolean CheckOTP(String otp) {
        return otp.equals(CurrentOTP);
    }
}
