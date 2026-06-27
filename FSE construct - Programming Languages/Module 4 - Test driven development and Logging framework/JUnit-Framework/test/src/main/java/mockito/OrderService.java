package mockito;

public class OrderService {

    private final PaymentGateway paymentGateway;
    private final EmailService emailService;

    public OrderService(PaymentGateway paymentGateway, EmailService emailService) {
        this.paymentGateway = paymentGateway;
        this.emailService = emailService;
    }

    public boolean placeOrder(String orderId, double amount, String customerEmail) {
        boolean paymentSuccess = paymentGateway.processPayment(orderId, amount);
        if (paymentSuccess) {
            emailService.sendEmail(customerEmail, "Order Confirmation",
                    "Your order " + orderId + " has been placed successfully.");
            return true;
        }
        return false;
    }

    public boolean cancelOrder(String transactionId, double amount, String customerEmail) {
        boolean refundSuccess = paymentGateway.refund(transactionId, amount);
        if (refundSuccess) {
            emailService.sendEmail(customerEmail, "Order Cancelled",
                    "Your refund of " + amount + " has been processed.");
            return true;
        }
        return false;
    }

    public String checkOrderStatus(String transactionId) {
        return paymentGateway.getPaymentStatus(transactionId);
    }
}
