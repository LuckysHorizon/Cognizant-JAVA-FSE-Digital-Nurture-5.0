package automation.data;

import java.util.*;

public class TestData {

    private static final Map<String, Map<String, String>> testDataStore = new LinkedHashMap<>();

    static {
        Map<String, String> validLogin = new LinkedHashMap<>();
        validLogin.put("username", "admin");
        validLogin.put("password", "admin123");
        validLogin.put("expected", "Login Successful");
        testDataStore.put("validLogin", validLogin);

        Map<String, String> invalidLogin = new LinkedHashMap<>();
        invalidLogin.put("username", "wrong");
        invalidLogin.put("password", "wrong123");
        invalidLogin.put("expected", "Invalid Credentials");
        testDataStore.put("invalidLogin", invalidLogin);
    }

    public static Map<String, String> getTestData(String scenario) {
        return testDataStore.getOrDefault(scenario, Collections.emptyMap());
    }

    public static List<Map<String, String>> getAllTestData() {
        return new ArrayList<>(testDataStore.values());
    }

    public static String getValue(String scenario, String key) {
        return testDataStore.getOrDefault(scenario, Collections.emptyMap())
                .getOrDefault(key, "");
    }
}
