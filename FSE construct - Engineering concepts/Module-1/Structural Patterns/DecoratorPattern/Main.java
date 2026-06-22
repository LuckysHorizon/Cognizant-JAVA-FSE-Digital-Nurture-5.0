public class Main {

    public static void main(String [] args)
    {
        Coffee coffee = new MilkDecorator(new SimpleCoffee());

        System.out.println(coffee.getDescription());

        System.out.println(coffee.getCost());
    
    }    
}
