package mockito;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    boolean isEmailSent(String to);
}
