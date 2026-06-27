package lombokdemo;

import lombok.Value;

@Value
public class ImmutableConfig {
    String host;
    int port;
    String database;
    boolean sslEnabled;
}
