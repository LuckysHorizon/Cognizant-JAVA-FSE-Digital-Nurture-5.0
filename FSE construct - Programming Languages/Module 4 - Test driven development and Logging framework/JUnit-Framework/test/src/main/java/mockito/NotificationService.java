package mockito;

public class NotificationService {

    private final EmailService emailService;
    private final SmsService smsService;

    public NotificationService(EmailService emailService, SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void notifyByEmail(String to, String message) {
        emailService.sendEmail(to, "Notification", message);
    }

    public void notifyBySms(String phoneNumber, String message) {
        smsService.sendSms(phoneNumber, message);
    }

    public void notifyAll(String email, String phone, String message) {
        emailService.sendEmail(email, "Notification", message);
        smsService.sendSms(phone, message);
    }

    public boolean isUserNotified(String email, String phone) {
        return emailService.isEmailSent(email) || smsService.isSmsSent(phone);
    }
}
