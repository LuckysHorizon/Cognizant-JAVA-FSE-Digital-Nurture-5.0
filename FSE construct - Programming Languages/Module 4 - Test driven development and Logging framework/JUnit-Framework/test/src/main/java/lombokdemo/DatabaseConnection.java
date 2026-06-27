package lombokdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DatabaseConnection {

    private final String url;
    private final String username;

    public String connect() {
        log.info("Connecting to database: {} with user: {}", url, username);
        log.debug("Connection parameters initialized");
        return "Connected to " + url;
    }

    public void disconnect() {
        log.info("Disconnecting from database: {}", url);
    }

    public String executeQuery(String query) {
        log.info("Executing query: {}", query);
        if (query == null || query.isBlank()) {
            log.error("Invalid query provided");
            throw new IllegalArgumentException("Query cannot be blank");
        }
        log.debug("Query executed successfully");
        return "Result for: " + query;
    }
}
