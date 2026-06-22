public class VehicleFactory {
    public Vehicle getVehicle(String type)
    {
        if(type.equalsIgnoreCase("CAR"))
        {
            return new Car();
        }
        if(type.equalsIgnoreCase("BIKE"))
        {
            return new Bike();
        }
        return null;
    }    
}
