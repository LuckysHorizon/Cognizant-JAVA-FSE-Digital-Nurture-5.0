package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Void Method Mocking")
class VoidMethodTest {

    @Mock
    private EmailService emailService;

    @Mock
    private SmsService smsService;

    @Test
    void testDoNothingForVoidMethod() {
        doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());
        emailService.sendEmail("test@mail.com", "Subject", "Body");
        verify(emailService).sendEmail("test@mail.com", "Subject", "Body");
    }

    @Test
    void testDoThrowForVoidMethod() {
        doThrow(new RuntimeException("Email server down"))
                .when(emailService).sendEmail(anyString(), anyString(), anyString());

        assertThrows(RuntimeException.class,
                () -> emailService.sendEmail("test@mail.com", "Subject", "Body"));
    }

    @Test
    void testDoAnswerForVoidMethod() {
        doAnswer(invocation -> {
            String to = invocation.getArgument(0);
            String message = invocation.getArgument(1);
            System.out.println("SMS sent to " + to + ": " + message);
            return null;
        }).when(smsService).sendSms(anyString(), anyString());

        smsService.sendSms("1234567890", "Test message");
        verify(smsService).sendSms("1234567890", "Test message");
    }

    @Test
    void testDoNothingThenDoThrow() {
        doNothing().doThrow(new RuntimeException("Second call fails"))
                .when(emailService).sendEmail(anyString(), anyString(), anyString());

        emailService.sendEmail("user@mail.com", "Hi", "First");
        assertThrows(RuntimeException.class,
                () -> emailService.sendEmail("user@mail.com", "Hi", "Second"));
    }
}
