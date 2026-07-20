public abstract class NotificationFactory {

    public abstract Notification createNotification();

    public void dispatch(String recipient, String message) {
        Notification notification = createNotification();
        System.out.println("Preparing to dispatch via " + notification.getChannel() + " channel...");
        notification.send(recipient, message);
    }
}
