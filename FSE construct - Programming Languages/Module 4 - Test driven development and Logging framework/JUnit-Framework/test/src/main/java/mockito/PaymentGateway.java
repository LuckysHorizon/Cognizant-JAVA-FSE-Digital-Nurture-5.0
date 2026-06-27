package mockito;

public interface PaymentGateway {
    boolean processPayment(String orderId, double amount);
    boolean refund(String transactionId, double amount);
    String getPaymentStatus(String transactionId);
}
