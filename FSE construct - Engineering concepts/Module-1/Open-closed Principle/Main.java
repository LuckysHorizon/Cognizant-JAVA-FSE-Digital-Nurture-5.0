public class Main {
    public static void main(String [] args)
    {
        PaymentService service = new PaymentService();
        service.processPayment(
            new UpiPayment(), 500
        );
        service.processPayment(
            new CardPayment(), 5000
        );
    }
}
