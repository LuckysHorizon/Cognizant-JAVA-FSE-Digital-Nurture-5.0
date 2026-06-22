public class Main {
    public static void main(String [] args)
    {
        PaymentContext payment1 = new PaymentContext(new UpiPayment());

        payment1.makePayment(500);

        PaymentContext payment2 = new PaymentContext(new CardPayment());

        payment2.makePayment(1000);
    }    
}
