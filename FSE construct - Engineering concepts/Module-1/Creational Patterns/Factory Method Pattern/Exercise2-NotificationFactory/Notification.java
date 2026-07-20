public interface Notification {

    void send(String recipient, String message);

    String getChannel();
}
