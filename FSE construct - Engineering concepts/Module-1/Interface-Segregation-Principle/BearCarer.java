public class BearCarer implements BearCleaner, BearFeeder{
    @Override
    public void washTheBear()
    {
        System.out.println("Washing The Bear");
    }

    @Override
    public void feedTheBear()
    {
        System.out.println("Feeding the bear ...");
    }

}
