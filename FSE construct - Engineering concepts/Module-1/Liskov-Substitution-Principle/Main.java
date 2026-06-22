public class Main {
    public static void main(String [] args)
    {
        MotorCar motorCar = new MotorCar();
        motorCar.turnOnEngine();
        motorCar.accelerate();

        System.out.println();

        ElectricCar electricCar = new ElectricCar();
        electricCar.accelerate();
    }
}
