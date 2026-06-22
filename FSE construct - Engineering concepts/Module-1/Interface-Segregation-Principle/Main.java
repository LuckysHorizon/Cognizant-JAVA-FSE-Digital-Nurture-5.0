public class Main {
    public static void main(String [] args)
    {
        BearCarer carer = new BearCarer();
        
        carer.washTheBear();
        carer.feedTheBear();

        System.out.println();

        CrazyPerson person = new CrazyPerson();
        person.petTheBear();
    }
}
