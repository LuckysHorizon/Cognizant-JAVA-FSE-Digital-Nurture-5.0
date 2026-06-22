public class Main {

    public static void main(String[] args) {

        Television tv =
                new Television();

        Command command =
                new TurnOnCommand(tv);

        RemoteControl remote =
                new RemoteControl(command);

        remote.pressButton();
    }
}