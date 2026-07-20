public class Main {

    public static void main(String[] args) {
        NotificationFactory emailFactory = new EmailNotificationFactory();
        emailFactory.dispatch("user@example.com", "Your report is ready.");

        System.out.println("--------------------------------------------------");

        NotificationFactory smsFactory = new SmsNotificationFactory();
        smsFactory.dispatch("+15550199", "Your one-time code is 8492");

        System.out.println("--------------------------------------------------");

        NotificationFactory pushFactory = new PushNotificationFactory();
        pushFactory.dispatch("device_token_xyz123", "New message received!");
    }
}
