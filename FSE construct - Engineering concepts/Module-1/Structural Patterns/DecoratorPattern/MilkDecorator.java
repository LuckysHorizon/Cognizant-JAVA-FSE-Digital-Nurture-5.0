public class MilkDecorator implements Coffee{
    private Coffee coffee;
    public MilkDecorator(Coffee coffee)
    {
        this.coffee = coffee;
    }
    @Override
    public String getDescription()
    {
        return coffee.getDescription() + " + Milk";
    }

    @Override
    public double getCost()
    {
        return coffee.getCost() + 20;
    }
}
