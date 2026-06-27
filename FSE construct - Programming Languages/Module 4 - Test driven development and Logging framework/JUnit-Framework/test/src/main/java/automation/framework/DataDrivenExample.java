package automation.framework;

import automation.data.TestData;

import java.util.Map;

public class DataDrivenExample {

    public String executeLoginTest(String scenario) {
        Map<String, String> data = TestData.getTestData(scenario);
        if (data.isEmpty()) {
            return "No test data found for scenario: " + scenario;
        }
        String username = data.get("username");
        String password = data.get("password");
        return simulateLogin(username, password);
    }

    private String simulateLogin(String username, String password) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "Login Successful";
        }
        return "Invalid Credentials";
    }
}
