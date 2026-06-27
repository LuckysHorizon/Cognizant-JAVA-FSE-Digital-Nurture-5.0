package automation.framework;

import automation.data.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HybridFrameworkExample {

    private final KeywordDrivenExample keywordEngine;

    public HybridFrameworkExample() {
        this.keywordEngine = new KeywordDrivenExample();
    }

    public List<String> executeTestScenario(String scenario) {
        List<String> results = new ArrayList<>();
        Map<String, String> data = TestData.getTestData(scenario);

        results.add(keywordEngine.executeKeyword("OPEN_BROWSER", "chrome"));
        results.add(keywordEngine.executeKeyword("NAVIGATE", "http://localhost:8080/login"));
        results.add(keywordEngine.executeKeyword("ENTER_TEXT", "username", data.getOrDefault("username", "")));
        results.add(keywordEngine.executeKeyword("ENTER_TEXT", "password", data.getOrDefault("password", "")));
        results.add(keywordEngine.executeKeyword("CLICK", "loginButton"));
        results.add(keywordEngine.executeKeyword("VERIFY_TEXT", data.getOrDefault("expected", "")));
        results.add(keywordEngine.executeKeyword("CLOSE_BROWSER"));

        return results;
    }
}
