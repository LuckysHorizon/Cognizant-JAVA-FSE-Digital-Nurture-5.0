package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Service Mocking: Email, SMS, Payment")
class ServiceMockingTest {

    @Nested
    @DisplayName("Order Service with Payment Gateway")
    class OrderServiceTest {

        @Mock
        private PaymentGateway paymentGateway;

        @Mock
        private EmailService emailService;

        @InjectMocks
        private OrderService orderService;

        @Test
        void testPlaceOrderSuccess() {
            when(paymentGateway.processPayment("ORD-001", 99.99)).thenReturn(true);
            assertTrue(orderService.placeOrder("ORD-001", 99.99, "user@mail.com"));
            verify(emailService).sendEmail(eq("user@mail.com"), anyString(), anyString());
        }

        @Test
        void testPlaceOrderPaymentFailed() {
            when(paymentGateway.processPayment("ORD-002", 50.0)).thenReturn(false);
            assertFalse(orderService.placeOrder("ORD-002", 50.0, "user@mail.com"));
            verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
        }

        @Test
        void testCancelOrder() {
            when(paymentGateway.refund("TXN-001", 99.99)).thenReturn(true);
            assertTrue(orderService.cancelOrder("TXN-001", 99.99, "user@mail.com"));
            verify(emailService).sendEmail(eq("user@mail.com"), eq("Order Cancelled"), anyString());
        }

        @Test
        void testCheckOrderStatus() {
            when(paymentGateway.getPaymentStatus("TXN-001")).thenReturn("COMPLETED");
            assertEquals("COMPLETED", orderService.checkOrderStatus("TXN-001"));
        }
    }

    @Nested
    @DisplayName("Notification Service")
    class NotificationServiceTest {

        @Mock
        private EmailService emailService;

        @Mock
        private SmsService smsService;

        @InjectMocks
        private NotificationService notificationService;

        @Test
        void testNotifyByEmail() {
            notificationService.notifyByEmail("user@mail.com", "Hello");
            verify(emailService).sendEmail("user@mail.com", "Notification", "Hello");
        }

        @Test
        void testNotifyBySms() {
            notificationService.notifyBySms("1234567890", "Hello");
            verify(smsService).sendSms("1234567890", "Hello");
        }

        @Test
        void testNotifyAll() {
            notificationService.notifyAll("user@mail.com", "1234567890", "Alert");
            verify(emailService).sendEmail("user@mail.com", "Notification", "Alert");
            verify(smsService).sendSms("1234567890", "Alert");
        }

        @Test
        void testIsUserNotified() {
            when(emailService.isEmailSent("user@mail.com")).thenReturn(true);
            assertTrue(notificationService.isUserNotified("user@mail.com", "1234567890"));
        }
    }
}
