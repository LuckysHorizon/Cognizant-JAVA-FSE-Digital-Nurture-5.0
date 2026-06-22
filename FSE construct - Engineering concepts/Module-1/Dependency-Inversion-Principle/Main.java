public class Main {
    public static void main(String [] args)
    {
        Keyboard keyboard = new GamingKeyboard();
        Monitor monitor = new Monitor();

        Windows98Machine machine = new Windows98Machine(keyboard,monitor);

        machine.startMachine();
    }    
}
