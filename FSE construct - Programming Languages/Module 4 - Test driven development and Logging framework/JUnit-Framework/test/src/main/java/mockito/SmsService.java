package mockito;

public interface SmsService {
    void sendSms(String phoneNumber, String message);
    boolean isSmsSent(String phoneNumber);
}
