package automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestUtility {

    private static final Random random = new Random();

    public static String generateTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    public static String generateScreenshotName(String testName) {
        return testName + "_" + generateTimestamp() + ".png";
    }

    public static String generateRandomEmail() {
        return "user" + random.nextInt(10000) + "@test.com";
    }

    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
