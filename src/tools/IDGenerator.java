package tools;

import java.util.UUID;

public class IDGenerator {
    private static String RandomID(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    public static String RandomID(String suffix, int length) {
        return suffix + "-" + RandomID(length);
    }
}
