package automation.framework;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class KeywordDrivenExample {

    private final Map<String, Function<String[], String>> keywords = new LinkedHashMap<>();

    public KeywordDrivenExample() {
        keywords.put("OPEN_BROWSER", args -> "Browser opened: " + (args.length > 0 ? args[0] : "chrome"));
        keywords.put("NAVIGATE", args -> "Navigated to: " + (args.length > 0 ? args[0] : "unknown"));
        keywords.put("ENTER_TEXT", args -> "Entered text '" + (args.length > 1 ? args[1] : "") + "' in " + (args.length > 0 ? args[0] : "unknown"));
        keywords.put("CLICK", args -> "Clicked on: " + (args.length > 0 ? args[0] : "unknown"));
        keywords.put("VERIFY_TEXT", args -> "Verified text: " + (args.length > 0 ? args[0] : "unknown"));
        keywords.put("CLOSE_BROWSER", args -> "Browser closed");
    }

    public String executeKeyword(String keyword, String... args) {
        Function<String[], String> action = keywords.get(keyword.toUpperCase());
        if (action == null) {
            return "Unknown keyword: " + keyword;
        }
        return action.apply(args);
    }

    public Map<String, Function<String[], String>> getAvailableKeywords() {
        return keywords;
    }
}
