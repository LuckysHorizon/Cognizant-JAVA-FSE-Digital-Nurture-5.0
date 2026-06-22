public class Main {
    public static void main(String [] args)
    {
        Internet internet = new ProxyInternet();
        internet.connectTo("google.com");
        internet.connectTo("reddit.com");
        internet.connectTo("youtube.com");
        internet.connectTo("facebook.com");
        internet.connectTo("instagram.com");
    }    
}
