import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {

    private final Map<String, String> settings = new HashMap<>();

    private ConfigurationManager() {
        settings.put("app.name", "DigitalNurture");
        settings.put("app.version", "5.0");
        settings.put("db.host", "localhost");
        settings.put("db.port", "3306");
        settings.put("max.connections", "100");
    }

    private static class Holder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return Holder.INSTANCE;
    }

    public String get(String key) {
        return settings.get(key);
    }

    public void set(String key, String value) {
        settings.put(key, value);
    }

    public boolean contains(String key) {
        return settings.containsKey(key);
    }

    public Map<String, String> getAllSettings() {
        return Map.copyOf(settings);
    }
}
