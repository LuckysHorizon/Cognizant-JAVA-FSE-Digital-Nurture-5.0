public class MotorCar implements Vehicle, EngineVehicle{
    @Override
    public void turnOnEngine()
    {
        System.out.println("Engine Started");
    }

    @Override
    public void accelerate()
    {
        System.out.println("Motor Car Accelerating");
    }
}
