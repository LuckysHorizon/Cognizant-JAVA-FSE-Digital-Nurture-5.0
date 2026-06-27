package automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = FrameworkConfig.class.getClassLoader()
                .getResourceAsStream("framework.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load framework configuration", e);
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url", "http://localhost:8080");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "10"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
