public class Main {
    public static void main(String [] args)
    {
        YoutubeChannel channel = new YoutubeChannel();

        channel.subscribe(new Subscriber("Manikanta"));
        channel.subscribe(new Subscriber("Abhishek"));

        channel.uploadVideo("Observer Pattern Tutorial");
    }    
}
