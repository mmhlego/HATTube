package api;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javafx.scene.control.Alert.AlertType;
import tools.Dialog;
import user.UserController;

public class OTPSender {
    private static String CurrentOTP = "";
    private static int CurrentState = 0;

    public static int getCurrentState() {
        return CurrentState;
    }

    private static String LoadApiToken() throws IOException {
        Scanner reader = new Scanner(new File("src/api/Token.api.txt"));
        return reader.next();
    }

    public static void SendOTP(String Phone) {
        CurrentState = State.SENDING;
        try {
            String Token = LoadApiToken();

            URL url = new URL("http://89.165.64.251:1500/api/MMHSmsSender?phone=" + Phone + "&Token=" + Token);
            URL Url = new URL("http://127.0.0.1:1000/api/MMHSmsSender?phone=" + Phone + "&token=" + Token);

            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner scanner = new Scanner(Url.openStream());
            CurrentOTP = scanner.next();
            CurrentState = State.RECIEVED;
            Dialog.Alert(AlertType.INFORMATION, "Code",
                    "A Verification Code Has Been Sent To " + UserController.getCurrentUser().getPhone().substring(0, 2)
                            + "*****" + UserController.getCurrentUser().getPhone().substring(8, 12));
        } catch (IOException e) {
            Dialog.Alert(AlertType.ERROR, "Error", "Check Your Internet Connection !");
            CurrentState = State.ERROR;
            e.printStackTrace();
        }
    }

    // public static int CreateOTP() {
    // SecureRandom random = new SecureRandom();
    // int otp = random.nextInt(100000) + random.nextInt(100000);
    // if (otp > 100000 && otp < 999999) {
    // CurrentOTP = String.valueOf(otp);
    // CurrentState = State.RECIEVED;
    // return otp;
    // } else {
    // return CreateOTP();
    // }
    // }

    public static boolean CheckOTP(String otp) {
        return otp.equals(CurrentOTP) && CurrentState == State.RECIEVED;
    }

    public class State {
        public final static int SENDING = 0, RECIEVED = 1, ERROR = 2;

    }

}
