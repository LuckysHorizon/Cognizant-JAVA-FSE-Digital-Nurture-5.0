public class SmsNotification implements Notification {

    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }

    @Override
    public String getChannel() {
        return "SMS";
    }
}
