public class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy)
    {
        this.strategy = strategy;
    }

    public void makePayment(double amount)
    {
        strategy.pay(amount);
    }
}
